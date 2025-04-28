package onlineShoppingSystem;
import java.time.LocalDate;

public class Groceries extends ShoppingItem {
    private LocalDate expiryDate;
    private boolean bulkDiscount;

    public Groceries(String id, String name, String desc, double price, int stock, LocalDate expiry, boolean bulk) {
        super(id, name, desc, price, stock);
        this.expiryDate = expiry;
        this.bulkDiscount = bulk;
        if (bulk) this.price *= 0.9;
    }

    public void updateStock(int quantity) {
        stockAvailable += quantity;
    }

    public void addToCart(Customer customer) {
        if (expiryDate.isBefore(LocalDate.now())) {
            System.out.println("⚠️ Warning: Item is expired!");
        }
    }

    @Override
    public void generateInvoice(Customer customer) {

    }

    public boolean validateItem() {
        return stockAvailable > 0 && expiryDate.isAfter(LocalDate.now());
    }
}
