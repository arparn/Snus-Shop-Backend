package ee.taltech.webpage.b_theory.question11;

public class Nr2isO {
//todo this is a contribution based question so make sure to keep commits separate

//todo A What does O stand for in SOLID? Explain the principle. Answered by Artur-Aleksander Pärnoja
// Answer: O stands for Open/Closed principle
// This principle stands that software classes, modules and functions must be open for extension,
// but not for modification. These means that if we need to expand or add functionality to already existing entities
// we should make entities that will extend them, but not to change code of already existing entities.
// For example use interfaces and abstract classes.
}

//todo B Give an example. Write actual or pseudo code.
// For example if we want to create a calculator with addition and subtraction operations, we can do it like this:
// Instead of writing all code in one class, lets separate it into two classes using implementation.
class Calculator {
    public int calculate(Operation operation) {
        return operation.giveAnswer();
    }
}

interface Operation {
    public int giveAnswer();
}

class Addition implements Operation {

    private int a;
    private int b;

    public Addition(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // getters and setters here

    @Override
    public int giveAnswer() {
        return a + b;
    }
}

// todo: As both addition and subtraction have similar logic, they can implement same operation interface
//  and we dont need to put all code in one class. By doing this we follow Open/closed principle.
//  In future, if we want to add multiplication and division we dont need to modify Operation class, we
//  can simply extend it by creating new classes for multiplication and division which will implement Operation class.

class Subtraction implements Operation {

    private int a;
    private int b;

    public Subtraction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    // getters and setters here

    @Override
    public int giveAnswer() {
        return a - b;
    }
}