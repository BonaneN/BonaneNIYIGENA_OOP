package onlineShoppingSystem;
import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amountPaid;
    private LocalDate transactionDate;

    public Payment(String id, String method, double amount, double totalPrice) {
        if (!method.equalsIgnoreCase("Credit Card") && !method.equalsIgnoreCase("PayPal")) {
            throw new IllegalArgumentException("Invalid payment method.");
        }
        if (amount < totalPrice) {
            throw new IllegalArgumentException("Insufficient payment.");
        }

        this.paymentId = id;
        this.paymentMethod = method;
        this.amountPaid = amount;
        this.transactionDate = LocalDate.now();
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
