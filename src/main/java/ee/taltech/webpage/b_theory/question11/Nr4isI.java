package ee.taltech.webpage.b_theory.question11;

public class Nr4isI {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does I stand for in SOLID? - It is better to create more highly specialized interfaces
    // than one that includes many functions. This will make the architecture more flexible and allow you to
    // use the interfaces separately.
    //todo B Give an example. Write actual or pseudo code.
    //

    // Here we create interface with 2 functions
    interface ItemClickB {
        void onClick();
        void onLongClick();
    }

    // Here we implement our interface to class
    static class SomeClass implements ItemClickB {
        public void onClick() {
            // needed code here
        }

        public void onLongClick() {
            // here we dont do nothing, but we still need to add this method, because our interface needs so.
        }
    }

    // TODO but here we separate our first interface, to 2 small ones, so the code is more flexible and we can use it
    //  separately

    interface ItemClickAfter {
        void onClick();
    }

    interface ItemLongClickAfter {
        void onLongClick();
    }

    static class SomeClassAfter implements ItemClickAfter {
        public void onClick() {
            // needed code here
        }
    }

    //TODO or if we need second function
    static class SomeClassAfter2 implements ItemLongClickAfter {
        public void onLongClick() {
            // needed code here
        }
    }
}
