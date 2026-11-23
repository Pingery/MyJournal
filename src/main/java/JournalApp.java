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
        SwingUtilities.invokeLater(() ->
                new SectionSelector(sectionLibrary, this::openJournal)
        );
    }

    private void openJournal(ArrayList<JournalSection> selectedSections) {
        currentEntry = new JournalEntry();

        for (JournalSection section : selectedSections) {
            currentEntry.addSection(section);
        }

        new JournalEditor(currentEntry.getSections());
    }

    public JournalEntry getCurrentEntry() {
        return currentEntry;
    }

    public SectionLibrary getSectionLibrary() {
        return sectionLibrary;
    }
}
