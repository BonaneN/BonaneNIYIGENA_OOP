package stockManagementSystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PerishableItem extends StockItem {
    private LocalDate expirationDate;
    private int shelfLife; // in days

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, String expirationDate, int shelfLife) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
        this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.shelfLife = shelfLife;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (isAboutToExpire() ? 0.5 : 1.0);
    }

    @Override
    public String generateStockReport() {
        return String.format("Perishable Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nExpiration: %s\nShelf Life: %d days\nStatus: %s\nStock Value: $%.2f", itemId, itemName, quantityInStock, pricePerUnit, expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), shelfLife, isAboutToExpire() ? "About to expire (50% discount)" : "Fresh", calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && !isExpired();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public boolean isAboutToExpire() {
        return LocalDate.now().plusDays(2).isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public int getShelfLife() { return shelfLife; }
}
