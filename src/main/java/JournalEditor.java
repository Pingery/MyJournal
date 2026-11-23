
package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JournalEditor {
    private JFrame frame;
    private JPanel sectionPanel;
    private JTextArea writingArea;
    private ArrayList<JournalSection> sections;
    private int currentSectionIndex;
    private FileManager fileManager;
    private JournalEntry journalEntry;

    public JournalEditor(ArrayList<JournalSection> sections) {
        this.sections = sections;
        this.currentSectionIndex = 0;
        this.fileManager = new FileManager();
        this.journalEntry = new JournalEntry();

        for (JournalSection section : sections) {
            journalEntry.addSection(section);
        }

        createWindow();
    }

    private void createWindow() {
        frame = new JFrame("MyJournal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setBackground(new Color(30, 30, 30));
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        writingArea = new JTextArea();
        writingArea.setLineWrap(true);
        writingArea.setWrapStyleWord(true);
        writingArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        writingArea.setBackground(new Color(38, 38, 38));
        writingArea.setForeground(new Color(234, 234, 234));
        writingArea.setCaretColor(new Color(234, 234, 234));
        writingArea.setSelectionColor(new Color(75, 100, 140));
        writingArea.setSelectedTextColor(Color.WHITE);
        writingArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(writingArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(38, 38, 38));

        JButton saveButton = new JButton("Save Journal");
        saveButton.setBackground(new Color(55, 55, 55));
        saveButton.setForeground(Color.BLACK);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(event -> saveJournal());

        JButton openButton = new JButton("Open Journal");
        openButton.setBackground(new Color(55, 55, 55));
        openButton.setForeground(Color.BLACK);
        openButton.setFocusPainted(false);
        openButton.addActionListener(event -> openJournal());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.add(openButton);
        bottomPanel.add(saveButton);

        frame.add(sectionPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        displaySections();

        frame.setVisible(true);
    }

    private void displaySections() {
        sectionPanel.removeAll();

        for (int i = 0; i < sections.size(); i++) {
            int index = i;

            JButton button = new JButton(sections.get(i).getName());
            button.setBackground(new Color(55, 55, 55));
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

            button.addActionListener(event -> switchSection(index));

            sectionPanel.add(button);
            sectionPanel.add(Box.createVerticalStrut(8));
        }

        sectionPanel.revalidate();
        sectionPanel.repaint();
    }

    private void switchSection(int index) {
        saveCurrentSection();

        currentSectionIndex = index;

        writingArea.setText(sections.get(index).getContent());
    }

    private void saveCurrentSection() {
        if (!sections.isEmpty()) {
            sections.get(currentSectionIndex).setContent(writingArea.getText());
        }
    }

    private void saveJournal() {
        saveCurrentSection();
        fileManager.saveEntry(journalEntry);
    }

    private void openJournal() {
        JournalEntry loadedEntry = fileManager.loadEntry();

        if (loadedEntry == null) {
            return;
        }

        if (loadedEntry.getSections().isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "This journal entry does not contain any sections.");
            return;
        }

        sections = loadedEntry.getSections();
        journalEntry = loadedEntry;
        currentSectionIndex = 0;

        displaySections();

        writingArea.setText(sections.get(0).getContent());
    }
}
