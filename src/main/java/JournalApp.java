package main.java;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class JournalApp {
    private SectionLibrary sectionLibrary;
    private JournalEntry currentEntry;

    public JournalApp() {
        this.sectionLibrary = new SectionLibrary();
        this.currentEntry = new JournalEntry();
    }

    public void start() {
        ArrayList<String> availableSections = sectionLibrary.getSections();

        currentEntry.addSection(new JournalSection(availableSections.get(0)));
        currentEntry.addSection(new JournalSection(availableSections.get(1)));
        currentEntry.addSection(new JournalSection(availableSections.get(2)));

        SwingUtilities.invokeLater(() ->
                new JournalEditor(currentEntry.getSections())
        );
    }

    public JournalEntry getCurrentEntry() {
        return currentEntry;
    }

    public SectionLibrary getSectionLibrary() {
        return sectionLibrary;
    }
}
