package onlineShoppingSystem;

public class Books extends ShoppingItem {
    private String isbn, edition;

    public Books(String id, String name, String desc, double price, int stock, String isbn, String edition) {
        super(id, name, desc, price, stock);
        this.isbn = isbn;
        this.edition = edition;
    }

    public void updateStock(int quantity) {
        stockAvailable += quantity;
    }

    public void addToCart(Customer customer) {
        System.out.println("Book Edition: " + edition);
    }

    @Override
    public void generateInvoice(Customer customer) {

    }

    public boolean validateItem() {
        return stockAvailable > 0;
    }
}

