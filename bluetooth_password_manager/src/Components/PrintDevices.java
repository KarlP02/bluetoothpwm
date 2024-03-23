package Components;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class PrintDevices implements ActionListener {
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
        if (e.getSource() == connectButton) {
            try {
                String remoteDeviceAddress = printLine2.getText();
                String connectionURL = "btspp://" + remoteDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";
                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);

                System.out.println("Connected to: " + remoteDeviceAddress);

                if (e.getSource() == sendButton) {
                    File fileToSend = new File("logins.txt");
                    FileInputStream fileInputStream = new FileInputStream(fileToSend);
                    OutputStream outputStream = streamConnection.openOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                if (e.getSource() == receiveButton) {
                    File fileToSend = new File("logins.txt");
                    FileInputStream fileInputStream = new FileInputStream(fileToSend);
                    OutputStream outputStream = streamConnection.openOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

//                streamConnection.close();

            } catch (IOException c) {
                c.printStackTrace();
            }
        }
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
//                InputStream inputStream = streamConnection.openInputStream();
//
//                File receivedFile = new File("logins.txt");
//                FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
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

    }
}
