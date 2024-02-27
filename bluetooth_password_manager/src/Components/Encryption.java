package Components;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Encryption {
    BufferedWriter writer;
    Cryption aes = new Cryption();

    public String Encryption(String content) {
        try {
            aes.init();
            String encryptedContent = aes.encrypt(content);
            String key = aes.exportKey();
            String iv = aes.exportIV();

            writer = new BufferedWriter(new FileWriter("loginsKeys.txt", true));
            writer.write(key + "\n");
            writer.write(iv + "\n");
            writer.close();

            return encryptedContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
