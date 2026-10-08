# Object relationships in Java 25

Association, aggregation, composition, inheritance and polymorphism,
shown with one small payment scenario: Sarah Thompson pays a shop,
the payment moves through its statuses, and the shop settles its
payments with the bank at the end of the day.

Every package is one relationship, small enough to read in one
sitting, with a `Demo` you can run and tests that pin the behaviour.
Where a trade-off is discussed, both designs are in the code.

Versions as of October 2026: Java 25 (the current long term support
release at the time of writing), JUnit 6.0.3, AssertJ 3.27.7.

## Run it

    ./mvnw test                 # needs a JDK 25
    scripts/verify.sh           # tests, every demo, every overlay

or, with no JDK installed:

    docker build -t object-relationships .
    docker run --rm object-relationships

`scripts/verify.sh` writes everything shown in the video to
`evidence/`. Nothing in the video is typed by hand.

## The packages

| Package | Relationship | What to look at |
|---|---|---|
| `association` | Association | `Payment` holds a `Customer` it did not create. Two payments share one customer object |
| `aggregation` | Aggregation | `SettlementBatch` groups payments that existed before it and can move to another batch |
| `composition` | Composition | `Payment` copies the history it is given into a list only it can change. `LeakyPayment` differs by one line and can be changed from outside |
| `inheritance` | Inheritance, and composition instead | `CountingLedger extends Ledger` counts three payments as six. `CountingLog` wraps a `PaymentLog` and counts three |
| `polymorphism.strings` | None yet | One method branches on a string; an unknown method quietly costs 0p |
| `polymorphism.overriding` | Polymorphism by overriding | Each `PaymentMethod` says its own fee; the compiler refuses a new one without a fee |
| `polymorphism.switching` | The alternative | A `switch` over a sealed interface; the compiler refuses a missing case |

## The overlays

`overlays/` holds small changes that `scripts/verify.sh` applies to a
scratch copy, so `src/` always stays the finished, green version.

| Overlay | Shows |
|---|---|
| `count-in-record-only` | The obvious fix to `CountingLedger`: count only in `record()`. It gives 3 |
| `ledger-uses-addall` | `Ledger.recordAll` changed to `addAll`. With the fix above, `CountingLedger` now counts 0, and not one line of it changed |
| `mobile-money-without-fee` | A new `PaymentMethod` with no fee does not compile |
| `switch-without-mobile-money` | A `switch` that forgets a case does not compile |

## Ownership and lifecycle in Java

Java has no destructors, and the garbage collector frees an object
once nothing can reach it. So composition in Java is not something
the language enforces. It is a design decision you enforce with
encapsulation: the whole creates or copies its parts, never hands
out a reference that can change them, and nothing else holds them.
How an object arrives, through a constructor or otherwise, does not
decide the relationship. Who creates it, who else holds it and
whether it can outlive or move does.

## Simplifications

Amounts are whole pence in a `long`, and fees round down. A real
payment system uses a money type with a currency and an agreed
rounding rule, and keeps the status history in a database. The fees
are made up for the example.

## Code layout

Java lines stay within 64 columns, apart from `package` and
`import` lines, so two files fit side by side in the video.
