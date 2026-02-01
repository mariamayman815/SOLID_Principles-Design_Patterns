# Open / Closed Principle (OCP)

## 1) Definition

The **Open/Closed Principle (OCP)** states:

> Software entities should be open for extension, but closed for modification.

This means:

* Open for extension → you can add new behavior
* Closed for modification → you should not change existing, tested code

In simple words:

When adding a new feature, you should add new code instead of editing old code.

---

## 2) Why modifying existing code is risky

Existing code is usually:

* Already tested
* Already working
* Possibly used in many places

Changing it may:

* Introduce bugs
* Break stable features
* Increase maintenance cost
* Make the system fragile

So the safer approach is to keep old code stable and extend behavior using new classes.

---

## 3) Modification vs Extension

### Modification (Bad)

* Edit the same class repeatedly
* Add more if/else or switch logic
* Keep changing old code

### Extension (Good)

* Add new classes
* Add new implementations
* Leave existing code untouched

---

## 4) Bad Example (Violates OCP)

```java
class Payment {
    public void pay(String type) {
        if (type.equals("cash")) {
            System.out.println("cash payment");
        } else if (type.equals("card")) {
            System.out.println("card payment");
        }
    }
}
```

### Problem

If we want to add:

* PayPal
* Apple Pay
* Vodafone Cash

We must edit the same class and add more conditions each time.

This means the class is open for modification and violates OCP.

---

## 5) Good Example (Applies OCP)

Use abstraction and polymorphism.

### Step 1 – Create an interface

```java
interface PaymentMethod {
    void pay();
}
```

### Step 2 – Create one class per type

```java
class CashPayment implements PaymentMethod {
    public void pay() {
        System.out.println("cash payment");
    }
}

class CardPayment implements PaymentMethod {
    public void pay() {
        System.out.println("card payment");
    }
}
```

### Usage

```java
PaymentMethod payment = new CashPayment();
payment.pay();
```

---

## 6) Adding a New Feature (Extension Only)

```java
class PaypalPayment implements PaymentMethod {
    public void pay() {
        System.out.println("paypal payment");
    }
}
```

What changed?

* No edits to old classes
* Only new code added
* Existing behavior remains stable

This is exactly what OCP recommends.

---

## 7) The Golden Rule of OCP

If you keep adding:

* if / else
* switch
* type checks

whenever new behavior appears, your design likely violates OCP.

Prefer using:

* Interfaces
* Inheritance
* Polymorphism
* Strategy Pattern
* Dependency Injection

---

## 8) Relation to SOLID and Design Patterns

OCP is one of the SOLID principles and is commonly implemented using design patterns such as:

* Strategy Pattern
* Factory Pattern
* Decorator Pattern
* Command Pattern

Design patterns help extend behavior without modifying existing code.

---

## 9) Summary

* Open for extension
* Closed for modification
* Add new behavior without touching old code
* Prefer abstraction and polymorphism
* Avoid large conditional logic

If SRP separates responsibilities, OCP protects stable code from risky changes.

---

