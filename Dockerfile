# Runs the tests and every demo on Java 25 without installing a JDK.
#   docker build -t object-relationships .
#   docker run --rm object-relationships
FROM eclipse-temurin:25-jdk
# verify.sh summarises the test reports with a few lines of Python.
RUN apt-get update \
    && apt-get install -y --no-install-recommends python3 \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests compile
CMD ["scripts/verify.sh"]
