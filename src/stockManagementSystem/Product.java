package stockManagementSystem;

public class Product {
    private String productId;
    private String productName;
    private String brand;
    private String supplier;
    private int stockQuantity;

    public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }

        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.supplier = supplier;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters with validation
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public String getSupplier() { return supplier; }
    public int getStockQuantity() { return stockQuantity; }

    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.productName = productName;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return String.format("Product ID: %s\nName: %s\nBrand: %s\nSupplier: %s\nQuantity: %d",
                productId, productName, brand, supplier, stockQuantity);
    }
}