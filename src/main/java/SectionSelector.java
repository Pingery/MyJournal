
package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class SectionSelector {
    private JFrame frame;
    private JPanel sectionPanel;
    private JTextField customSectionField;
    private ArrayList<String> availableSections;
    private ArrayList<JCheckBox> sectionCheckboxes;
    private Consumer<ArrayList<JournalSection>> selectionHandler;

    public SectionSelector(SectionLibrary sectionLibrary,
                           Consumer<ArrayList<JournalSection>> selectionHandler) {
        this.availableSections = sectionLibrary.getSections();
        this.sectionCheckboxes = new ArrayList<>();
        this.selectionHandler = selectionHandler;

        createWindow();
    }

    private void createWindow() {
        frame = new JFrame("Create Your Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Choose Your Journal Sections", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));

        sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        displaySections();

        JScrollPane scrollPane = new JScrollPane(sectionPanel);

        customSectionField = new JTextField(20);

        JButton addButton = new JButton("Add Custom Section");
        addButton.addActionListener(event -> addCustomSection());

        JPanel customPanel = new JPanel(new FlowLayout());
        customPanel.add(customSectionField);
        customPanel.add(addButton);

        JButton continueButton = new JButton("Continue to Journal");
        continueButton.addActionListener(event -> continueToJournal());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(customPanel, BorderLayout.CENTER);
        bottomPanel.add(continueButton, BorderLayout.SOUTH);

        frame.add(title, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void displaySections() {
        sectionPanel.removeAll();
        sectionCheckboxes.clear();

        for (String section : availableSections) {
            JCheckBox checkbox = new JCheckBox(section);

            sectionCheckboxes.add(checkbox);
            sectionPanel.add(checkbox);
        }

        sectionPanel.revalidate();
        sectionPanel.repaint();
    }

    private void addCustomSection() {
        String name = customSectionField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a section name.");
            return;
        }

        if (availableSections.contains(name)) {
            JOptionPane.showMessageDialog(frame, "This section already exists.");
            return;
        }

        availableSections.add(name);
        displaySections();

        customSectionField.setText("");
    }

    private void continueToJournal() {
        ArrayList<JournalSection> selectedSections = new ArrayList<>();

        for (JCheckBox checkbox : sectionCheckboxes) {
            if (checkbox.isSelected()) {
                selectedSections.add(new JournalSection(checkbox.getText()));
            }
        }

        if (selectedSections.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select at least one section.");
            return;
        }

        frame.dispose();
        selectionHandler.accept(selectedSections);
    }
}
