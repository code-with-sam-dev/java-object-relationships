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
                + " payments, "
                + monday.totalCents() + " cents");

        monday.reject();
        IO.println("MON rejected: " + monday.rejected());
        IO.println("P-1 unchanged: " + first.id() + ", "
                + first.cents() + " cents");

        var retry = new SettlementBatch("MON-RETRY");
        retry.add(first);
        retry.add(second);
        IO.println("MON-RETRY: " + retry.payments().size()
                + " payments, "
                + retry.totalCents() + " cents");
        IO.println("same P-1 object in both batches: "
                + (monday.payments().getFirst()
                        == retry.payments().getFirst()));
    }
}
