/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 *
 * @author Sheila
 */

public class MorseCodeApp {

    private JFrame frame;
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JButton translateButton, addButton;
    private HashMap<String, String> morseCodeMap;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MorseCodeApp window = new MorseCodeApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MorseCodeApp() {
        // Initialize the Morse Code Map
        morseCodeMap = new HashMap<>();
        initializeMorseCodeMap();

        // Initialize the GUI components
        frame = new JFrame("Morse Code Application");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel inputLabel = new JLabel("Masukkan kata dalam Bahasa Indonesia:");
        panel.add(inputLabel);

        inputTextField = new JTextField();
        panel.add(inputTextField);
        inputTextField.setColumns(20);

        translateButton = new JButton("Terjemahkan ke Morse");
        panel.add(translateButton);

        // Action Listener for translation
        translateButton.addActionListener(e -> translateToMorse());

        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.CENTER);

        JLabel outputLabel = new JLabel("Hasil Sandi Morse:");
        panel_1.add(outputLabel);

        outputTextArea = new JTextArea(6, 20);
        outputTextArea.setEditable(false);
        panel_1.add(new JScrollPane(outputTextArea));

        JPanel panel_2 = new JPanel();
        frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

        addButton = new JButton("Tambah Kata Baru");
        panel_2.add(addButton);

        // Action Listener for adding new word to morse code
        addButton.addActionListener(e -> addNewWord());
    }

    // Initialize default Morse Code Map for Bahasa Indonesia
    private void initializeMorseCodeMap() {
        morseCodeMap.put("A", ".-");
        morseCodeMap.put("B", "-...");
        morseCodeMap.put("C", "-.-.");
        morseCodeMap.put("D", "-..");
        morseCodeMap.put("E", ".");
        morseCodeMap.put("F", "..-.");
        morseCodeMap.put("G", "--.");
        morseCodeMap.put("H", "....");
        morseCodeMap.put("I", "..");
        morseCodeMap.put("J", ".---");
        morseCodeMap.put("K", "-.-");
        morseCodeMap.put("L", ".-..");
        morseCodeMap.put("M", "--");
        morseCodeMap.put("N", "-.");
        morseCodeMap.put("O", "---");
        morseCodeMap.put("P", ".--.");
        morseCodeMap.put("Q", "--.-");
        morseCodeMap.put("R", ".-.");
        morseCodeMap.put("S", "...");
        morseCodeMap.put("T", "-");
        morseCodeMap.put("U", "..-");
        morseCodeMap.put("V", "...-");
        morseCodeMap.put("W", ".--");
        morseCodeMap.put("X", "-..-");
        morseCodeMap.put("Y", "-.--");
        morseCodeMap.put("Z", "--..");
        morseCodeMap.put("0", "-----");
        morseCodeMap.put("1", ".----");
        morseCodeMap.put("2", "..---");
        morseCodeMap.put("3", "...--");
        morseCodeMap.put("4", "....-");
        morseCodeMap.put("5", ".....");
        morseCodeMap.put("6", "-....");
        morseCodeMap.put("7", "--...");
        morseCodeMap.put("8", "---..");
        morseCodeMap.put("9", "----.");
        morseCodeMap.put(" ", "/"); // space is represented as "/"
    }

    // Translate input text to morse code
    private void translateToMorse() {
        String inputText = inputTextField.getText().toUpperCase().trim();
        StringBuilder morseCode = new StringBuilder();

        for (char c : inputText.toCharArray()) {
            String morseChar = morseCodeMap.get(String.valueOf(c));
            if (morseChar != null) {
                morseCode.append(morseChar).append(" ");
            } else {
                morseCode.append("? "); // Handle unrecognized characters
            }
        }

        outputTextArea.setText(morseCode.toString().trim());
    }

    // Add new word to the Morse Code Map
    private void addNewWord() {
        String newWord = JOptionPane.showInputDialog(frame, "Masukkan kata baru dalam Bahasa Indonesia:");
        String morseEquivalent = JOptionPane.showInputDialog(frame, "Masukkan sandi morse untuk kata tersebut:");

        if (newWord != null && morseEquivalent != null) {
            newWord = newWord.toUpperCase().trim();
            if (!newWord.isEmpty() && !morseEquivalent.isEmpty()) {
                morseCodeMap.put(newWord, morseEquivalent);
                JOptionPane.showMessageDialog(frame, "Kata baru berhasil ditambahkan!");
            } else {
                JOptionPane.showMessageDialog(frame, "Input tidak valid.");
            }
        }
    }
}