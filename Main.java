import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer c = new Customer("C001", "Alice Smith", "123 Main St", "555-1111");
        Salesperson s = new Salesperson("S001", "Bob Johnson", "555-2222");
        Vehicle v = new Vehicle("V100", "Adventurer", "XLT", 2025, "Winnebago", new BigDecimal("120000"));
        Option opt1 = new Option("O001", "Solar Panel Kit", new BigDecimal("3000"));
        Option opt2 = new Option("O002", "Luxury Awning", new BigDecimal("1500"));
        TradeInVehicle trade = new TradeInVehicle("T001", "Toyota", "Highlander", 2018);

        Invoice inv = new Invoice("INV001", LocalDate.now(), c, s, v, new BigDecimal("115000"));
        inv.addOption(opt1);
        inv.addOption(opt2);
        inv.setTradeIn(trade, new BigDecimal("12000"));
        inv.setTaxAndFees(new BigDecimal("7000"), new BigDecimal("500"));
        BigDecimal total = inv.calculateTotal();

        System.out.println("Customer: " + c.getInfo());
        System.out.println("Salesperson: " + s.getName());
        System.out.println("Vehicle: " + v.displayInfo());
        System.out.println("Trade-in: " + trade.displayInfo() + " Value=" + trade.estimateValue());
        System.out.println("Options: ");
        for (Option o : inv.getOptions()) {
            System.out.println("  - " + o.getDetail());
        }
        System.out.println("Final Price: " + total);
        inv.captureSignature();
        System.out.println("Vehicle Sold? " + v.isSold());
    }
}
