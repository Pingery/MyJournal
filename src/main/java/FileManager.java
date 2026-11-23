package main.java;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
}
