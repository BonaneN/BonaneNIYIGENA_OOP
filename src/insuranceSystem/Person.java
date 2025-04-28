package insuranceSystem;
import java.time.LocalDate;

public class Person {
    private String personId;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;

    public Person(String personId, String fullName, LocalDate dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            this.email = email;
        }
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d{10,15}")) {
            this.phone = phone;
        }
    }

    public boolean validate() {
        return personId != null && !personId.isEmpty() && fullName != null && !fullName.isEmpty() && dob != null && dob.isBefore(LocalDate.now().minusYears(18)) && email != null && email.contains("@") && email.contains(".") && phone != null && phone.matches("\\d{10,15}");
    }
}
