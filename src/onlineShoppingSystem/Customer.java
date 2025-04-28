package onlineShoppingSystem;

public class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        if (!customerName.matches("[a-zA-Z ]+")) throw new IllegalArgumentException("Invalid name");
        if (!phone.matches("[0-9]+")) throw new IllegalArgumentException("Invalid phone number");
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (address.isEmpty()) throw new IllegalArgumentException("Invalid address");

        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}

