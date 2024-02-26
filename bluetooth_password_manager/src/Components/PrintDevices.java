package Components;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PrintDevices implements ActionListener {
    JLabel label = new JLabel("device:");
    JButton connectButton = new JButton("connect");
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

        frame.add(label);
        frame.add(printLine);
        frame.add(printLine2);
        frame.add(connectButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton) {
            try {
                // Replace this with the actual Bluetooth address of the device you want to connect to
                String remoteDeviceAddress = printLine2.getText(); // Replace with the device's Bluetooth address

                // Create a Bluetooth URL for the device
                String connectionURL = "btspp://" + remoteDeviceAddress + ":1;authenticate=false;encrypt=false;master=false";

                // Establish the connection
                StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);

                // Now, you can use the 'streamConnection' to send/receive data with the connected device
                System.out.println("Connected to: " + remoteDeviceAddress);

                // Close the connection when done
//                streamConnection.close();
//                System.out.println("Connection closed");

            } catch (IOException c) {
                c.printStackTrace();
            }
        }

    }
}
