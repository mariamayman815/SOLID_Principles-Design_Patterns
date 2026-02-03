# Liskov Substitution Principle (LSP)

**One of the five SOLID principles**  
**L**iskov **S**ubstitution **P**rinciple

> Objects of a superclass should be replaceable with objects of its subclasses without breaking the correctness of the program.

In simple words:  
**A subclass must be usable anywhere its parent is expected — without surprises, without special cases, without runtime errors.**

## Intuition

```text
Parent
   ↑
 Child
This must always work correctly:
JavaParent p = new Child();
The program should behave exactly the same as if we used the real Parent.
No unexpected behavior, no broken logic, no exceptions.
Classic Violation – Rectangle & Square
Bad design (violates LSP)
Javaclass Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int w)  { width = w; }
    public void setHeight(int h) { height = h; }
    public int area()            { return width * height; }
}

class Square extends Rectangle {
    @Override
    public void setWidth(int w)  { width = height = w; }
    @Override
    public void setHeight(int h) { width = height = h; }
}
The famous broken test
JavaRectangle r = new Square();
r.setWidth(5);
r.setHeight(4);
System.out.println(r.area());   // Expected: 20    →   Actual: 16
Why it fails:
Mathematically a square is a rectangle, but in software behavior — no.
The subclass strengthens preconditions and weakens postconditions → breaks LSP.
What LSP really forbids in subclasses
A subclass must NOT:

Add new constraints
Weaken return values / postconditions
Strengthen preconditions
Throw new unexpected exceptions
Change the "meaning" of the parent method

Correct Design – Use Abstraction (Composition over broken inheritance)
Javainterface Shape {
    int area();
}

class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int area() {
        return width * height;
    }
}

class Square implements Shape {
    private final int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }
}
Usage:
JavaShape s1 = new Rectangle(5, 4);
Shape s2 = new Square(5);

System.out.println(s1.area());  // 20
System.out.println(s2.area());  // 25
→ Substitution works perfectly.
Good LSP Example – Payments
Javainterface Payment {
    void pay(double amount);
}

class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Cash paid: " + amount);
    }
}

class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Card paid: " + amount);
    }
}
Substitution is safe and predictable.
Another Common Violation – Birds
Bad:
Javaclass Bird {
    void fly() { /* flies */ }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
Correct:
Javainterface Bird {}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird { ... }
class Penguin implements Bird { ... }
→ Only birds that can fly implement FlyingBird.
Quick LSP Violation Smells

instanceof checks everywhere
UnsupportedOperationException in overrides
Subclass throws exceptions parent never throws
Subclass drastically changes method meaning
Subclass cannot fulfill all parent responsibilities

Rule of Thumb
If you find yourself:

weakening parent behavior
adding new preconditions
throwing surprise exceptions

→ Don't use inheritance.
Prefer interfaces, composition, or separate abstractions.
