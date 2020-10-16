package ee.taltech.webpage.a_theory.question5;

public class ApplicationLayers {

    //todo
    // Architects insist on having layered architecture in the back-end... ¯\_(ツ)_/¯

    //todo p1
    // Name 3 layers of back-end architecture. Give a brief description for each.
    // 1 Entity Layer
    // Description: This layer contains objects to transfer them between other layers.
    // 2 Data Layer
    // Description: This layer contains all needed to communicate with database, it sends and receives.
    // 3 Business Layer
    // Description: This layer evaluates data from client (API) and using business rules and logic transfers it to
    // data layer.

    //todo p2
    // Do you agree with the architects? Why?
    // Yes
    // Because: It's hard to create great projects if there are no rules and if it is not divided into parts.
    // It's impossible to do all needed in one place.

    //todo p3
    // We use objects to transport data between different layers.
    // What is the difference between Entity and Dto? What is same between them?
    // Answer: The same is that they are data transfer objects. Difference is that if we want to hide somethin
    // from client, for example if we have some field that client don't need to read and change, Dto able us to do this.

}
