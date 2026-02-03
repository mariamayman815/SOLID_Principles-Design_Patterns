# Liskov Substitution Principle (LSP)

## 1) Definition

The **Liskov Substitution Principle (LSP)** states:

> Subtypes must be substitutable for their base types without breaking the correctness of the program.

This means:

- Open for substitution → child can replace parent
- Closed for surprises → behavior must stay consistent

In simple words:

If B extends A, you should use B anywhere A is expected without breaking the program.

---

## 2) Why is LSP important?

When inheritance is misused:

- Code becomes unpredictable
- Polymorphism breaks
- Bugs appear at runtime
- Maintenance becomes harder

So inheritance must preserve behavior, not change it.

---

## 3) The Core Rule

A child class:

❌ must NOT add new constraints  
❌ must NOT change expected behavior  
❌ must NOT break parent logic  

✅ must respect the same contract

If the child changes how the parent works → LSP is violated.

---

## 4) Classic Problem – Rectangle & Square

Mathematically:

Square is a Rectangle ✅

But programmatically:

It breaks behavior ❌

Let’s see why.

---

## 5) Bad Example (Violates LSP)

```java
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
```

### Usage

```java
Rectangle r = new Square();

r.setWidth(5);
r.setHeight(4);

System.out.println(r.area());
```

### Expected

20

### Actual

16

---

## 6) Why is this wrong?

Rectangle allows:

width ≠ height

But Square forces:

width = height

So Square:

- adds extra constraints
- changes behavior
- breaks expectations

Therefore:

❌ LSP is violated

---

## 7) The Real Design Problem

The issue is:

We used inheritance when there is no true behavioral “is-a” relationship.

Square is not a special Rectangle in behavior.

Instead:

They are just different shapes.

So inheritance is the wrong tool.

---

## 8) Correct Design Idea

Instead of:

Square extends Rectangle ❌

Use:

Rectangle → Shape  
Square → Shape

Common abstraction without forcing wrong inheritance.

---

## 9) Good Example (Applies LSP)

### Step 1 – Create abstraction

```java
interface Shape {
    int area();
}
```

### Step 2 – Rectangle independent

```java
class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public int area() {
        return width * height;
    }
}
```

### Step 3 – Square independent

```java
class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public int area() {
        return side * side;
    }
}
```

### Usage

```java
Shape s1 = new Rectangle(5, 4);
Shape s2 = new Square(5);

System.out.println(s1.area());
System.out.println(s2.area());
```

---

## 10) Why this design is correct?

Now:

- No weird overrides
- No hidden constraints
- No broken behavior
- Each class handles its own responsibility
- Substitution works perfectly

Both follow the same contract:

area()

So:

✅ LSP satisfied

---

## 11) When to avoid inheritance

If the subclass:

- changes logic
- adds restrictions
- overrides heavily
- or feels forced

Then:

👉 Use abstraction or composition instead of inheritance

Inheritance should model behavior similarity, not mathematical similarity.

---

## 12) The Golden Rule of LSP

If replacing the parent object with the child:

- breaks functionality
- changes results
- or surprises the user

Your design violates LSP.

Good inheritance should be invisible and safe.

---

## 13) Summary

- Child must behave like parent
- Do not change expectations
- Do not add constraints
- Prefer abstraction when behavior differs
- Rectangle–Square inheritance is a common LSP trap

If OCP protects stable code from modification,
LSP protects inheritance from misuse.
