package Components;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Decryption {
    BufferedReader reader;
    Cryption aes = new Cryption();
    String key;
    String iv;
    String decryptedString;

    public String Decryption(int lineNumber, String lineString) {
        try {
            reader = new BufferedReader(new FileReader("loginsKeys.txt"));
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    key = line;
                }
                if (currentLine == lineNumber + 1) {
                    iv = line;
                    break;
                }
                currentLine++;
            }

            aes.initWithStrings(key, iv);
            return decryptedString = aes.decrypt(lineString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
