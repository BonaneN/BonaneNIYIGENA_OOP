package onlineShoppingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public List<ShoppingItem> cartItems;
    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.cartItems = new ArrayList<>();
    }

    public void addItem(ShoppingItem item) {
        if (!item.validateItem()) {
            System.out.println("❌ Cannot add item: Invalid or out of stock.");
            return;
        }
        item.addToCart(customer);  // This is where the item is being added to cart
        cartItems.add(item);       // Add item to cartItems list
        item.updateStock(-1);      // Decrease stock
        System.out.println("✅ " + item.getItemName() + " added to cart.");
    }


    public double getTotalPrice() {
        double total = 0;
        for (ShoppingItem item : cartItems) {
            double itemPrice = item.getPrice();
            total += itemPrice;
        }
        return total;
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("🛑 Your cart is empty.");
            return;
        }

        Map<String, Integer> itemQuantities = new HashMap<>();
        for (ShoppingItem item : cartItems) {
            itemQuantities.put(item.itemName, itemQuantities.getOrDefault(item.itemName, 0) + 1);
        }

        for (String itemName : itemQuantities.keySet()) {
            int quantity = itemQuantities.get(itemName);
            ShoppingItem item = findItemByName(itemName);

            if (item != null) {
                System.out.println("\nItem: " + item.itemName);
                System.out.println("Quantity: " + quantity);
                System.out.println(item.getDetails());
                if (item instanceof Clothing && ((Clothing) item).isSeasonal()) {
                    System.out.println("Discount Applied");
                }
                System.out.println("Price to be paid: $" + (item.getPrice() * quantity));
                System.out.println("=============================");
            }
        }

        System.out.printf("Total: $%.2f\n", getTotalPrice());
        System.out.println("=============================");
    }

    private ShoppingItem findItemByName(String itemName) {
        for (ShoppingItem item : cartItems) {
            if (item.itemName.equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void generateCombinedInvoice() {
        System.out.println("\n=== 🧾 SHOPPED ITEMS INVOICE ===");
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Date: " + java.time.LocalDate.now());
        System.out.println("----------------------------");

        Map<String, Integer> itemQuantities = new HashMap<>();
        for (ShoppingItem item : cartItems) {
            itemQuantities.put(item.getItemName(), itemQuantities.getOrDefault(item.getItemName(), 0) + 1);
        }

        double total = 0;
        for (String itemName : itemQuantities.keySet()) {
            int quantity = itemQuantities.get(itemName);
            ShoppingItem item = findItemByName(itemName); // Find the item by name

            if (item != null) {
                double itemPrice = item.getPrice();
                double itemTotal = itemPrice * quantity;
                System.out.printf("%s (%d) - $%.2f each - AmountPaid: $%.2f\n", item.getItemName(), quantity, itemPrice, itemTotal);
                total += itemTotal;
            }
        }

        System.out.println("----------------------------");
        System.out.printf("TOTAL: $%.2f\n", total);
        System.out.println("Thank you for shopping with us. Have a great time!");
        System.out.println("============================\n");
    }

}
