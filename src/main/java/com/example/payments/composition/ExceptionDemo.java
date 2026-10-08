package com.example.payments.composition;

public class ExceptionDemo {

    void main() {
        var sarah = new Customer("C-1", "Sarah Thompson");
        var payment = new Payment("P-1", sarah, 2_500);
        try {
            payment.capture();
        } catch (IllegalStateException e) {
            IO.println("caught by: catch "
                    + "(IllegalStateException e)");
            IO.println("class:     "
                    + e.getClass().getSimpleName());
            IO.println("message:   " + e.getMessage());
        }
    }
}
