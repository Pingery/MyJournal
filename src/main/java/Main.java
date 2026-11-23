
package main.java;

public class Main {
    void main() {
        IO.println("==============================");
        IO.println("          My Journal");
        IO.println("==============================");
        IO.println();

        IO.println("Welcome to My Journal!");
        IO.println("A personal space for your thoughts!");

        JournalApp app = new JournalApp();
        app.start();
    }
}
