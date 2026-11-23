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

    public JournalEditor(ArrayList<JournalSection> sections) {
        this.sections = sections;
        this.currentSectionIndex = 0;

        createWindow();
    }

    private void createWindow() {
        frame = new JFrame("MyJournal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        writingArea = new JTextArea();
        writingArea.setLineWrap(true);
        writingArea.setWrapStyleWord(true);
        writingArea.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(writingArea);

        frame.add(sectionPanel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        displaySections();

        frame.setVisible(true);
    }

    private void displaySections() {
        sectionPanel.removeAll();

        for (int i = 0; i < sections.size(); i++) {
            int index = i;

            JButton button = new JButton(sections.get(i).getName());

            button.addActionListener(event -> switchSection(index));

            sectionPanel.add(button);
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
}
