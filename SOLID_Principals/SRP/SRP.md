# Single Responsibility Principle (SRP) 

## 1) Definition

The **Single Responsibility Principle (SRP)** states that each class or module in the code should be responsible for **one and only one task**, and therefore should have **only one reason to change**.

In simpler terms:

* If a class or module does more than one thing, it has multiple reasons to change, which violates SRP.

**Official Definition:**

> "A class should have only one reason to change."

### ❓ Module vs Class

* A **Class** is a construct in OOP languages (like Java) that combines data and methods together.

```java
class Car {
    // data + methods
}
```

* A **Module** is a broader concept, meaning an independent part of the program that handles a group of related functions. A module can be:

  * A single class
  * Multiple classes
  * Even an entire file

* Simply put:

  * A **Module** can contain multiple classes
  * A **Class** is just a single unit of code

## 2) What does "one responsibility" mean?

A "responsibility" in SRP is not just a single method or function, but a reason that might cause the class to change.

* If a class does two different things and each can change independently, it violates SRP.

**Example:**

* A class calculates salaries and prints reports.

  * Calculating salaries may change if the formula changes.
  * Printing the report may change if the format changes.
* Two different reasons to change → Anti-SRP.

## 3) Why use SRP?

When code is divided into small units with one responsibility each:

* Maintenance becomes easier
* Testing becomes simpler
* Changes in one part of the system don’t affect everything else
* Code reusability improves

This means each unit works independently instead of being coupled with multiple concerns.

## 4) Practical Example in Java

**Bad Example (without SRP):**

```java
class Employee {
    public void calculatePay() { /* salary calculation */ }
    public void saveToDatabase() { /* save data */ }
    public void generateReport() { /* generate report */ }
}
```

* `Employee` class has three responsibilities: calculate pay, save data, generate report.
* Changing database logic or report format requires modifying this class → violates SRP.

**Good Example (with SRP):**

```java
class PayrollCalculator {
    public void calculatePay() { /* salary calculation */ }
}

class EmployeeRepository {
    public void save(Employee e) { /* save data */ }
}

class ReportGenerator {
    public void generate(Employee e) { /* generate report */ }
}
```

* Each class has **one responsibility**
* Changes in one concern only affect its own class
* No class changes for more than one reason → SRP compliant

## 5) Responsibility vs Single Method

* SRP does **not** mean each class should have only one method.
* All methods in a class can exist as long as they contribute to the **same single responsibility**.
* Methods that serve different purposes should be in separate classes.

## 6) SRP, Modules, and Separation of Concerns

* SRP is closely related to **Separation of Concerns (SoC)**.
* Each class or module handling a single concern keeps behaviors separate.
* Modules help group related classes or functions that share a responsibility.
* This leads to a clean, organized, and maintainable system, even over long periods.

## 7) What is Separation of Concerns (SoC)?

Separation of Concerns means **dividing the program into distinct parts**, each handling a single type of problem or concern, and avoiding mixing different concerns together.

**Official Definition:**

> Separation of Concerns is a design principle for separating a computer program into distinct sections, such that each section addresses a separate concern.

Source: [Wikipedia – Separation of Concerns](https://en.wikipedia.org/wiki/Separation_of_concerns)

### What is a Concern?

A **Concern** is a part of the system that has an independent reason to change.

**Examples of Concerns:**

* Business Logic (calculations)
* Data Storage (Database)
* User Interface (UI)
* Printing Reports
* Input Validation
* Authentication

Two things are separate concerns if:

* They change for different reasons
* Different people are working on them

Source: [Martin Fowler – Separation of Concerns](https://martinfowler.com/bliki/SeparationOfConcerns.html)

### Why mixing concerns is bad

**Real life analogy:**

* One person cooks, cleans, handles finances, and serves customers.
* Under stress, everything breaks.
* Changing one task affects the others.

**Programming analogy:**

* A program calculates student grades, stores them in a database, and displays them on a screen.
* If all is in one module:

  * Changing the UI breaks calculations
  * Changing database breaks display
  * Changing calculations risks the whole system
* This is called **Tight Coupling** and violates SoC

Source: [Microsoft – Software Architecture Patterns](https://learn.microsoft.com/en-us/azure/architecture/guide/architecture-styles/)

### Benefits of separating concerns

| Concern | Responsible for         |
| ------- | ----------------------- |
| Logic   | Calculations and rules  |
| Data    | Storage and retrieval   |
| UI      | Display and interaction |
| Reports | Printing and reporting  |

* Each part can change independently
* Fewer errors
* Easier to understand
* Easier testing
* Better for teamwork

Source: [Clean Architecture – Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

### Relation to SOLID and SRP

* **Separation of Concerns** is a very general idea
* **SRP** is a specific application of SoC at the class/module level
* SRP says: "Make each class or module responsible for only one concern"

Source: [Wikipedia – Single Responsibility Principle](https://en.wikipedia.org/wiki/Single-responsibility_principle)

### Mental Summary

* Concern = independent reason to change
* Separation of Concerns = don’t mix reasons to change
* SoC predates SOLID
* SRP = SoC applied to classes/modules
* If you understand SoC, SRP becomes intuitive and design patterns make sense

### Example in Java (conceptual)

Instead of putting all responsibilities in one class:

```java
class StudentManager {
    void calculateGrades() {}
    void saveToDatabase() {}
    void displayUI() {}
}
```

We separate concerns into modules/classes:

```java
class GradeCalculator {
    void calculateGrades() {}
}

class StudentRepository {
    void saveToDatabase() {}
}

class StudentUI {
    void displayUI() {}
}
```

* Each class/module has one concern
* Changes in one module don’t affect others
* Follows SoC and SRP

