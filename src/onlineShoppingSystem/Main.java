package onlineShoppingSystem;
import java.util.*;
import java.time.LocalDate;

public class Main {
    private static List<ShoppingItem> inventory = new ArrayList<>();
    private static Map<String, Integer> itemSales = new HashMap<>();
    private static double totalRevenue = 0;
    private static Map<String, Integer> paymentBreakdown = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("=== Welcome to the Online Store ===");

            String id;
            while (true) {
                System.out.print("Enter Customer ID: ");
                id = sc.nextLine().trim();
                if (!id.isEmpty() && id.matches(".*\\d.*") && !id.matches("[a-zA-Z]+") && !id.startsWith("-")) {
                    break;
                }
                System.out.println("❌ Customer ID must contain at least one number, cannot be only letters, and cannot be negative. Try again.");
            }

            String name;
            while (true) {
                System.out.print("Enter Name: ");
                name = sc.nextLine().trim();
                if (!name.isEmpty() && name.matches("[a-zA-Z ]+")) break;
                System.out.println("❌ Name must only contain letters and cannot be empty. Try again.");
            }

            String email;
            while (true) {
                System.out.print("Enter Email: ");
                email = sc.nextLine().trim();
                if (!email.isEmpty() && email.contains("@") && email.contains(".")) break;
                System.out.println("❌ Invalid or empty email format. Try again.");
            }

            String address;
            while (true) {
                System.out.print("Enter Address: ");
                address = sc.nextLine().trim();

                if (address.matches("[0-9]+")) {
                    System.out.println("❌ Address cannot contain only numbers. It must also contain letters. Try again.");
                    continue;
                }
                if (!address.isEmpty() && address.matches("[a-zA-Z0-9\\s,.-/]+")) break;
                System.out.println("❌ Address cannot be empty and must contain valid characters (letters, numbers, spaces, commas, periods, hyphens, or slashes). Try again.");
            }

            String phone;
            while (true) {
                System.out.print("Enter Phone: ");
                phone = sc.nextLine().trim();
                if (!phone.isEmpty() && phone.matches("[0-9]{10,13}")) break;
                System.out.println("❌ Phone number must be 10-13 digits and cannot be empty or negative. Try again.");
            }

            Customer customer = new Customer(id, name, email, address, phone);
            ShoppingCart cart = new ShoppingCart(customer);

            loadInventory();
            while (true) {
                System.out.println("\n=== Product Catalog ===");
                for (int i = 0; i < inventory.size(); i++) {
                    ShoppingItem item = inventory.get(i);
                    System.out.printf("%d. %s - $%.2f (%d in stock)\n", i + 1, item.itemName, item.price, item.stockAvailable);
                }

                System.out.print("Enter item number to add to cart (0 to checkout): ");
                int choice = Integer.parseInt(sc.nextLine().trim());

                if (choice == 0) break;
                if (choice < 1 || choice > inventory.size()) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                ShoppingItem selectedItem = inventory.get(choice - 1);
                cart.addItem(selectedItem);
            }

            if (cart.cartItems.isEmpty()) {
                System.out.println("🛑 No items in cart. Exiting.");
                return;
            }

            System.out.println("\n=== Your Cart ===");
            cart.displayCart();

            double total = cart.getTotalPrice();
            String method;
            while (true) {
                System.out.print("Enter Payment Method (Credit Card/PayPal): ");
                method = sc.nextLine().trim();
                if (!method.isEmpty() && (method.equalsIgnoreCase("Credit Card") || method.equalsIgnoreCase("PayPal"))) break;
                System.out.println("❌ Invalid or empty method. Please enter 'Credit Card' or 'PayPal'.");
            }

            while (true) {
                System.out.println("\n=== Payment Confirmation ===");
                System.out.printf("Your Total Price: $%.2f\n", total);
                System.out.printf("You chose %s as your payment method.\n", method);

                String confirmation;
                while (true) {
                    System.out.printf("Do you confirm the payment of $%.2f using %s? (c for confirm, d for decline): ", total, method);
                    confirmation = sc.nextLine().trim().toLowerCase();

                    if (confirmation.equals("c")) {
                        Payment payment = new Payment("P-" + System.currentTimeMillis(), method, total, total);
                        System.out.println("✅ Payment Successful!");
                        break;

                    } else if (confirmation.equals("d")) {
                        System.out.println("❌ Payment Declined. Please select your payment method again.");
                        break;
                    } else {
                        System.out.println("❌ Invalid input. Please type 'c' for confirm or 'd' for decline.");
                    }
                }

                if (confirmation.equals("c")) {
                    break;
                } else {
                    System.out.println("\n=== Reselect Payment Method ===");
                    while (true) {
                        System.out.print("Select Payment Method (Credit Card/PayPal): ");
                        method = sc.nextLine().trim();
                        if (method.equalsIgnoreCase("Credit Card") || method.equalsIgnoreCase("PayPal")) {
                            break;
                        } else {
                            System.out.println("❌ Invalid method. Please select 'Credit Card' or 'PayPal'.");
                        }
                    }
                }
            }

            cart.generateCombinedInvoice();
            logSales(cart.cartItems, total, method);

            printSalesReport();

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        sc1.close();
    }

    public static void loadInventory() {
        inventory.add(new Electronics("E101", "Laptop", "Gaming Laptop", 1200.0, 5, "2-year Warranty"));
        inventory.add(new Clothing("C101", "T-Shirt", "Summer Shirt", 20.0, 10, "L", true));
        inventory.add(new Groceries("G101", "Apple", "Fresh Apples", 1.0, 30, LocalDate.now().plusDays(5), true));
        inventory.add(new Books("B101", "Java Book", "Learn Java", 45.0, 7, "978-1234567890", "3rd Edition"));
        inventory.add(new Accessories("A101", "Watch", "Leather Strap", 75.0, 3, "Leather", 4.5));
    }

    public static void logSales(List<ShoppingItem> items, double amount, String method) {
        totalRevenue += amount;
        paymentBreakdown.put(method, paymentBreakdown.getOrDefault(method, 0) + 1);
        for (ShoppingItem item : items) {
            itemSales.put(item.itemName, itemSales.getOrDefault(item.itemName, 0) + 1);
        }
    }

    public static void printSalesReport() {
        System.out.println("\n=== 📊 Sales Report ===");
        System.out.printf("Total Revenue: $%.2f\n", totalRevenue);
        System.out.println("\nItem Sales:");
        itemSales.forEach((name, count) -> System.out.println("- " + name + ": " + count));
        System.out.println("\nPayment Methods:");
        paymentBreakdown.forEach((method, count) -> System.out.println("- " + method + ": " + count));
    }
}
