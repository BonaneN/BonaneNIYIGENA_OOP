package onlineShoppingSystem;

public class Clothing extends ShoppingItem {
    private String size;
    private boolean seasonal;  // Use `seasonal` instead of `isSeasonal`
    private double seasonalDiscount = 20.0;  // 20% discount

    public Clothing(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String size, boolean seasonal) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.size = size;
        this.seasonal = seasonal;
    }

    @Override
    public void updateStock(int quantity) {
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        System.out.println("Size Selected: " + size + (isSeasonal() ? " | Seasonal Discount Applied!" : ""));
    }

    @Override
    public void generateInvoice(Customer customer) {}

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && size != null && !size.isEmpty();
    }

    @Override
    public String getDetails() {
        String details = super.getDetails() + "\nSize: " + size;
        if (isSeasonal()) {
            details += "\nSeasonal Discount: 20%";
        }
        return details;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    @Override
    public double getPrice() {
        double price = super.getPrice();
        if (isSeasonal()) {
            price -= price * (seasonalDiscount / 100);
        }
        return price;
    }
}
