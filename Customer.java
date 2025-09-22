import java.util.Objects;

public class Customer {
    private final String customerID;
    private String name;
    private String address;
    private String phone;

    public Customer(String customerID, String name, String address, String phone) {
        this.customerID = Objects.requireNonNull(customerID);
        this.name = Objects.requireNonNull(name);
        this.address = Objects.requireNonNull(address);
        this.phone = Objects.requireNonNull(phone);
    }

    public void updateContact(String newAddress, String newPhone) {
        if (newAddress != null && !newAddress.isBlank()) this.address = newAddress;
        if (newPhone != null && !newPhone.isBlank()) this.phone = newPhone;
    }

    public String getInfo() {
        return "%s (%s) — %s".formatted(name, phone, address);
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}
