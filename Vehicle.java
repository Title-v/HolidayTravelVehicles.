import java.math.BigDecimal;
import java.util.Objects;

public class Vehicle {
    private final String serialNumber;
    private String name;
    private String model;
    private int year;
    private String manufacturer;
    private BigDecimal baseCost;
    private boolean sold = false;

    public Vehicle(String serialNumber, String name, String model, int year,
                   String manufacturer, BigDecimal baseCost) {
        this.serialNumber = Objects.requireNonNull(serialNumber);
        this.name = Objects.requireNonNull(name);
        this.model = Objects.requireNonNull(model);
        this.year = year;
        this.manufacturer = Objects.requireNonNull(manufacturer);
        this.baseCost = Objects.requireNonNull(baseCost);
    }

    public void markSold() { this.sold = true; }
    public void markInStock() { this.sold = false; }

    public String displayInfo() {
        return "%s %s %s (%d) — SN:%s".formatted(manufacturer, name, model, year, serialNumber);
    }

    public String getSerialNumber() { return serialNumber; }
    public String getName() { return name; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getManufacturer() { return manufacturer; }
    public BigDecimal getBaseCost() { return baseCost; }
    public boolean isSold() { return sold; }
}
