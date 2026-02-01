// ---------------------------Task_1--------------------------------
// ----------------------This code violate OCP----------------------

class FineCalculator {

    public double calculate(String type, int days) {

        if(type.equals("regular"))
            return days * 1;

        else if(type.equals("vip"))
            return days * 0.5;

        else if(type.equals("staff"))
            return 0;

        return 0;
    }
}

// -------------------------Solution--------------------------

public interface finePolicy{
    double calculateFine(int days);
}
// REGULAR
public class RegularFine implements finePolicy{
    double calculateFine(int days){
        return days*1;
    }
}
//VIP
public class VipFine implements FinePolicy {
    public double calculateFine(int days) {
        return days * 0.5;
    }
}
//STAFF
public class StaffFine implements FinePolicy {
    public double calculateFine(int days) {
        return 0;
    }
}
// using??  FinePolicy policy = new RegularFine(); // polymorphism
// what about add a new type ? simply implements inteface no modification just Extention

// ---------------------------------------------------------------------------------------------

// -----------------------------Task_2----------------------------

class Order {

    private double price;
    private String paymentType;
    private String customerType;
    private String shippingType;

    public Order(double price, String paymentType, String customerType, String shippingType) {
        this.price = price;
        this.paymentType = paymentType;
        this.customerType = customerType;
        this.shippingType = shippingType;
    }

    public double calculateDiscount() {

        if(customerType.equals("regular"))
            return price * 0.05;

        else if(customerType.equals("vip"))
            return price * 0.20;

        else if(customerType.equals("employee"))
            return price * 0.50;

        return 0;
    }

    public double calculateShipping() {

        if(shippingType.equals("standard"))
            return 20;

        else if(shippingType.equals("express"))
            return 50;

        return 0;
    }

    public void processPayment(double amount) {

        if(paymentType.equals("cash"))
            System.out.println("Cash paid: " + amount);

        else if(paymentType.equals("card"))
            System.out.println("Card paid: " + amount);

        else if(paymentType.equals("paypal"))
            System.out.println("Paypal paid: " + amount);
    }

    public void checkout() {

        double discount = calculateDiscount();
        double shipping = calculateShipping();
        double finalPrice = price - discount + shipping;

        processPayment(finalPrice);
    }
}

// --------------------------------Solution------------------------------

// Order → DiscountStrategy
//       → PaymentStrategy
//       → ShippingStrategy

// this instead of 
//Order  → if if if

//Discount Strategy
interface DiscountStrategy {
    double applyDiscount(double price);
}
class RegularDiscount implements DiscountStrategy {
    public double applyDiscount(double price) {
        return price * 0.05;
    }
}

class VipDiscount implements DiscountStrategy {
    public double applyDiscount(double price) {
        return price * 0.20;
    }
}

//Payment Strategy
interface PaymentStrategy {
    void pay(double amount);
}
class CashPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Cash paid: " + amount);
    }
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Card paid: " + amount);
    }
}

//Shipping Strategy
interface ShippingStrategy {
    double calculateShipping();
}
class StandardShipping implements ShippingStrategy {
    public double calculateShipping() {
        return 20;
    }
}

class ExpressShipping implements ShippingStrategy {
    public double calculateShipping() {
        return 50;
    }
}

//Order
class Order {

    private double price;
    private DiscountStrategy discountStrategy;
    private PaymentStrategy paymentStrategy;
    private ShippingStrategy shippingStrategy;

    public Order(double price,
        DiscountStrategy discountStrategy,
        PaymentStrategy paymentStrategy,
        ShippingStrategy shippingStrategy) {

        this.price = price;
        this.discountStrategy = discountStrategy;
        this.paymentStrategy = paymentStrategy;
        this.shippingStrategy = shippingStrategy;
    }

    public void checkout() {

        double discount = discountStrategy.applyDiscount(price);
        double shipping = shippingStrategy.calculateShipping();

        double finalPrice = price - discount + shipping;

        paymentStrategy.pay(finalPrice);
    }
}

/* USAGE??  Order order = new Order(
        1000,
        new VipDiscount(),
        new CardPayment(),
        new ExpressShipping()
);
order.checkout();
*/

// this method of solution acheived by using strategy pattern



