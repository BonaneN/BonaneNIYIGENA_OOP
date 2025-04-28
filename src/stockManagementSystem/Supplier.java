package stockManagementSystem;

public class Supplier {
     String supplierId;
     String companyName;
     String contactPerson;
     String phone;
     String email;

    public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone format");
        }

        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
    }

    public String getSupplierId() { return supplierId; }
    public String getCompanyName() { return companyName; }
    public String getContactPerson() { return contactPerson; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setPhone(String phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone format");
        }
        this.phone = phone;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Supplier ID: %s\nCompany: %s\nContact: %s\nPhone: %s\nEmail: %s",
                supplierId, companyName, contactPerson, phone, email);
    }
}

