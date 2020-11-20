package ee.taltech.webpage.b_theory.question11;

public class Nr5isD {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does D stand for in SOLID? Explain the principle.
    // Answer: D stands for Dependency inversion principle
    // Principle is to decrease contentedness in computer programs.
    // Upper level modules shouldn't depend on lower level modules, so they have to depend on abstraction.
    // Abstractions shouldn't depend on details, but details have to depend on abstractions.
}

//todo B Give an example. Write actual or pseudo code.
// this is an example of service having 1 class in another.
// Car is lower level module, CarServiceBefore is upper level module.
// Here CarServiceBefore depends on Car class, what is wrong with SOLID D principle.
class CarServiceBefore {

    private CarBefore car;

    public CarServiceBefore(CarBefore car) {
        this.car = car;
    }

    //private methods cut
}

class CarBefore{
    //private methods cut
}


//todo Now we have in CarService class CarInterface, so they are separated and also no need in type checking for belonging of the passed object to the class.
class CarServiceAfter {
    private CarInterface car;

    public CarServiceAfter(CarInterface car) {
        this.car = car;
    }

    //private methods cut
}

//Interface have method getCarName and CarAfter realize it.
interface CarInterface {
    public String getCarName();

    //private methods cut
}

//Make Car class, which implements CarInterface.
class CarAfter implements CarInterface {

    @Override
    public String getCarName() {
        return "name";
    }

    //private methods cut
}
