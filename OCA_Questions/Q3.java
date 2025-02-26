// 1) How many objects are eligible for garbage collection right before the end of the main method?

// 1: public class Person {
// 2: public Person youngestChild;
// 3:
// 4: public static void main(String... args) {
// 5: Person elena = new Person();
// 6: Person diana = new Person();
// 7: elena.youngestChild = diana;
// 8: diana = null;
// 9: Person zoe = new Person();
// 10: elena.youngestChild = zoe;
// 11: zoe = null;
// 12: }
// 13: }

// A. None
// B. One
// C. Two
// D. Three

public class Q3 {
    public static void main(String[] args) {
        System.err.println("Hello, World!");
    }
}
