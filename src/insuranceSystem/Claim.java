package insuranceSystem;
import java.time.LocalDate;


public class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    private String claimStatus;
    private InsurancePolicy policy;

    public Claim(String claimId, double claimAmount, LocalDate claimDate, String claimStatus, InsurancePolicy policy) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
        this.policy = policy;
    }

    public String getClaimId() { return claimId; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getClaimStatus() { return claimStatus; }
    public InsurancePolicy getPolicy() { return policy; }

    public void setClaimStatus(String claimStatus) {
        if (claimStatus.equals("Pending") || claimStatus.equals("Approved") || claimStatus.equals("Rejected") || claimStatus.equals("Paid")) {
            this.claimStatus = claimStatus;
        }
    }

    public boolean validate() {
        return claimId != null && !claimId.isEmpty() && claimAmount > 0 && claimAmount <= policy.getCoverageAmount() && claimDate != null && (claimStatus.equals("Pending") || claimStatus.equals("Approved") || claimStatus.equals("Rejected") || claimStatus.equals("Paid"));
    }
}
