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
| `composition` | Composition | `Payment` creates its own `PaymentHistory` and changes it only through `authorise()` and `capture()`. `SharedHistoryPayment` takes one from the caller, so two payments can share it and one changes the other. `PaymentStateException` is inheritance used well: exception specialisation |
| `inheritance` | Inheritance, and delegation instead | `CountingLedger extends Ledger` counts three payments as six. `CountingLog` holds a `PaymentLog` and forwards to it, counting a call only after the log accepted it |
| `polymorphism.strings` | None yet | One method branches on a string; an unknown method quietly costs 0p |
| `polymorphism.overriding` | Polymorphism by overriding | Each `PaymentMethod` says its own fee and `Checkout` never asks which one it has; the interface contract refuses a new method without a fee |
| `polymorphism.switching` | The alternative design | Payment methods as a sealed, data only hierarchy and every fee rule in one `switch`; the compiler refuses a missing case |

## The overlays

`overlays/` holds small changes that `scripts/verify.sh` applies to a
scratch copy, so `src/` always stays the finished, green version.

| Overlay | Shows |
|---|---|
| `count-in-record-only` | The obvious fix to `CountingLedger`: count only in `record()`. It gives 3, because `Ledger.recordAll` happens to call `record()` |
| `ledger-uses-addall` | `Ledger.recordAll` changed to `addAll`. With the fix above, `CountingLedger` now counts 0, and not one line of it changed |
| `mobile-money-without-fee` | A new `PaymentMethod` with no fee does not compile |
| `switch-without-mobile-money` | A `switch` that forgets a case does not compile |

## Three questions, not a keyword

Java has no keyword for association, aggregation or composition. A
class diagram states them; the code has to keep the promise. Ask:

1. Is this a whole and its parts, or two objects that only know
   each other?
2. Can one part belong to more than one whole at the same time?
3. When the whole is deleted, does the part go with it?

How an object arrives (a constructor parameter, a field
initialiser) is a clue, not the answer. And logical ownership is not
the same as object lifetime: the garbage collector frees an object
when nothing can reach it, which a diagram does not predict.

## Simplifications

Amounts are whole pence in a `long`, and fees round down. A real
payment system uses a money type with a currency and an agreed
rounding rule, and keeps the status history in a database. The fees
are made up for the example.

## Code layout

Java lines stay within 64 columns, apart from `package` and
`import` lines, so two files fit side by side in the video.

To see one overlay on its own, `scripts/verify.sh` prints each to its
own file in `evidence/`, named after what it shows.
