package onlineShoppingSystem;

public abstract class ShoppingItem {
    String itemId;
    String itemName;
    String itemDescription;
    double price;
    int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public abstract void updateStock(int quantity);

    public abstract void addToCart(Customer customer);

    public abstract void generateInvoice(Customer customer);

    public abstract boolean validateItem();

    public String getDetails() {
        return String.format("Description: %s\nPrice: $%.2f", itemDescription, price, stockAvailable);
    }
}

