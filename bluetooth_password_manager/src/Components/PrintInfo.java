package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintInfo implements ActionListener {
    JLabel printLine = new JLabel();
    JButton copyButton = new JButton("copy");

    public PrintInfo(int height, int j, String line, JFrame frame) {
        printLine.setBounds(100, height +  j, 200, 25);
        printLine.setText(line);

        copyButton.setBounds(325, height + j, 100, 25);
        copyButton.addActionListener(this);

        frame.add(printLine);
        frame.add(copyButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyButton) {
            String text = printLine.getText();
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }
}
