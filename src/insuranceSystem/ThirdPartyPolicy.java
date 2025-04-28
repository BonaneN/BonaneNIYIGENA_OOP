package insuranceSystem;
import java.time.LocalDate;

public class ThirdPartyPolicy extends InsurancePolicy {
    private double engineCapacity;
    private boolean extendedCoverage;

    public ThirdPartyPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate, double engineCapacity, boolean extendedCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.engineCapacity = engineCapacity;
        this.extendedCoverage = extendedCoverage;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = engineCapacity * 10;
        if (extendedCoverage) {
            premiumAmount *= 1.2;
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
        return "Third Party Policy Report\n" + display() + "\nCoverage: Third Party Liability\n" + "Engine Capacity: " + engineCapacity + "cc\n" + "Extended Coverage: " + (extendedCoverage ? "Yes" : "No");
    }

    @Override
    public boolean validatePolicy() {
        return engineCapacity > 0;
    }
}