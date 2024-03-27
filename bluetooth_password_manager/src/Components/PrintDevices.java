package Components;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.rmi.Remote;
import java.util.ArrayList;

public class PrintDevices implements ActionListener {
    private static final String serverUUID = "2d26618601fb47c28d9f10b8ec891363";
    UUID uuid = new UUID(serverUUID, false);
    SendAndReceive sendAndReceive = new SendAndReceive();
    JLabel label = new JLabel("device:");
    JButton sendLoginButton = new JButton("send login");
    JButton receiveLoginButton = new JButton("receive login");
    JButton sendLoginKeysButton = new JButton("send keys");
    JButton receiveLoginKeysButton = new JButton("receive keys");
    JLabel printLine = new JLabel();
    JLabel printLine2 = new JLabel();

    PrintDevices(int i, int j, int height, String device, ArrayList<String> deviceAddress, JFrame frame) {

        label.setBounds(10, height + j, 75, 25);

        printLine.setBounds(100, height + j, 200, 25);
        printLine.setText(device);

        printLine2.setBounds(300, height + j, 200, 25);
        printLine2.setText(deviceAddress.get(i));

        sendLoginButton.setBounds(525, height + j, 100, 25);
        sendLoginButton.setFocusable(false);
        sendLoginButton.addActionListener(this);

        sendLoginKeysButton.setBounds(525, height + j + 25, 100, 25);
        sendLoginKeysButton.setFocusable(false);
        sendLoginKeysButton.addActionListener(this);

        receiveLoginButton.setBounds(625, height + j, 150, 25);
        receiveLoginButton.setFocusable(false);
        receiveLoginButton.addActionListener(this);

        receiveLoginKeysButton.setBounds(625, height + j + 25, 150, 25);
        receiveLoginKeysButton.setFocusable(false);
        receiveLoginKeysButton.addActionListener(this);

        frame.add(label);
        frame.add(printLine);
        frame.add(printLine2);
        frame.add(sendLoginButton);
        frame.add(receiveLoginButton);
        frame.add(sendLoginKeysButton);
        frame.add(receiveLoginKeysButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendLoginButton) {
            File fileToSend = new File("logins.txt");
            sendAndReceive.SendFile(uuid, fileToSend);
        }
        if (e.getSource() == sendLoginKeysButton) {
            File fileToSend = new File("loginsKeys.txt");
            sendAndReceive.SendFile(uuid, fileToSend);
        }
        if (e.getSource() == receiveLoginButton) {
            String remoteDeviceAddress = printLine2.getText();
            File fileToReceive = new File("logins.txt");
            sendAndReceive.ReceiveFile(remoteDeviceAddress, fileToReceive);
        }
        if (e.getSource() == receiveLoginKeysButton) {
            String remoteDeviceAddress = printLine2.getText();
            File fileToReceive = new File("loginsKeys.txt");
            sendAndReceive.ReceiveFile(remoteDeviceAddress, fileToReceive);
        }
    }
}
