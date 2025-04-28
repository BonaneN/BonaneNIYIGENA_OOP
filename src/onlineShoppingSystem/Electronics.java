package onlineShoppingSystem;


public class Electronics extends ShoppingItem {
    private String warranty;

    public Electronics(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String warranty) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warranty = warranty;
    }

    public void updateStock(int quantity) {
        stockAvailable += quantity;
    }

    public void addToCart(Customer customer) {
        System.out.println("Warranty info: " + warranty);
    }

    @Override
    public void generateInvoice(Customer customer) {}

    @Override
    public boolean validateItem() {
        return stockAvailable > 0;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nWarranty: " + warranty;
    }
}

