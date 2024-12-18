package code;

import java.io.*;
import java.util.ArrayList;

public class HashTable {
    private String[] keys;
    private String[] values;
    private int size;
    private static HashTable hashTableMorse;
    private static HashTable hashTableText;
    private static final String HISTORY_FILE = "translation_history.txt"; // Nama file penyimpanan

    private static ArrayList translationHistory = new ArrayList();

    public HashTable(int size) {
        this.size = size;
        keys = new String[size];
        values = new String[size];
    }

    public int hashFunc(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % size;
  
    }

    public void insert(String key, String value) {
        int index = hashFunc(key);
        while (keys[index] != null) {
            index = (index + 1) % size;
        }
        keys[index] = key;
        values[index] = value;
    }

    public String find(String key) {
        int index = hashFunc(key);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            index = (index + 1) % size;
        }
        return null;
    }

    public static void inisialisasiHashTables() {
        String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "0"
        };

        String[] morseCode = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-",
            ".....", "-....", "--...", "---..", "----.", "-----"
        };

        hashTableMorse = new HashTable(36);
        hashTableText = new HashTable(36);

        for (int i = 0; i < letters.length; i++) {
            hashTableMorse.insert(letters[i], morseCode[i]);
            hashTableText.insert(morseCode[i], letters[i]);
        }

        aksesTranslationHistory();
    }

    public static String translateToMorse(String input) {
        StringBuilder morseCode = new StringBuilder();
        for (char c : input.toUpperCase().toCharArray()) {
            if (c == ' ') {
                morseCode.append("/ ");
            } else {
                String morse = hashTableMorse.find(String.valueOf(c));
                if (morse != null) {
                    morseCode.append(morse).append(" ");
                }
            }
        }
        String result = morseCode.toString().trim();
        saveTranslation(input, result);
        return result;
    }

    public static String translateToText(String input) {
        StringBuilder text = new StringBuilder();
        String[] morseArray = input.split(" ");
        for (String morse : morseArray) {
            if (morse.equals("/")) {
                text.append(" ");
            } else {
                String letter = hashTableText.find(morse);
                if (letter != null) {
                    text.append(letter);
                }
            }
        }
        String result = text.toString();
        saveTranslation(input, result);
        return result;
    }

    private static void saveTranslation(String input, String result) {
        String record = "Input: " + input + "\nOutput: " + result + "\n";
        translationHistory.add(record);

        // Save to file
        try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
            writer.write(record + "\n");
        } catch (IOException e) {
            System.err.println("Error saving history to file: " + e.getMessage());
        }
    }

    private static void aksesTranslationHistory() {
        File file = new File(HISTORY_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    translationHistory.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error loading history from file: " + e.getMessage());
            }
        }
    }

    public static ArrayList getTranslationHistory() {
        return translationHistory;
    }
}