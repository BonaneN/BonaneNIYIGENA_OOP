package insuranceSystem;
import java.time.LocalDate;

public class LiabilityPolicy extends InsurancePolicy {
    private boolean medicalCheckupDone;
    private boolean extendedDisabilityCoverage;

    public LiabilityPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount, LocalDate policyStartDate, LocalDate policyEndDate, boolean medicalCheckupDone, boolean extendedDisabilityCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.medicalCheckupDone = medicalCheckupDone;
        this.extendedDisabilityCoverage = extendedDisabilityCoverage;
    }

    @Override
    public void calculatePremium() {
        premiumAmount = coverageAmount * 0.03;
        if (extendedDisabilityCoverage) {
            premiumAmount *= 1.15;
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
        return "Liability Policy Report\n" + display() + "\nCoverage: Third Party Liability\n" + "Medical Checkup Done: " + (medicalCheckupDone ? "Yes" : "No") + "\n" + "Extended Disability Coverage: " + (extendedDisabilityCoverage ? "Yes" : "No");
    }

    @Override
    public boolean validatePolicy() {
        return medicalCheckupDone;
    }
}
