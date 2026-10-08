#!/usr/bin/env bash
# Reproduces every output shown in the video and writes it to
# evidence/. Needs a JDK 25 on the PATH (or JAVA_HOME set).
#
#   scripts/verify.sh
#
# Overlays are applied to a scratch copy, never to src/.
set -uo pipefail
cd "$(dirname "$0")/.."
ROOT=$(pwd)
EV=$ROOT/evidence
mkdir -p "$EV"
JAVA=${JAVA_HOME:+$JAVA_HOME/bin/}java

say() { # say <log> <command...>: echo the command, run it, keep both
  local log=$1; shift
  { echo "\$ $*"; "$@" 2>&1; echo "exit $?"; } >> "$log"
}

# Strip Maven's own noise so a log holds only what a reader needs.
clean() {  # keeps the path from src/ onwards
  grep -v -e '^WARNING:' -e 'Downloading' -e 'Downloaded' \
    | sed -E 's/^\[ERROR\] //; s#^.*/src/main/java/#src/main/java/#'
}

java_version() {
  "$JAVA" -version 2>&1 | head -1 > "$EV/java-version.log"
}

tests() {
  local log=$EV/tests.log; : > "$log"
  ./mvnw -q -o test > "$EV/.tests.raw" 2>&1 || ./mvnw -q test > "$EV/.tests.raw" 2>&1
  local code=$?
  ./mvnw -o surefire-report:report-only -q >/dev/null 2>&1 || true
  echo "\$ ./mvnw test" >> "$log"
  for f in target/surefire-reports/TEST-*.xml; do
    python3 - "$f" >> "$log" <<'PY'
import sys, xml.etree.ElementTree as E
r = E.parse(sys.argv[1]).getroot()
name = r.get('name').replace('com.example.payments.', '')
print(f"{name}: {r.get('tests')} tests, {r.get('failures')} failures, {r.get('errors')} errors")
for c in r.findall('testcase'):
    print(f"  {'FAIL' if len(c) else 'ok  '} {c.get('name')}")
PY
  done
  echo "exit $code" >> "$log"
}

demo() { # demo <name> <package>
  local log=$EV/$1.log; : > "$log"
  echo "\$ java com.example.payments.$2.Demo" >> "$log"
  "$JAVA" -cp target/classes "com.example.payments.$2.Demo" >> "$log" 2>&1
}

# overlay <name> <package> <overlay...>: copy, apply, compile, run
overlay() {
  local name=$1 pkg=$2; shift 2
  local log=$EV/$name.log scratch
  scratch=$(mktemp -d)
  : > "$log"
  cp -R pom.xml mvnw .mvn src "$scratch/"
  for o in "$@"; do
    cp -R "overlays/$o/." "$scratch/"
    echo "# overlay: $o" >> "$log"
  done
  echo "\$ ./mvnw compile" >> "$log"
  if (cd "$scratch" && ./mvnw -q -o compile -Dmaven.compiler.showWarnings=false) > "$scratch/.out" 2>&1; then
    echo "BUILD SUCCESS" >> "$log"
    echo "\$ java com.example.payments.$pkg.Demo" >> "$log"
    "$JAVA" -cp "$scratch/target/classes" "com.example.payments.$pkg.Demo" >> "$log" 2>&1
  else
    grep -E '^\[ERROR\] /' "$scratch/.out" | sort -u | clean >> "$log"
    echo "BUILD FAILURE" >> "$log"
  fi
  rm -rf "$scratch"
}

java_version
tests
demo association association
demo aggregation aggregation
demo composition composition
demo inheritance inheritance
demo fees-strings polymorphism.strings
demo fees-overriding polymorphism.overriding
demo fees-switching polymorphism.switching
overlay inheritance-record-only inheritance count-in-record-only
overlay inheritance-base-changed inheritance count-in-record-only ledger-uses-addall
overlay overriding-missing-fee polymorphism.overriding mobile-money-without-fee
overlay switch-missing-case polymorphism.switching switch-without-mobile-money
rm -f "$EV/.tests.raw"
echo "evidence written to $EV"
