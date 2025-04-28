package insuranceSystem;
import java.time.LocalDate;

public class RoadsideAssistancePolicy extends InsurancePolicy {
    private boolean commercialVehicle;
    private boolean registrationVerified;

    public RoadsideAssistancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate, boolean commercialVehicle, boolean registrationVerified) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.commercialVehicle = commercialVehicle;
        this.registrationVerified = registrationVerified;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = commercialVehicle ? 200 : 100;
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
        return "Roadside Assistance Policy Report\n" + display() + "\nCoverage: Emergency Assistance\n" + "Commercial Vehicle: " + (commercialVehicle ? "Yes" : "No") + "\n" + "Registration Verified: " + (registrationVerified ? "Yes" : "No");
    }

    @Override
    public boolean validatePolicy() {
        return registrationVerified;
    }
}

