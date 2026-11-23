
package main.java;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileManager {

    public void saveEntry(JournalEntry entry) {
        JFileChooser fileChooser = new JFileChooser();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");

        String fileName = "MyJournal_" +
                entry.getDateCreated().format(formatter) + ".txt";

        fileChooser.setSelectedFile(new File(fileName));

        int result = fileChooser.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith(".txt")) {
            file = new File(file.getAbsolutePath() + ".txt");
        }

        if (file.exists()) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "This file already exists. Do you want to replace it?",
                    "Confirm Overwrite",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("My Journal\n");
            writer.write("Date: " + entry.getDateCreated() + "\n\n");

            for (JournalSection section : entry.getSections()) {
                writer.write("================================\n");
                writer.write(section.getName() + "\n");
                writer.write("================================\n\n");

                writer.write(section.getContent());
                writer.write("\n\n");
            }

            JOptionPane.showMessageDialog(null,
                    "Your journal entry was saved successfully!");

        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null,
                    "There was an error saving your journal entry.");
        }
    }

    public JournalEntry loadEntry() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Journal Entry");

        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        File file = fileChooser.getSelectedFile();

        try {
            List<String> lines = Files.readAllLines(file.toPath());

            JournalEntry entry = new JournalEntry();
            String separator = "================================";

            int i = 0;

            while (i < lines.size()) {
                if (!lines.get(i).equals(separator)) {
                    i++;
                    continue;
                }

                if (i + 2 >= lines.size()) {
                    break;
                }

                String sectionName = lines.get(i + 1);
                i += 3;

                if (i < lines.size() && lines.get(i).isEmpty()) {
                    i++;
                }

                StringBuilder content = new StringBuilder();

                while (i < lines.size() &&
                        !lines.get(i).equals(separator)) {
                    content.append(lines.get(i)).append("\n");
                    i++;
                }

                String sectionContent = content.toString();

                while (sectionContent.endsWith("\n")) {
                    sectionContent = sectionContent.substring(
                            0, sectionContent.length() - 1);
                }

                JournalSection section = new JournalSection(sectionName);
                section.setContent(sectionContent);

                entry.addSection(section);
            }

            JOptionPane.showMessageDialog(null,
                    "Journal entry loaded successfully!");

            return entry;

        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null,
                    "There was an error opening your journal entry.");

            return null;
        }
    }
}
