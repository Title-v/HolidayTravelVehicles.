import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Invoice {
    private final String invoiceID;
    private final LocalDate date;
    private Customer customer;
    private Salesperson salesperson;
    private Vehicle vehicle;
    private BigDecimal negotiatedPrice;
    private BigDecimal taxes;
    private BigDecimal licenseFees;
    private BigDecimal totalPrice;
    private TradeInVehicle tradeIn;
    private BigDecimal tradeInAllowance = BigDecimal.ZERO;
    private final Map<String, Option> options = new LinkedHashMap<>();

    public Invoice(String invoiceID, LocalDate date,
                   Customer customer, Salesperson salesperson, Vehicle vehicle,
                   BigDecimal negotiatedPrice) {
        this.invoiceID = Objects.requireNonNull(invoiceID);
        this.date = Objects.requireNonNull(date);
        this.customer = Objects.requireNonNull(customer);
        this.salesperson = Objects.requireNonNull(salesperson);
        this.vehicle = Objects.requireNonNull(vehicle);
        this.negotiatedPrice = Objects.requireNonNull(negotiatedPrice);
        this.taxes = BigDecimal.ZERO;
        this.licenseFees = BigDecimal.ZERO;
        this.totalPrice = negotiatedPrice;
    }

    public void addOption(Option option) {
        Objects.requireNonNull(option);
        options.put(option.getOptionCode(), option);
    }

    public void removeOption(String optionCode) {
        options.remove(optionCode);
    }

    public void setTradeIn(TradeInVehicle tradeIn, BigDecimal allowance) {
        this.tradeIn = Objects.requireNonNull(tradeIn);
        this.tradeInAllowance = (allowance == null) ? BigDecimal.ZERO : allowance.max(BigDecimal.ZERO);
    }

    public void clearTradeIn() {
        this.tradeIn = null;
        this.tradeInAllowance = BigDecimal.ZERO;
    }

    public void setTaxAndFees(BigDecimal taxes, BigDecimal licenseFees) {
        this.taxes = (taxes == null) ? BigDecimal.ZERO : taxes.max(BigDecimal.ZERO);
        this.licenseFees = (licenseFees == null) ? BigDecimal.ZERO : licenseFees.max(BigDecimal.ZERO);
    }

    public BigDecimal calculateTotal() {
        BigDecimal optionSum = options.values().stream()
                .map(Option::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalPrice = negotiatedPrice
                .add(optionSum)
                .add(taxes)
                .add(licenseFees)
                .subtract(tradeInAllowance);

        return totalPrice;
    }

    public void captureSignature() {
        vehicle.markSold();
    }

    public String getInvoiceID() { return invoiceID; }
    public LocalDate getDate() { return date; }
    public Customer getCustomer() { return customer; }
    public Salesperson getSalesperson() { return salesperson; }
    public Vehicle getVehicle() { return vehicle; }
    public Optional<TradeInVehicle> getTradeIn() { return Optional.ofNullable(tradeIn); }
    public BigDecimal getNegotiatedPrice() { return negotiatedPrice; }
    public BigDecimal getTaxes() { return taxes; }
    public BigDecimal getLicenseFees() { return licenseFees; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public Collection<Option> getOptions() { return Collections.unmodifiableCollection(options.values()); }
}
