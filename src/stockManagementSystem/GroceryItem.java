package stockManagementSystem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GroceryItem extends StockItem {
    private LocalDate expirationDate;
    private boolean nearExpiration;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, String expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
        this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        checkExpirationStatus();
    }

    private void checkExpirationStatus() {
        LocalDate today = LocalDate.now();
        this.nearExpiration = today.plusDays(7).isAfter(expirationDate);
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (nearExpiration ? 0.7 : 1.0);
    }

    @Override
    public String generateStockReport() {
        return String.format("Grocery Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nExpiration: %s\nStatus: %s\nStock Value: $%.2f", itemId, itemName, quantityInStock, pricePerUnit, expirationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), nearExpiration ? "Near Expiration (30% discount)" : "Fresh", calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && !LocalDate.now().isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() { return expirationDate; }
    public boolean isNearExpiration() { return nearExpiration; }
}

