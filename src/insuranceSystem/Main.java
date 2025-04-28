package insuranceSystem;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private List<InsurancePolicy> policies = new ArrayList<>();
    private List<Claim> claims = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main system = new Main();
        system.run();
    }

    public void run() {
        while (true) {
            System.out.println("\nMotor Vehicle Insurance System");
            System.out.println("1. Create New Policy");
            System.out.println("2. Process Claim");
            System.out.println("3. Generate Reports");
            System.out.println("4. Exit");

            int choice = getValidIntInput("Select option (1-4): ", 1, 4);

            switch (choice) {
                case 1:
                    createPolicy();
                    break;
                case 2:
                    processClaim();
                    break;
                case 3:
                    generateReports();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
            }
        }
    }

    private void createPolicy() {
        System.out.println("\nCreate New Insurance Policy");
        System.out.println("1. Comprehensive Policy");
        System.out.println("2. Third Party Policy");
        System.out.println("3. Collision Policy");
        System.out.println("4. Liability Policy");
        System.out.println("5. Roadside Assistance Policy");

        int policyType = getValidIntInput("Select policy type: ", 1, 5);

        String policyId = getValidStringInput("Enter policy ID: ", "^[a-zA-Z0-9]{3,20}$");

        System.out.println("\nEnter Vehicle Details:");
        String vehicleId = getValidStringInput("Vehicle ID: ", "^[a-zA-Z0-9]{1,10}$");
        String make = getValidStringInput("Make: ", "^[a-zA-Z\\s]{3,30}$");
        String model = getValidStringInput("Model: ", "^[a-zA-Z0-9\\s]{3,10}$");
        int year = getValidIntInput("Year (1900-" + LocalDate.now().getYear() + "): ", 1900, LocalDate.now().getYear());
        String type = getValidChoiceInput("Type (private/commercial): ", new String[]{"private", "commercial"});

        Vehicle vehicle = new Vehicle(vehicleId, make, model, year, type);

        System.out.println("\nEnter Policy Holder Details:");
        String personId = getValidStringInput("Person ID: ", "^[a-zA-Z0-9]{3,20}$");
        String fullName = getValidStringInput("Full Name: ", "^[a-zA-Z\\s]{3,50}$");

        LocalDate dob;
        while (true) {
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
            try {
                dob = LocalDate.parse(input);
                Period age = Period.between(dob, LocalDate.now());

                if (age.getYears() < 18) {
                    System.out.println("Error: Policy holder must be at least 18 years old.");
                    System.out.println("Returning to main menu...");
                    return; // Exit back to main menu
                }

                // If we get here, age is valid
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        String email = getValidStringInput("Email: ", "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        String phone = getValidStringInput("Phone (10-15 digits): ", "^\\d{10,15}$");

        Person person = new Person(personId, fullName, dob, email, phone);

        double coverageAmount = getValidDoubleInput("Coverage Amount: ", 100, Double.MAX_VALUE);
        LocalDate startDate = getValidDateInput("Policy Start Date (YYYY-MM-DD): ", LocalDate.now(), null);
        LocalDate endDate = getValidDateInput("Policy End Date (YYYY-MM-DD, must be after start date): ", startDate.plusDays(1), null);

        InsurancePolicy policy = createSpecificPolicy(policyType, policyId, vehicle, person,
                coverageAmount, startDate, endDate);

        if (policy != null) {
            policy.calculatePremium();
            if (policy.validatePolicy()) {
                policies.add(policy);
                System.out.println("\nPolicy created successfully!");
                System.out.println(policy.generatePolicyReport());
            } else {
                System.out.println("Policy validation failed. Please check the details.");
            }
        }
    }

    private InsurancePolicy createSpecificPolicy(int policyType, String policyId, Vehicle vehicle, Person person, double coverageAmount, LocalDate startDate, LocalDate endDate) {
        switch (policyType) {
            case 1:
                return new ComprehensivePolicy(policyId, vehicle, person, coverageAmount, startDate, endDate);
            case 2:
                double engineCapacity = getValidDoubleInput("Engine Capacity (cc, minimum 50): ", 50, Double.MAX_VALUE);
                boolean extendedCoverage = getValidBooleanInput("Extended Coverage (true/false): ");
                return new ThirdPartyPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, engineCapacity, extendedCoverage);
            case 3:
                boolean safeDriver = getValidBooleanInput("Safe Driver Discount (true/false): ");
                boolean safetyCheck = getValidBooleanInput("Safety Check Passed (true/false): ");
                return new CollisionPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, safeDriver, safetyCheck);
            case 4:
                boolean medicalCheck = getValidBooleanInput("Medical Checkup Done (true/false): ");
                boolean disabilityCoverage = getValidBooleanInput("Extended Disability Coverage (true/false): ");
                return new LiabilityPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, medicalCheck, disabilityCoverage);
            case 5:
                boolean commercial = getValidBooleanInput("Commercial Vehicle (true/false): ");
                boolean registrationVerified = getValidBooleanInput("Registration Verified (true/false): ");
                return new RoadsideAssistancePolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, commercial, registrationVerified);
            default:
                return null;
        }
    }

    private void processClaim() {
        if (policies.isEmpty()) {
            System.out.println("No policies available to process claims.");
            return;
        }

        System.out.println("\nProcess Claim");
        String policyId = getValidStringInput("Enter Policy ID: ", ".+");

        InsurancePolicy policy = findPolicy(policyId);
        if (policy == null) {
            System.out.println("Policy not found.");
            return;
        }

        String claimId = getValidStringInput("Enter Claim ID: ", ".+");
        double claimAmount = getValidDoubleInput("Enter Claim Amount: ", 0.01, policy.getCoverageAmount());
        LocalDate claimDate = getValidDateInput("Enter Claim Date (YYYY-MM-DD): ", policy.getPolicyStartDate(), LocalDate.now());

        Claim claim = new Claim(claimId, claimAmount, claimDate, "Pending", policy);
        if (policy.processClaim(claimAmount)) {
            claim.setClaimStatus("Approved");
            claims.add(claim);
            System.out.println("Claim processed successfully!");
        } else {
            claim.setClaimStatus("Rejected");
            System.out.println("Claim could not be processed.");
        }
    }

    private void generateReports() {
        System.out.println("\nGenerate Reports");
        System.out.println("1. Policy Details");
        System.out.println("2. Claims Summary");
        System.out.println("3. Financial Summary");

        int reportType = getValidIntInput("Select report type: ", 1, 3);

        switch (reportType) {
            case 1:
                String policyId = getValidStringInput("Enter Policy ID: ", ".+");
                InsurancePolicy policy = findPolicy(policyId);
                if (policy != null) {
                    System.out.println("\n" + policy.generatePolicyReport());
                } else {
                    System.out.println("Policy not found.");
                }
                break;
            case 2:
                System.out.println("\nClaims Summary");
                System.out.println("Total Claims: " + claims.size());
                long approved = claims.stream().filter(c -> c.getClaimStatus().equals("Approved")).count();
                long pending = claims.stream().filter(c -> c.getClaimStatus().equals("Pending")).count();
                long rejected = claims.stream().filter(c -> c.getClaimStatus().equals("Rejected")).count();
                System.out.println("Approved: " + approved);
                System.out.println("Pending: " + pending);
                System.out.println("Rejected: " + rejected);
                break;
            case 3:
                double totalPremiums = policies.stream().mapToDouble(InsurancePolicy::getPremiumAmount).sum();
                double totalClaims = claims.stream().filter(c -> c.getClaimStatus().equals("Approved")).mapToDouble(Claim::getClaimAmount).sum();
                System.out.println("\nFinancial Summary");
                System.out.printf("Total Premiums Collected: $%.2f\n", totalPremiums);
                System.out.printf("Total Claims Paid: $%.2f\n", totalClaims);
                System.out.printf("Net Profit/Loss: $%.2f\n", (totalPremiums - totalClaims));
                break;
        }
    }

    private InsurancePolicy findPolicy(String policyId) {
        return policies.stream().filter(p -> p.getPolicyId().equals(policyId)).findFirst().orElse(null);
    }

    private String getValidStringInput(String prompt, String regex) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println("Invalid input!!! Enter 3 to 20 characters");
        }
    }

    private int getValidIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Input must be between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double getValidDoubleInput(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Input must be between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private LocalDate getValidDateInput(String prompt, LocalDate minDate, LocalDate maxDate) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                LocalDate date = LocalDate.parse(input);
                if (minDate != null && date.isBefore(minDate)) {
                    System.out.println("Date cannot be before " + minDate);
                    continue;
                }
                if (maxDate != null && date.isAfter(maxDate)) {
                    System.out.println("Date cannot be after " + maxDate);
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private boolean getValidBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter exactly 'true' or 'false'.");
        }
    }

    private String getValidChoiceInput(String prompt, String[] validChoices) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            for (String choice : validChoices) {
                if (input.equals(choice.toLowerCase())) {
                    return choice;
                }
            }
            System.out.println("Invalid choice. Please enter one of: " + String.join(", ", validChoices));
        }
    }
}