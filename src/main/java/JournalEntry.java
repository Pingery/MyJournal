package main.java;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class JournalEntry {
    private LocalDateTime dateCreated;
    private ArrayList<JournalSection> sections;

    public JournalEntry() {
        this.dateCreated = LocalDateTime.now();
        this.sections = new ArrayList<>();
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public ArrayList<JournalSection> getSections() {
        return sections;
    }

    public void addSection(JournalSection section) {
        sections.add(section);
    }

    public void removeSection(JournalSection section) {
        sections.remove(section);
    }
}
