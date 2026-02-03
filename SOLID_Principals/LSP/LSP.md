Liskov Substitution Principle (LSP)
Definition

The Liskov Substitution Principle states:

Objects of a superclass should be replaceable with objects of its subclasses without breaking the correctness of the program.

In simple terms, a subclass must be usable anywhere its parent is expected without changing the behavior of the program.

Intuition

If we have:

Parent
↑
Child

Then the following should always work safely:

Parent p = new Child();

The program should behave exactly the same as when using the parent itself.

There must be:

no unexpected behavior

no special conditions

no runtime errors

no broken logic

A subclass must behave like a true substitute for its parent.

Classic Violation – Rectangle and Square
Rectangle
class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public int area() {
        return width * height;
    }
}

Square (Incorrect Design)
class Square extends Rectangle {

    @Override
    public void setWidth(int w) {
        width = height = w;
    }

    @Override
    public void setHeight(int h) {
        width = height = h;
    }
}

Test
Rectangle r = new Square();

r.setWidth(5);
r.setHeight(4);

System.out.println(r.area());


Expected result: 20
Actual result: 16

The behavior changed even though we used the parent reference.
This breaks LSP.

Why This Design Is Wrong

Mathematically, a square is a rectangle.
But in software design, this inheritance is incorrect.

Because:

Rectangle allows width ≠ height

Square forces width = height

The subclass adds stricter constraints and changes behavior.

A subclass must NOT:

add new constraints

change behavior

reduce capabilities

break parent assumptions

Square violates all of these.

Correct Design – Use Abstraction Instead of Inheritance

Instead of:

Square → Rectangle

Use a shared abstraction:

Rectangle → Shape
Square → Shape

Both are simply shapes, not specializations of each other.

Professional Solution
Abstraction
interface Shape {
    int area();
}

Rectangle
class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    @Override
    public int area() {
        return width * height;
    }
}

Square
class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }
}

Usage
Shape s1 = new Rectangle(5, 4);
Shape s2 = new Square(5);

System.out.println(s1.area());
System.out.println(s2.area());


Now:

no incorrect inheritance

no strange overrides

each class is independent

substitution works correctly

LSP is satisfied.

Correct Example of LSP
Payment abstraction
interface Payment {
    void pay(double amount);
}

Implementations
class CashPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Cash paid " + amount);
    }
}

class CardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Card paid " + amount);
    }
}

Usage
Payment p = new CashPayment();
p.pay(100);

p = new CardPayment();
p.pay(100);


Both behave consistently with the same expectations.
This respects LSP.

Another Common Violation – Bird Example
Bad design
class Bird {
    void fly() {}
}

class Penguin extends Bird {
    void fly() {
        throw new UnsupportedOperationException();
    }
}


Penguins cannot fly, so the subclass breaks the parent behavior.
This violates LSP.

Correct Design
interface Bird {}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {}

class Penguin implements Bird {}


Each class implements only what it can actually do.
No broken behavior.
LSP satisfied.

How to Detect LSP Violations

Common warning signs:

frequent use of instanceof

throwing UnsupportedOperationException

overriding methods that change behavior drastically

subclasses that cannot fully support parent operations

If you see these signs, inheritance is probably incorrect.

Design Rule to Remember

If a subclass changes the parent logic, adds constraints, or breaks expectations, do not use inheritance.

Prefer:

abstraction

interfaces

composition
