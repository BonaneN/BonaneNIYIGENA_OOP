package stockManagementSystem;
import java.util.HashMap;
import java.util.Map;

public class ClothingItem extends StockItem {
    private Map<String, Map<String, Integer>> sizesColors;
    private boolean hasDiscount;
    private double discountRate;

    public ClothingItem(String itemId, String itemName, double pricePerUnit, String supplier) {
        super(itemId, itemName, 0, pricePerUnit, "Clothing", supplier);
        this.sizesColors = new HashMap<>();
        this.hasDiscount = false;
        this.discountRate = 0.0;
    }

    public void addSizeColorQuantity(String size, String color, int quantity) {
        sizesColors.putIfAbsent(size, new HashMap<>());
        sizesColors.get(size).put(color, sizesColors.get(size).getOrDefault(color, 0) + quantity);
        this.quantityInStock += quantity;
    }

    @Override
    public void updateStock(int quantity) {
        System.out.println("For clothing items, please use addSizeColorQuantity method");
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (hasDiscount ? (1.0 - discountRate) : 1.0);
    }

    @Override
    public String generateStockReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Clothing Item Report:\nID: %s\nName: %s\nTotal Quantity: %d\nPrice: $%.2f\nDiscount: %s\nStock Value: $%.2f\n", itemId, itemName, quantityInStock, pricePerUnit, hasDiscount ? (int)(discountRate * 100) + "%" : "No", calculateStockValue()));

        report.append("Size/Color Breakdown:\n");
        for (String size : sizesColors.keySet()) {
            for (String color : sizesColors.get(size).keySet()) {
                report.append(String.format("- Size %s, Color %s: %d\n", size, color, sizesColors.get(size).get(color)));
            }
        }
        return report.toString();
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0;
    }

    public void setDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public void applyDiscount(double discountRate) {
        if (discountRate < 0 || discountRate > 0.5) {
            throw new IllegalArgumentException("Discount rate must be between 0% and 50%");
        }
        this.hasDiscount = true;
        this.discountRate = discountRate;
    }

    public Map<String, Map<String, Integer>> getSizesColors() {
        return sizesColors;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }
}
