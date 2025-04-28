package insuranceSystem;
import java.time.LocalDate;

public abstract class InsurancePolicy {
    String policyId;
    Vehicle vehicle;
    Person policyHolder;
    double coverageAmount;
    double premiumAmount;
    LocalDate policyStartDate;
    LocalDate policyEndDate;

    public InsurancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = policyId;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    public abstract void calculatePremium();
    public abstract boolean processClaim(double claimAmount);
    public abstract String generatePolicyReport();
    public abstract boolean validatePolicy();

    public String getPolicyId() { return policyId; }
    public Vehicle getVehicle() { return vehicle; }
    public Person getPolicyHolder() { return policyHolder; }
    public double getCoverageAmount() { return coverageAmount; }
    public double getPremiumAmount() { return premiumAmount; }
    public LocalDate getPolicyStartDate() { return policyStartDate; }
    public LocalDate getPolicyEndDate() { return policyEndDate; }

    public void setCoverageAmount(double coverageAmount) { this.coverageAmount = coverageAmount; }
    public void setPremiumAmount(double premiumAmount) { this.premiumAmount = premiumAmount; }
    public void setPolicyEndDate(LocalDate policyEndDate) { this.policyEndDate = policyEndDate; }

    public String display() {
        return "Policy ID: " + policyId + "\n" + "Vehicle: " + vehicle.getVehicleMake() + " " + vehicle.getVehicleModel() + " (" + vehicle.getVehicleYear() + ")\n" + "Policy Holder: " + policyHolder.getFullName() + "\n" + "Coverage Amount: $" + coverageAmount + "\n" + "Premium Amount: $" + premiumAmount + "\n" + "Policy Period: " + policyStartDate + " to " + policyEndDate;
    }
}