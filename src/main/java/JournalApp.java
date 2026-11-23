
package main.java;

public class JournalApp {
    private SectionLibrary sectionLibrary;
    private JournalEntry currentEntry;

    public JournalApp() {
        this.sectionLibrary = new SectionLibrary();
        this.currentEntry = new JournalEntry();
    }

    public void start() {
        System.out.println("Welcome to My Journal!");
    }

    public JournalEntry getCurrentEntry() {
        return currentEntry;
    }

    public SectionLibrary getSectionLibrary() {
        return sectionLibrary;
    }
}
