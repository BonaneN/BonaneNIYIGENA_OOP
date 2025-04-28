package stockManagementSystem;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Warehouse {
    private String warehouseId;
    private String location;
    private int capacity; // in items
    private String managerName;
    private List<StockItem> inventory;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.capacity = capacity;
        this.managerName = managerName;
        this.inventory = new ArrayList<>();
    }

    public boolean addItem(StockItem item) {
        if (getCurrentStockCount() + item.getQuantityInStock() <= capacity) {
            inventory.add(item);
            return true;
        }
        return false;
    }

    public boolean removeItem(String itemId) {
        return inventory.removeIf(item -> item.getItemId().equals(itemId));
    }

    public int getCurrentStockCount() {
        return inventory.stream().mapToInt(StockItem::getQuantityInStock).sum();
    }

    public double getTotalStockValue() {
        return inventory.stream().mapToDouble(StockItem::calculateStockValue).sum();
    }

    public String generateInventoryReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Warehouse ID: %s\nLocation: %s\nManager: %s\nCapacity: %d/%d\nTotal Stock Value: $%.2f\n\n", warehouseId, location, managerName, getCurrentStockCount(), capacity, getTotalStockValue()));

        report.append("Inventory Items:\n");
        for (StockItem item : inventory) {
            report.append(item.generateStockReport()).append("\n\n");
        }

        report.append("Items Needing Attention:\n");
        for (StockItem item : inventory) {
            if (item instanceof GroceryItem) {
                GroceryItem grocery = (GroceryItem) item;
                if (grocery.isNearExpiration()) {
                    report.append(String.format("- %s (Expires: %s)\n",
                            grocery.getItemName(),
                            grocery.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
                }
            } else if (item instanceof PerishableItem) {
                PerishableItem perishable = (PerishableItem) item;
                if (perishable.isAboutToExpire()) {
                    report.append(String.format("- %s (Expires: %s)\n",
                            perishable.getItemName(),
                            perishable.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
                }
            }
        }

        return report.toString();
    }

    public String getWarehouseId() { return warehouseId; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getManagerName() { return managerName; }
    public List<StockItem> getInventory() { return inventory; }
}