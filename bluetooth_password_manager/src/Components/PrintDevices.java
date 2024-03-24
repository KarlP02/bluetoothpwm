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

    JLabel label = new JLabel("device:");
    JButton sendButton = new JButton("send");
    JButton receiveButton = new JButton("receive");
    JLabel printLine = new JLabel();
    JLabel printLine2 = new JLabel();

    PrintDevices(int i, int j, int height, String device, ArrayList<String> deviceAddress, JFrame frame) {

        label.setBounds(10, height + j, 75, 25);

        printLine.setBounds(100, height + j, 200, 25);
        printLine.setText(device);

        printLine2.setBounds(300, height + j, 200, 25);
        printLine2.setText(deviceAddress.get(i));

        sendButton.setBounds(525, height + j, 100, 25);
        sendButton.setFocusable(false);
        sendButton.addActionListener(this);

        receiveButton.setBounds(625, height + j, 100, 25);
        receiveButton.setFocusable(false);
        receiveButton.addActionListener(this);

        frame.add(label);
        frame.add(printLine);
        frame.add(printLine2);
        frame.add(sendButton);
        frame.add(receiveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            try {
                String connectionURL = "btspp://localhost:" + uuid.toString() + ";authenticate=false;encrypt=false;name=FileServer";
                StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(connectionURL);

                System.out.println("Waiting for connection...");
                StreamConnection connection = notifier.acceptAndOpen();
                RemoteDevice remoteDevice = RemoteDevice.getRemoteDevice(connection);
                System.out.println("Client connected " + remoteDevice.getFriendlyName(false));

                OutputStream outputStream = connection.openOutputStream();

                FileInputStream fileInputStream = new FileInputStream("logins.txt");
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                outputStream.close();
                connection.close();
                notifier.close();

                System.out.println("File received successfully.");
            } catch (Exception c) {
                c.printStackTrace();
            }
        }
        if (e.getSource() == receiveButton) {
            try {
                String remoteDeviceAddress = printLine2.getText();
                String connectionURL = "btspp://" + remoteDeviceAddress + ":4;authenticate=false;encrypt=false;master=false";
                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);

                File receivedFile = new File("logins.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
                InputStream inputStream = streamConnection.openInputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File received successfully");

                fileOutputStream.close();
                inputStream.close();
                streamConnection.close();
            } catch (Exception c) {
                c.printStackTrace();
            }
        }
    }
}
