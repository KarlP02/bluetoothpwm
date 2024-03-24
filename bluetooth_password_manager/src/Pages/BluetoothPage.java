package Pages;

import Components.BluetoothDiscover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BluetoothPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton findDevices = new JButton("find");

    BluetoothPage() {
        findDevices.setBounds(10, 10, 100, 25);
        findDevices.setFocusable(false);
        findDevices.addActionListener(this);

        frame.add(findDevices);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 630);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findDevices) {
            BluetoothDiscover bluetoothDiscover = new BluetoothDiscover(frame);
        }
    }
}
