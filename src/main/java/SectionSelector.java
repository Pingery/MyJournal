
package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class SectionSelector {
    private JFrame frame;
    private JPanel sectionPanel;
    private JPanel customSectionsPanel;
    private JTextField customSectionField;
    private SectionLibrary sectionLibrary;
    private ArrayList<JCheckBox> sectionCheckboxes;
    private Consumer<ArrayList<JournalSection>> selectionHandler;

    public SectionSelector(SectionLibrary sectionLibrary,
                           Consumer<ArrayList<JournalSection>> selectionHandler) {
        this.sectionLibrary = sectionLibrary;
        this.sectionCheckboxes = new ArrayList<>();
        this.selectionHandler = selectionHandler;

        createWindow();
    }

    private void createWindow() {
        frame = new JFrame("Create Your Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Choose Your Journal Sections",
                SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(234, 234, 234));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        sectionPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        sectionPanel.setBackground(new Color(30, 30, 30));
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        customSectionsPanel = new JPanel();
        customSectionsPanel.setLayout(
                new BoxLayout(customSectionsPanel, BoxLayout.Y_AXIS));
        customSectionsPanel.setBackground(new Color(43, 43, 43));
        customSectionsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(75, 75, 75)),
                "Custom Sections",
                0,
                0,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(234, 234, 234)
        ));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(30, 30, 30));

        displaySections();

        contentPanel.add(sectionPanel);
        contentPanel.add(customSectionsPanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        customSectionField = new JTextField(20);
        customSectionField.setBackground(new Color(38, 38, 38));
        customSectionField.setForeground(new Color(234, 234, 234));
        customSectionField.setCaretColor(new Color(234, 234, 234));
        customSectionField.setBorder(BorderFactory.createLineBorder(
                new Color(75, 75, 75)));

        JButton addButton = new JButton("Add Custom Section");
        styleButton(addButton);
        addButton.addActionListener(event -> addCustomSection());

        JPanel customPanel = new JPanel(new FlowLayout());
        customPanel.setBackground(new Color(30, 30, 30));
        customPanel.add(customSectionField);
        customPanel.add(addButton);

        JButton continueButton = new JButton("Continue to Journal");
        styleButton(continueButton);
        continueButton.addActionListener(event -> continueToJournal());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));
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

        for (String category : sectionLibrary.getCategories()) {
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(
                    new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
            categoryPanel.setBackground(new Color(43, 43, 43));

            categoryPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(75, 75, 75)),
                    category,
                    0,
                    0,
                    new Font("SansSerif", Font.BOLD, 14),
                    new Color(234, 234, 234)
            ));

            ArrayList<JCheckBox> categoryCheckboxes = new ArrayList<>();

            JCheckBox selectAll = new JCheckBox("Select All");
            styleCheckbox(selectAll);

            selectAll.addActionListener(event -> {
                for (JCheckBox checkbox : categoryCheckboxes) {
                    checkbox.setSelected(selectAll.isSelected());
                }
            });

            categoryPanel.add(selectAll);

            for (String section : sectionLibrary.getSections(category)) {
                JCheckBox checkbox = new JCheckBox(section);
                styleCheckbox(checkbox);

                categoryCheckboxes.add(checkbox);
                sectionCheckboxes.add(checkbox);
                categoryPanel.add(checkbox);
            }

            sectionPanel.add(categoryPanel);
        }

        sectionPanel.revalidate();
        sectionPanel.repaint();
    }

    private void addCustomSection() {
        String name = customSectionField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a section name.");
            return;
        }

        for (JCheckBox checkbox : sectionCheckboxes) {
            if (checkbox.getText().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(frame,
                        "This section already exists.");
                return;
            }
        }

        JCheckBox checkbox = new JCheckBox(name);
        styleCheckbox(checkbox);
        checkbox.setSelected(true);

        sectionCheckboxes.add(checkbox);
        customSectionsPanel.add(checkbox);

        customSectionField.setText("");

        customSectionsPanel.revalidate();
        customSectionsPanel.repaint();
    }

    private void continueToJournal() {
        ArrayList<JournalSection> selectedSections = new ArrayList<>();

        for (JCheckBox checkbox : sectionCheckboxes) {
            if (checkbox.isSelected()) {
                selectedSections.add(new JournalSection(checkbox.getText()));
            }
        }

        if (selectedSections.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Please select at least one section.");
            return;
        }

        frame.dispose();
        selectionHandler.accept(selectedSections);
    }

    private void styleCheckbox(JCheckBox checkbox) {
        checkbox.setBackground(new Color(43, 43, 43));
        checkbox.setForeground(new Color(234, 234, 234));
        checkbox.setFocusPainted(false);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(55, 55, 55));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
    }
}
