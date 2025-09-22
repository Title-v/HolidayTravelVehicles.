import java.math.BigDecimal;
import java.util.Objects;

public class Option {
    private final String optionCode;
    private String description;
    private BigDecimal price;

    public Option(String optionCode, String description, BigDecimal price) {
        this.optionCode = Objects.requireNonNull(optionCode);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
    }

    public void changePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) return;
        this.price = newPrice;
    }

    public String getDetail() {
        return "%s: %s (%s)".formatted(optionCode, description, price.toPlainString());
    }

    public String getOptionCode() { return optionCode; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
}
