package ee.taltech.webpage.a_theory.question3;

public class Abstraction {

    //todo
    // Abstra-ca-dabra

    //todo p1
    // In your words (do not use wiki definitions)
    // What is abstraction? (P.S It has nothing to do with keyword abstract)
    // Answer: this is a process of exclusion of the irrelevant, to see the main facts

    //todo p2
    // Create an abstraction of an animal of your choice.
    // It should have at least 1 property and 1 method.
    // Create it as a real java class inside this package.

    private static class Cow {
        private String color;
        private String sound;

        public Cow(String color, String sound) {
            this.color = color;
            this.sound = sound;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public String MakeSound() {
            return this.sound;
        }
    }
}
