package Components;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Encryption {
    BufferedWriter writer;
    Cryption aes = new Cryption();

    public String Encryption(String content, int whichFile) {
        try {
            aes.init();
            String encryptedContent = aes.encrypt(content);
            String key = aes.exportKey();
            String iv = aes.exportIV();

            if (whichFile == 0) {
                writer = new BufferedWriter(new FileWriter("mainLoginKeys.txt", true));
            }
            if (whichFile == 1) {
                writer = new BufferedWriter(new FileWriter("loginsKeys.txt", true));
            }
            writer.write(key + "\n");
            writer.write(iv + "\n");
            writer.close();

            return encryptedContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
