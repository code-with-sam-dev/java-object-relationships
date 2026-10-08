package com.example.payments.aggregation;

public class Demo {

    void main() {
        var sarah = new Customer("C-1", "Sarah Thompson");
        var first = new Payment("P-1", sarah, 2_500);
        var second = new Payment("P-2", sarah, 4_000);

        var monday = new SettlementBatch("MON");
        monday.add(first);
        monday.add(second);
        IO.println("MON: " + monday.payments().size()
                + " payments, " + monday.totalPence() + "p");

        IO.println("the bank rejects MON");

        var tuesday = new SettlementBatch("TUE");
        tuesday.add(first);
        tuesday.add(second);
        IO.println("TUE: " + tuesday.payments().size()
                + " payments, " + tuesday.totalPence() + "p");
        IO.println("same P-1 object in both batches: "
                + (monday.payments().getFirst()
                        == tuesday.payments().getFirst()));
    }
}
