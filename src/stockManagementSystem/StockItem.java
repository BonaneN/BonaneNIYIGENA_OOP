package stockManagementSystem;

public abstract class StockItem {
     String itemId;
     String itemName;
     int quantityInStock;
     double pricePerUnit;
     String category;
     String supplier;

    public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.supplier = supplier;
    }


    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public String getCategory() {
        return category;
    }
    public String getSupplier() {
        return supplier;
    }

    public abstract void updateStock(int quantity);
    public abstract double calculateStockValue();
    public abstract String generateStockReport();
    public abstract boolean validateStock();

}
