package insuranceSystem;
import java.time.LocalDate;

public class CollisionPolicy extends InsurancePolicy {
    private boolean safeDriverDiscount;
    private boolean safetyCheckPassed;

    public CollisionPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate, boolean safeDriverDiscount, boolean safetyCheckPassed) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.safeDriverDiscount = safeDriverDiscount;
        this.safetyCheckPassed = safetyCheckPassed;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * 0.04;
        if (safeDriverDiscount) {
            premiumAmount *= 0.9; // 10% discount
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
        return "Collision Policy Report\n" + display() + "\nCoverage: Collision Damage\n" + "Safe Driver Discount: " + (safeDriverDiscount ? "Yes" : "No") + "\n" + "Safety Check Passed: " + (safetyCheckPassed ? "Yes" : "No");
    }

    @Override
    public boolean validatePolicy() {
        return safetyCheckPassed;
    }
}