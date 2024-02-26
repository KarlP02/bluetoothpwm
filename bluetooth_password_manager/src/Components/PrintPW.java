package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintPW implements ActionListener {
    JLabel printLine = new JLabel();
    JLabel hiddenLine = new JLabel();
    JButton copyButton = new JButton("copy");
    JButton revealButton = new JButton("reveal");

    public PrintPW(int height, int j, String line, JFrame frame) {
        printLine.setBounds(100, height +  j, 200, 25);
        printLine.setText("********");
        hiddenLine.setText(line);

        copyButton.setBounds(325, height + j, 100, 25);
        copyButton.setFocusable(false);
        copyButton.addActionListener(this);
        revealButton.setBounds(425, height + j, 100, 25);
        revealButton.setFocusable(false);
        revealButton.addActionListener(this);

        frame.add(printLine);
        frame.add(copyButton);
        frame.add(revealButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyButton) {
            String text = hiddenLine.getText();
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }

        if (e.getSource() == revealButton) {
            String hiddenLineText = hiddenLine.getText();
            String printLineText = printLine.getText();
            if (!printLineText.equals(hiddenLineText)){
                try {
                    Cryption aes = new Cryption();
                    aes.initFromStrings("WbppAl3DiX8oZYf/0OlspQ==", "D0Z7l4n/gyRi4Bqe");
                    String decryptedMessage = aes.decrypt(hiddenLineText);
                    printLine.setText(decryptedMessage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                printLine.setText("********");
            }
        }
    }

}
