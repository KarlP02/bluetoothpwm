package Components;

import javax.bluetooth.*;
import javax.swing.*;
import java.util.ArrayList;

public class BluetoothDiscover {
    JLabel holdAddress = new JLabel();

    public BluetoothDiscover(JFrame frame) {
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent discoveryAgent = localDevice.getDiscoveryAgent();

            ArrayList<String> devices = new ArrayList<>();
            ArrayList<String> deviceAddress = new ArrayList<>();
            DiscoveryListener discoveryListener = new DiscoveryListener() {
                @Override
                public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
                    try {
                        devices.add(remoteDevice.getFriendlyName(false));
                        deviceAddress.add(remoteDevice.getBluetoothAddress());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void inquiryCompleted(int discType) {
                    System.out.println("Inquiry completed!");
                    synchronized (BluetoothDiscover.class) {
                        BluetoothDiscover.class.notify();
                    }
                }

                @Override
                public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                    // Not used
                }

                @Override
                public void serviceSearchCompleted(int transID, int respCode) {
                    // Not used
                }
            };

            discoveryAgent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);

            synchronized (BluetoothDiscover.class) {
                BluetoothDiscover.class.wait();
            }

            discoveryAgent.cancelInquiry(discoveryListener);

            int i = 0;
            int j = 0;
            int height = 50;
            for (String device : devices) {
                PrintDevices printDevices = new PrintDevices(i, j, height, device, deviceAddress, frame);
                j += 25;
                i++;
            }

            frame.invalidate();
            frame.validate();
            frame.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
