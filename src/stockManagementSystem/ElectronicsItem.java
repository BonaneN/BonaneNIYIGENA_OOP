package stockManagementSystem;

public class ElectronicsItem extends StockItem {
    private int warrantyPeriod;
    private double discount;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int warrantyPeriod) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        this.warrantyPeriod = warrantyPeriod;
        this.discount = 0.0;
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit * (1 - discount/100);
    }

    @Override
    public boolean validateStock() {
        if (quantityInStock <= 0) {
            System.out.println("Alert: Stock quantity must be greater than zero for sale!");
            return false; // Validation fails
        }
        return true; // Validation passes
    }

    private double discountRate = 0.0; // not 5000!

    public void applyDiscount(double discount) {
        if (discount < 0 || discount > 0.5) {
            throw new IllegalArgumentException("Discount must be between 0% and 50%");
        }
        this.discountRate = discount; // example: 0.2 for 20%
        this.pricePerUnit = this.pricePerUnit * (1 - discount); // reduce price accordingly
    }

    public String generateStockReport() {
        return "Electronics Item Report:\n" + "ID: " + itemId + "\n" + "Name: " + itemName + "\n" + "Quantity: " + quantityInStock+ "\n" + String.format("Price: $%.2f\n", pricePerUnit) + "Warranty: " + getWarrantyPeriod() + " months\n" + String.format("Discount: %.1f%%\n", discountRate * 100) + String.format("Stock Value: $%.2f", quantityInStock * pricePerUnit);
    }


    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public double getDiscount() {
        return discount;
    }
}

