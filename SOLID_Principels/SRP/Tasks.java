//--------------------------------Task_1---------------------------

//-----This code violate (SRP)-----

public class PaymentProcessor {

    public void process(String userEmail, double amount, String cardNumber) {

        if (userEmail == null || !userEmail.contains("@") || amount <= 0) {
            throw new RuntimeException("Invalid data");
        }

        String maskedCard = cardNumber.substring(0, 4) + "****";

        double tax = amount * 0.14;
        double total = amount + tax;

        System.out.println("Charging card: " + maskedCard + " with " + total);

        System.out.println("Saving payment to database");

        System.out.println("Sending receipt to " + userEmail);

        System.out.println("Payment completed");
    }
}

/*

1.Validation rules

2.Security

3.Business rules (tax calculation)

4.Payment execution

5.Persistence

6.Notification

*/


//---------------------Solution--------------------

public class PaymentValidator {

    public void validate(String email, double amount) {
        if (email == null || !email.contains("@") || amount <= 0) {
            throw new RuntimeException("Invalid data");
        }
    }
}

public class CardMasker {

    public String mask(String cardNumber) {
        return cardNumber.substring(0, 4) + "****";
    }
}

public class TaxCalculator {

    public double calculate(double amount) {
        return amount * 0.14;
    }
}

public class PaymentGateway {

    public void charge(String card, double total) {
        System.out.println("Charging card: " + card + " with " + total);
    }
}

public class PaymentRepository {

    public void save() {
        System.out.println("Saving payment to database");
    }
}

public class ReceiptService {

    public void send(String email) {
        System.out.println("Sending receipt to " + email);
    }
}


//-------------------------------------------------------------------------------------------------


//----------------------Task_2------------------

//-----This code violate (SRP)-----

public class EcommerceSystem {

    public void checkout(String email, String password, double price, int quantity) {

        if (email == null || password.length() < 6 || quantity <= 0) {
            throw new RuntimeException();
        }

        if (!authenticate(email, password)) {
            throw new RuntimeException();
        }

        double total = calculatePrice(price, quantity);
        total += total * 0.14;

        saveOrder(email, total);

        sendEmail(email, total);
    }

    public boolean authenticate(String email, String password) {
        return password.equals("123456");
    }

    public double calculatePrice(double price, int quantity) {
        return price * quantity;
    }

    public void saveOrder(String email, double total) {
        System.out.println("Saved order for " + email + " with total " + total);
    }

    public void sendEmail(String email, double total) {
        System.out.println("Email sent to " + email + " with total " + total);
    }
}


//-----------------------Solution---------------------


public class AuthService {

    public boolean login(String email, String password) {
        validate(email, password);
        return checkCredentials(password);
    }

    private void validate(String email, String password) {
        if (email == null || password.length() < 6) {
            throw new RuntimeException();
        }
    }

    private boolean checkCredentials(String password) {
        return password.equals("123456");
    }
}

public class PricingService {

    public double calculateTotal(double price, int quantity) {
        validate(quantity);
        double subtotal = calculateSubtotal(price, quantity);
        return addTax(subtotal);
    }

    private void validate(int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException();
        }
    }

    private double calculateSubtotal(double price, int quantity) {
        return price * quantity;
    }

    private double addTax(double amount) {
        return amount + amount * 0.14;
    }
}

public class OrderService {

    public void placeOrder(String email, double total) {
        save(email, total);
        log(email, total);
    }

    private void save(String email, double total) {
        System.out.println("Saved order for " + email);
    }

    private void log(String email, double total) {
        System.out.println("Log order " + total);
    }
}

public class NotificationService {

    public void notifyUser(String email, double total) {
        sendEmail(email, buildMessage(total));
    }

    private String buildMessage(double total) {
        return "Total payment: " + total;
    }

    private void sendEmail(String email, String message) {
        System.out.println("Email sent to " + email + " : " + message);
    }
}
