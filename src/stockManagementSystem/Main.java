package stockManagementSystem;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Warehouse warehouse = new Warehouse("WH-01", "Rwanda", 500, "Bonane");

        while (true) {
            System.out.println("\nAdvanced Stock Management System");
            System.out.println("1. Add Supplier");
            System.out.println("2. Add Stock Item");
            System.out.println("3. View Inventory Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = getIntInput(scanner, 1, 4);

            switch (choice) {
                case 1:
                    addSupplier(scanner);
                    break;
                case 2:
                    addStockItem(scanner, warehouse);
                    break;
                case 3:
                    System.out.println("\n" + warehouse.generateInventoryReport() + "\n" + generateSuppliersReport());
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
            }
        }
    }

    private static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.printf("Invalid input!!! Please enter a number between %d and %d: ", min, max);
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, double min) {
        while (true) {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine();
                if (input >= min) {
                    return input;
                }
                System.out.printf("Please enter a number greater than or equal to %.2f: ", min);
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input!!! Please enter a valid number: ");
            }
        }
    }

    private static String getTextInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.matches(".*[a-zA-Z]+.*")) { // Must contain at least one letter
                return input;
            }
            System.out.println("Invalid input!!! Must contain letters and cannot be empty. Please try again.");
        }
    }

    private static String getValidatedIdInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.startsWith("-")) {
                return input;
            }
            System.out.println("Invalid ID!!! ID cannot be empty or start with a negative sign. Please try again.");
        }
    }

    private static List<Supplier> suppliers = new ArrayList<>();

    private static void addSupplier(Scanner scanner) {
        System.out.println("\nAdd New Supplier");
        String id = getValidatedIdInput(scanner, "Enter Supplier ID: ");
        String company = getTextInput(scanner, "Enter Company Name: ");
        String contact = getTextInput(scanner, "Enter Contact Person: ");

        String phone = "";
        while (true) {
            System.out.print("Enter Phone: ");
            phone = scanner.nextLine();
            if (phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")) {
                break;
            }
            System.out.println("Invalid phone format!!! Please try again.");
        }

        String email = "";
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();
            if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                break;
            }
            System.out.println("Invalid email format!!! Please try again e.g, user@example.com.");
        }

        try {
            Supplier supplier = new Supplier(id, company, contact, phone, email);
            suppliers.add(supplier);
            System.out.println("\nSupplier added successfully:\n" + supplier);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String generateSuppliersReport() {
        StringBuilder report = new StringBuilder("\nSuppliers List Report:\n");
        report.append("=================\n");
        if (suppliers.isEmpty()) {
            report.append("No suppliers registered yet.\n");
        } else {
            for (Supplier supplier : suppliers) {
                report.append(supplier.toString()).append("\n");
            }
        }
        return report.toString();
    }

    private static double getDiscountInput(Scanner scanner) {
        double discount = -1;
        while (discount < 0 || discount > 50) {
            System.out.print("Enter discount percentage (0-50): ");
            try {
                discount = Double.parseDouble(scanner.nextLine());
                if (discount < 0 || discount > 50) {
                    System.out.println("Invalid discount. Must be between 0 and 50. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter a valid discount percentage.");
            }
        }
        return discount / 100.0; // return as decimal (e.g., 20% => 0.2)
    }



    private static void addStockItem(Scanner scanner, Warehouse warehouse) {
        System.out.println("\nAdd Stock Item");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.println("4. Furniture");
        System.out.println("5. Perishable");
        System.out.print("Select category: ");
        int category = getIntInput(scanner, 1, 5);

        String id = getValidatedIdInput(scanner, "Enter Item ID: ");
        String name = getTextInput(scanner, "Enter Item Name: ");

        System.out.print("Enter Quantity: ");
        int quantity = getIntInput(scanner, 0, Integer.MAX_VALUE);

        System.out.print("Enter Price per Unit: ");
        double price = getDoubleInput(scanner, 0.01);

        String supplier = getTextInput(scanner, "Enter Supplier: ");

        StockItem item = null;
        boolean itemCreated = false;

        while (!itemCreated) {
            try {
                switch (category) {
                    case 1: // Electronics
                        System.out.print("Enter Warranty Period (months): ");
                        int warranty = getIntInput(scanner, 0, 120); // Max 10 years warranty
                        item = new ElectronicsItem(id, name, quantity, price, supplier, warranty);

                        System.out.print("Apply discount? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            System.out.print("Enter discount percentage (0-50): ");
                            double discount = getDiscountInput(scanner);
                            if (discount > 0.5) {
                                System.out.println("Discount cannot exceed 50%!!!!");
                                discount = 0.5;
                            }
                            ((ElectronicsItem) item).applyDiscount(discount);
                        }
                        itemCreated = true;
                        break;

                    case 2: // Clothing
                        item = new ClothingItem(id, name, price, supplier);

                        System.out.print("Does this clothing have discount? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            double discount = -1;
                            while (discount < 0 || discount > 50) {
                                System.out.print("Enter discount percentage (0-50): ");
                                try {
                                    discount = Double.parseDouble(scanner.nextLine());
                                    if (discount < 0 || discount > 50) {
                                        System.out.println("Invalid discount. Must be between 0 and 50.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid number. Please enter a valid discount percentage.");
                                }
                            }
                            ((ClothingItem) item).applyDiscount(discount / 100.0); // Convert percent to decimal
                        }

                        System.out.println("Add size/color quantities (enter 'done' when finished):");
                        while (true) {
                            System.out.print("Enter size (or 'done'): ");
                            String size = scanner.nextLine();
                            if (size.equalsIgnoreCase("done")) break;

                            System.out.print("Enter color: ");
                            String color = scanner.nextLine();

                            System.out.print("Enter quantity: ");
                            int qty = getIntInput(scanner, 0, Integer.MAX_VALUE);

                            ((ClothingItem) item).addSizeColorQuantity(size, color, qty);
                        }
                        itemCreated = true;
                        break;


                    case 3: // Grocery
                        LocalDate expDate = null;
                        while (expDate == null) {
                            try {
                                System.out.print("Enter expiration date (yyyy-MM-dd): ");
                                String dateStr = scanner.nextLine();
                                expDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format!!! Please use this format: yyyy-MM-dd.");
                            }
                        }
                        item = new GroceryItem(id, name, quantity, price, supplier, expDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        itemCreated = true;
                        break;

                    case 4: // Furniture
                        System.out.print("Enter weight (kg): ");
                        double weight = getDoubleInput(scanner, 0.1);
                        item = new FurnitureItem(id, name, quantity, price, supplier, weight);

                        System.out.print("Is the item packed for delivery? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            ((FurnitureItem) item).packForDelivery();
                        }
                        itemCreated = true;
                        break;

                    case 5: // Perishable
                        LocalDate perishExpDate = null;
                        while (perishExpDate == null) {
                            try {
                                System.out.print("Enter expiration date (yyyy-MM-dd): ");
                                String dateStr = scanner.nextLine();
                                perishExpDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format!!! Please use this format: yyyy-MM-dd.");
                            }
                        }

                        System.out.print("Enter shelf life (days): ");
                        int shelfLife = getIntInput(scanner, 1, 365); // Max 1 year shelf life

                        item = new PerishableItem(id, name, quantity, price, supplier,
                                perishExpDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), shelfLife);
                        itemCreated = true;
                        break;
                }

                if (item != null && warehouse.addItem(item)) {
                    System.out.println("\nItem added successfully to warehouse:");
                    System.out.println(item.generateStockReport());
                } else if (item != null) {
                    System.out.println("Failed to add item: warehouse capacity exceeded");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try entering the item details again.");
                itemCreated = false;
            }
        }
    }
}
