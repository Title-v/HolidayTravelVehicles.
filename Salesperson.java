import java.math.BigDecimal;
import java.util.Objects;

public class Salesperson {
    private final String salespersonID;
    private String name;
    private String phone;

    public Salesperson(String salespersonID, String name, String phone) {
        this.salespersonID = Objects.requireNonNull(salespersonID);
        this.name = Objects.requireNonNull(name);
        this.phone = Objects.requireNonNull(phone);
    }

    public void updateProfile(String newName, String newPhone) {
        if (newName != null && !newName.isBlank()) this.name = newName;
        if (newPhone != null && !newPhone.isBlank()) this.phone = newPhone;
    }

    public BigDecimal computeCommission(BigDecimal amount, BigDecimal rate) {
        if (amount == null || rate == null) return BigDecimal.ZERO;
        return amount.max(BigDecimal.ZERO).multiply(rate);
    }

    public String getSalespersonID() { return salespersonID; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
}
