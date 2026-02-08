# Interface Segregation Principle (ISP)

## Definition

The Interface Segregation Principle (ISP) states:

Clients should not be forced to depend on methods they do not use.

In simple terms, a class should only implement the behaviors it actually needs. Large, general-purpose interfaces that force classes to implement unnecessary methods lead to poor design, tight coupling, and unused or empty implementations.

---

## The Problem: Fat Interfaces

A common design mistake is creating one large interface that contains many unrelated responsibilities.

### Bad Design Example

```java
interface Worker {
    void work();
    void eat();
    void sleep();
    void salary();
    void drinkCoffee();
}
```

### Robot Implementation

```java
class Robot implements Worker {

    public void work() {
        System.out.println("Working...");
    }

    public void eat() { }
    public void sleep() { }
    public void salary() { }
    public void drinkCoffee() { }
}
```

---

## Why is this wrong?

The Robot:

- does not eat
- does not sleep
- does not receive a salary
- does not drink coffee

Yet it is forced to implement these methods.

This often results in:

- empty methods
- dummy implementations
- throwing UnsupportedOperationException

These are clear signs that the interface is too large and violates ISP.

---

## Why Fat Interfaces Are Harmful

### Tight Coupling
Classes depend on methods they do not need.

### Low Cohesion
The interface mixes unrelated responsibilities.

### Hard Maintenance
Adding one method forces all implementations to change.

### Code Smell
Empty or meaningless methods appear in many classes.

---

## Golden Rule

If you find yourself:

- writing empty methods
- throwing UnsupportedOperationException
- implementing methods that do not make sense

Your interface is probably too big.

---

## The Correct Approach: Split Interfaces

Instead of one large interface, create small, focused interfaces.

### Better Design

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}
```

---

## Usage

### Human

```java
class Human implements Workable, Eatable, Sleepable {

    public void work() {}
    public void eat() {}
    public void sleep() {}
}
```

### Robot

```java
class Robot implements Workable {

    public void work() {}
}
```

---

## Benefits

- Classes implement only what they need
- No unused or empty methods
- Lower coupling
- More flexible design
- Easier maintenance and testing

---

## Summary

The Interface Segregation Principle encourages designing small, cohesive, and focused interfaces. Prefer multiple small interfaces over one large interface to build cleaner and more maintainable systems.

---
