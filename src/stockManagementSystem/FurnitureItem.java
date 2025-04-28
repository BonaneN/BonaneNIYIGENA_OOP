package stockManagementSystem;

public class FurnitureItem extends StockItem {
    private double weight; // in kg
    private boolean isPacked;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, double weight) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
        this.weight = weight;
        this.isPacked = false;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }

    @Override
    public String generateStockReport() {
        return String.format("Furniture Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice: $%.2f\nWeight: %.2f kg\nPacked: %s\nStock Value: $%.2f", itemId, itemName, quantityInStock, pricePerUnit, weight, isPacked ? "Yes" : "No", calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && isPacked;
    }

    public void packForDelivery() {
        this.isPacked = true;
    }

    public double getWeight() { return weight; }
    public boolean isPacked() { return isPacked; }
}