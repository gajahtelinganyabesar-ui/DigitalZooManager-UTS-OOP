package com.zoo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ZooFrame extends JFrame {

    // ... (Semua properti GUI sama seperti sebelumnya) ...
    private List<Animal> zoo = new ArrayList<>();

    private JTextField nameField = new JTextField(15);
    private JTextField ageField = new JTextField(15);
    private JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Mammal", "Bird"});

    // Gunakan JLabel untuk label dinamis
    private JLabel specificDetailLabel = new JLabel("Fur Color:");
    private JTextField furColorField = new JTextField(15);
    private JCheckBox canFlyCheckBox = new JCheckBox("Can Fly?");

    private JButton addButton = new JButton("Add Animal");
    private JTextArea logArea = new JTextArea(15, 40);

    public ZooFrame() {
        // PERBAIKAN: Panggil super() SEBAGAI STATEMENT PERTAMA
        super("Digital Zoo Manager");

        // Sekarang, atur Look and Feel (L&F)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Fallback to default L&F
        }

        // JFrame Setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hapus this.setLayout(new BorderLayout(10, 10))

        // --- Container Utama dengan Padding (Untuk Menambah Jarak di Tepi) ---
        JPanel mainContainer = new JPanel(new BorderLayout(10, 10));
        // Tambahkan padding 10 pixel di semua sisi
        mainContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Set up Log Area
        logArea.setEditable(false);
        logArea.setText("Zoo Log:\n");

        // --- Input Form Panel Setup (GridBagLayout) ---
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5); // Tambahkan padding vertikal lebih besar
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Penyesuaian layout dan bobot kolom agar label tidak terlalu lebar

        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0; inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; inputPanel.add(nameField, gbc);

        // Row 2: Age
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; inputPanel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0; inputPanel.add(ageField, gbc);

        // Row 3: Animal Type (JComboBox)
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; inputPanel.add(new JLabel("Animal Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0; inputPanel.add(typeComboBox, gbc);

        // Row 4: Specific Attribute (dynamic display)
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        inputPanel.add(specificDetailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0;
        inputPanel.add(furColorField, gbc);
        inputPanel.add(canFlyCheckBox, gbc);
        canFlyCheckBox.setVisible(false);

        // Row 5: Add Button - Perintahkan tombol untuk mengisi seluruh lebar
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.weightx = 1.0;

        // Tambahkan tombol ke panel terpisah agar lebih mudah diatur
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(5, 0, 5, 0)); // Padding vertikal di atas/bawah tombol
        buttonPanel.add(addButton, BorderLayout.CENTER);

        inputPanel.add(buttonPanel, gbc);


        // --- Tambahkan komponen ke Main Container ---
        mainContainer.add(inputPanel, BorderLayout.NORTH);
        mainContainer.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // --- Tambahkan Main Container ke Frame ---
        this.add(mainContainer);

        // Setup Event Listeners (Logic)
        setupListeners();

        // Finalize Frame
        this.pack();
        // Atur ukuran awal agar tidak terlalu lebar
        this.setSize(500, this.getHeight());
        this.setMinimumSize(new Dimension(450, 400));

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ... (setupListeners(), addNewAnimal(), dan main() tetap sama) ...
    private void setupListeners() {
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                if ("Mammal".equals(selectedType)) {
                    specificDetailLabel.setText("Fur Color:");
                    furColorField.setVisible(true);
                    canFlyCheckBox.setVisible(false);
                } else if ("Bird".equals(selectedType)) {
                    specificDetailLabel.setText("Can Fly?:");
                    furColorField.setVisible(false);
                    canFlyCheckBox.setVisible(true);
                }
                pack();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewAnimal();
            }
        });
    }

    // ... (addNewAnimal() method sama seperti sebelumnya) ...
    private void addNewAnimal() {
        Animal newAnimal = null;
        try {
            // Read general input and validate
            String name = nameField.getText().trim();

            int age;
            try {
                age = Integer.parseInt(ageField.getText().trim());
                if (age <= 0) {
                    JOptionPane.showMessageDialog(this, "Age must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String selectedType = (String) typeComboBox.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name must not be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create object based on type selection
            if ("Mammal".equals(selectedType)) {
                String furColor = furColorField.getText().trim();
                newAnimal = new Mammal(name, age, furColor);
                logArea.append("\n--- Added a new Mammal! ---\n");
            } else if ("Bird".equals(selectedType)) {
                boolean canFly = canFlyCheckBox.isSelected();
                newAnimal = new Bird(name, age, canFly);
                logArea.append("\n--- Added a new Bird! ---\n");
            }

            // Add to the list
            if (newAnimal != null) {
                zoo.add(newAnimal);

                // Update JTextArea
                logArea.append("Info: " + newAnimal.getInfo() + "\n");
                logArea.append("Sound: " + newAnimal.makeSound() + "\n");

                // Clear input fields
                nameField.setText("");
                ageField.setText("");
                furColorField.setText("");
                canFlyCheckBox.setSelected(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus L&F not available, using default.");
        }

        SwingUtilities.invokeLater(() -> new ZooFrame());
    }
}