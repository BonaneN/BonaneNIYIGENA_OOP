package onlineShoppingSystem;

public class Accessories extends ShoppingItem {
    private String variety;
    private double customerRating;

    public Accessories(String id, String name, String desc, double price, int stock, String variety, double rating) {
        super(id, name, desc, price, stock);
        this.variety = variety;
        this.customerRating = rating;
    }

    public void updateStock(int quantity) {
        stockAvailable += quantity;
    }

    public void addToCart(Customer customer) {
        System.out.println("Variety: " + variety + " | Rating: " + customerRating);
    }

    @Override
    public void generateInvoice(Customer customer) {

    }

    public boolean validateItem() {
        return stockAvailable > 0;
    }


}

