# Dependency Inversion Principle (DIP)

## Definition

The **Dependency Inversion Principle (DIP)** states:

> High-level modules should not depend on low-level modules.  
> Both should depend on abstractions.  
> Abstractions should not depend on details. Details should depend on abstractions.

---

## Intuition

High-level classes contain the core business logic of the application,  
while low-level classes handle implementation details.

If high-level classes directly depend on low-level classes, the system becomes tightly coupled, hard to modify, and difficult to test.

Instead, both should depend on **interfaces (abstractions)**.

  Always depend on abstractions, not concrete implementations.

---

#   Bad Design (Violation of DIP)

### Direct dependency on a concrete class

```java
class Lamp {
    void turnOn() {
        System.out.println("Lamp ON");
    }
}

class Switch {
    Lamp lamp = new Lamp();   // Direct dependency

    void press() {
        lamp.turnOn();
    }
}
```

## Problems

The Switch:

- depends directly on Lamp  
- cannot work with any other device  
- must be modified when we change the device type  

This creates:

- Tight coupling  
- Poor flexibility  
- Hard maintenance  
- Difficult testing  

  This violates the Dependency Inversion Principle.

---

#   Good Design (Applying DIP)

## Step 1 — Create an abstraction

```java
interface Device {
    void turnOn();
}
```

## Step 2 — Implement the abstraction

```java
class Lamp implements Device {
    public void turnOn() {
        System.out.println("Lamp ON");
    }
}

class Fan implements Device {
    public void turnOn() {
        System.out.println("Fan ON");
    }
}
```

## Step 3 — Depend on the abstraction

```java
class Switch {

    private Device device;

    Switch(Device device) {
        this.device = device;
    }

    void press() {
        device.turnOn();
    }
}
```

## Usage

```java
Switch s1 = new Switch(new Lamp());
s1.press();

Switch s2 = new Switch(new Fan());
s2.press();
```

---

# Why This Design Is Better

Now the Switch:

- does not know the concrete device  
- depends only on the interface  
- works with any device  
- does not need modification when adding new devices  

Benefits:

- Loose coupling  
- High flexibility  
- Easier testing  
- Better maintainability  
- Extensible design  

✔ This fully follows the Dependency Inversion Principle.

---

# Golden Rule

> Depend on abstractions, not concretions.

---

# Warning Signs of DIP Violation

If you see:

- direct use of new ConcreteClass() inside high-level classes  
- tight coupling between modules  
- difficulty replacing implementations  
- hard unit testing  

  Your design likely violates DIP.

---

# Summary

- High-level modules → depend on interfaces  
- Low-level modules → implement interfaces  
- Never couple business logic to implementation details  

---
