package insuranceSystem;
import java.time.LocalDate;

public class ComprehensivePolicy extends InsurancePolicy {

    public ComprehensivePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
    }

    @Override
    public void calculatePremium() {
        int vehicleAge = LocalDate.now().getYear() - vehicle.getVehicleYear();
        double basePremium = coverageAmount * 0.05;

        if (vehicleAge > 10) {
            premiumAmount = basePremium * 1.5;
        } else if (vehicleAge > 5) {
            premiumAmount = basePremium * 1.2;
        } else {
            premiumAmount = basePremium;
        }
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (claimAmount <= coverageAmount) {
            coverageAmount -= claimAmount;
            return true;
        }
        return false;
    }

    @Override
    public String generatePolicyReport() {
        return "Comprehensive Policy Report\n" + display() + "\nCoverage: Damage and Theft\n" + "Vehicle Age: " + (LocalDate.now().getYear() - vehicle.getVehicleYear()) + " years";
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.getVehicleYear() >= 1980 && !vehicle.getVehicleType().equalsIgnoreCase("commercial");
    }
}