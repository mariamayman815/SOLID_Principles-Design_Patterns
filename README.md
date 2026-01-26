# SOLID Principles & Design Patterns

---

## What are SOLID Principles?

**SOLID** is a set of fundamental principles in object-oriented design. The main goal of these principles is to help developers write software that is:

* Easy to maintain — changes can be made safely without breaking existing functionality
* Easy to extend — new features can be added with minimal modification to existing code
* Clear and easy to understand for other developers working on the same project

> SOLID principles were introduced by **Robert C. Martin (Uncle Bob)** and are considered a core foundation of clean, scalable, and maintainable software design.

---

## What are Design Patterns?

**Design Patterns** are proven, reusable solutions to common problems in software design.
Instead of reinventing the wheel every time you face a design problem, you can apply a well-known pattern that has already been tested and refined.

Think of a design pattern as a **template or recipe**:

* When a specific problem appears in your code
* You apply the appropriate pattern
* Without rewriting everything from scratch

Design patterns focus on **structure, communication, and responsibility** between objects rather than specific implementation details.

### Common Design Patterns

* Singleton Pattern
* Strategy Pattern
* Observer Pattern
* Factory Pattern
* And many more

---

## Relationship Between SOLID Principles and Design Patterns

SOLID principles and Design Patterns are closely related, but they are not the same thing.

### SOLID Principles = Guidelines

SOLID provides a set of design rules and guidelines that help you create clean, flexible, and maintainable software architecture.

> They answer the question: “How should I design my code?”

### Design Patterns = Ready-made Solutions

Design Patterns are well-known templates and solutions that solve specific, recurring design problems.

> They answer the question: “How do I solve this design problem?”

### How They Work Together

Design Patterns often help developers apply SOLID principles in real code:

* SRP (Single Responsibility Principle) → Patterns like Observer or Strategy help separate responsibilities
* OCP (Open/Closed Principle) → Patterns such as Decorator or Strategy allow adding new behavior without modifying existing code
* DIP (Dependency Inversion Principle) → Techniques like Dependency Injection ensure code depends on abstractions rather than concrete implementations

### In Simple Terms

* SOLID → a strong design philosophy that guides how good software should be structured
* Design Patterns → practical tools and solutions that help implement this philosophy in real-world projects
