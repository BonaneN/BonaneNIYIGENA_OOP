package insuranceSystem;
import java.time.LocalDate;

public class Vehicle {
    private String vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() { return vehicleId; }
    public String getVehicleMake() { return vehicleMake; }
    public String getVehicleModel() { return vehicleModel; }
    public int getVehicleYear() { return vehicleYear; }
    public String getVehicleType() { return vehicleType; }

    public void setVehicleYear(int vehicleYear) {
        if (vehicleYear > 1900 && vehicleYear <= LocalDate.now().getYear()) {
            this.vehicleYear = vehicleYear;
        }
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType.equalsIgnoreCase("private") || vehicleType.equalsIgnoreCase("commercial")) {
            this.vehicleType = vehicleType;
        }
    }

    public boolean validate() {
        return vehicleId != null && !vehicleId.isEmpty() && vehicleMake != null && !vehicleMake.isEmpty() && vehicleModel != null && !vehicleModel.isEmpty() && vehicleYear >= 1900 && vehicleYear <= LocalDate.now().getYear() && (vehicleType.equalsIgnoreCase("private") || vehicleType.equalsIgnoreCase("commercial"));
    }
}