import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class TradeInVehicle {
    private final String serialNumber;
    private String make;
    private String model;
    private int year;

    public TradeInVehicle(String serialNumber, String make, String model, int year) {
        this.serialNumber = Objects.requireNonNull(serialNumber);
        this.make = Objects.requireNonNull(make);
        this.model = Objects.requireNonNull(model);
        this.year = year;
    }

    public BigDecimal estimateValue() {
        int age = Math.max(0, LocalDate.now().getYear() - year);
        BigDecimal base = new BigDecimal("30000");
        BigDecimal depreciation = new BigDecimal("0.12");
        BigDecimal factor = BigDecimal.ONE.subtract(depreciation).pow(age);
        return base.multiply(factor).max(new BigDecimal("1000"));
    }

    public String displayInfo() {
        return "%s %s (%d) — SN:%s".formatted(make, model, year, serialNumber);
    }

    public String getSerialNumber() { return serialNumber; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}
