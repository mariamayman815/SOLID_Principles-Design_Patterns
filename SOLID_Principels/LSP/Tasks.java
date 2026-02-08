//--------------------Task_1-------------------
//------------This code violate LSP-----------
class Employee {
    double getSalary() {
        return 5000;
    }
}

class Intern extends Employee {
    @Override
    double getSalary() {
        throw new RuntimeException("Interns are unpaid");
    }
}

//---in main---
Employee e = new Intern();
System.out.println(e.getSalary());

//--------------------Solution--------------------

interface Payable {
    double getSalary();
}

class Employee implements Payable {
    public double getSalary() { return 5000; }
}

class Intern { //intern not paid -> not payable
}

// -----------------------------------------------------------------------------

// ------------------Task_2----------------------

class Bird {
    void fly() {
        System.out.println("Flying...");
    }
}

class Ostrich extends Bird {
    @Override
    void fly() {
        System.out.println("Can't fly");
    }
}

//if someone write
void makeBirdFly(Bird b) {
    b.fly();
}
// we predict every bird=fly ,why?? cuz parent class itself known as void fly() , it means every bird fly,and this called contract.
// when violation happens?? if we write ->
void makeBirdFly(Bird b) {
    b.fly();
} //programmers write it (every bird flying) 
//case 1 ->
makeBirdFly(new Bird());// flying ✔
makeBirdFly(new Ostrich());//Can't fly ❌ ,where is the problem?? the problem is this function suppose that every bird fly 
                          // but behavior of this function changed, same type(bird) same method (fly()) but the result is diff.
                          // and this is called ->  "Surprise behavior", and LSP said "Subclasses should not surprise the client"




