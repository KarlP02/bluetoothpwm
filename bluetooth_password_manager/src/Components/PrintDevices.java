package Components;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class PrintDevices implements ActionListener {
    private static final String serverUUID = "0000110100001000800000805F9B34FB";
    private static final String filePath = "logins.txt";

    JLabel label = new JLabel("device:");
    JButton connectButton = new JButton("connect");
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

        connectButton.setBounds(525, height + j, 100, 25);
        connectButton.setFocusable(false);
        connectButton.addActionListener(this);

        sendButton.setBounds(625, height + j, 100, 25);
        sendButton.setFocusable(false);
        sendButton.addActionListener(this);

        receiveButton.setBounds(725, height + j, 100, 25);
        receiveButton.setFocusable(false);
        receiveButton.addActionListener(this);

        frame.add(label);
        frame.add(printLine);
        frame.add(printLine2);
        frame.add(connectButton);
        frame.add(sendButton);
        frame.add(receiveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == connectButton) {
//            try {
//                String remoteDeviceAddress = printLine2.getText();
//                String connectionURL = "btspp://" + remoteDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";
//                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);
//
//                System.out.println("Connected to: " + remoteDeviceAddress);
//
//                streamConnection.close();
//
//            } catch (IOException c) {
//                c.printStackTrace();
//            }
//        }
//        if (e.getSource() == sendButton) {
//            try {
//                String remoteDeviceAddress = printLine2.getText();
//                String connectionURL = "btspp://" + remoteDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";
//                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);
//
//                File fileToSend = new File("logins.txt");
//                FileInputStream fileInputStream = new FileInputStream(fileToSend);
//                OutputStream outputStream = streamConnection.openOutputStream();
//
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//
//                fileInputStream.close();
//                outputStream.close();
//                streamConnection.close();
//            } catch (IOException c) {
//                c.printStackTrace();
//            }
//        }
//        if (e.getSource() == receiveButton) {
//            try {
//                String remoteDeviceAddress = printLine2.getText();
//                String connectionURL = "btspp://" + remoteDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";
//                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);
//
//                File receivedFile = new File("logins.txt");
//                FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
//                InputStream inputStream = streamConnection.openInputStream();
//
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    fileOutputStream.write(buffer, 0, bytesRead);
//                }
//
//                fileOutputStream.close();
//                inputStream.close();
//                streamConnection.close();
//            } catch (IOException c) {
//                c.printStackTrace();
//            }
//        }
        if (e.getSource() == sendButton) {
            try {
                // Create a connection URL
                String url = "btspp://localhost:" + serverUUID + ";name=FileServer";

                // Open a connection
                StreamConnectionNotifier streamConnectionNotifier = (StreamConnectionNotifier) Connector.open(url);
                StreamConnection streamConnection = streamConnectionNotifier.acceptAndOpen();

                // Read file and send it
                FileInputStream fileInputStream = new FileInputStream(filePath);
                OutputStream outStream = streamConnection.openOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                // Clean up
                fileInputStream.close();
                outStream.close();
                streamConnection.close();
                streamConnectionNotifier.close();
                System.out.println("File sent successfully.");
            } catch (Exception c) {
                c.printStackTrace();
            }
        }
        if (e.getSource() == receiveButton) {
            try {
                // Create a connection URL
                String url = "btspp://localhost:" + serverUUID + ";name=FileServer";

                // Open a connection
                StreamConnection streamConnection = (StreamConnection) Connector.open(url);
                InputStream inStream = streamConnection.openInputStream();

                // Create a file to store received data
                FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                // Clean up
                fileOutputStream.close();
                inStream.close();
                streamConnection.close();
                System.out.println("File received successfully.");
            } catch (Exception c) {
                c.printStackTrace();
            }
        }
    }
}
