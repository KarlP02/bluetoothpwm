package Components;

import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.*;

public class SendAndReceive {
    public void SendFile(UUID uuid, File fileToSend) {
        try {
            String connectionURL = "btspp://localhost:" + uuid.toString() + ";authenticate=false;encrypt=false;name=FileServer";
            StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(connectionURL);

            System.out.println("Waiting for connection...");
            StreamConnection connection = notifier.acceptAndOpen();
            RemoteDevice remoteDevice = RemoteDevice.getRemoteDevice(connection);
            System.out.println("Client connected " + remoteDevice.getFriendlyName(false));

            OutputStream outputStream = connection.openOutputStream();

            FileInputStream fileInputStream = new FileInputStream(fileToSend);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
            fileInputStream.close();
            outputStream.close();
            connection.close();
            notifier.close();
        } catch (Exception c) {
            c.printStackTrace();
        }
    }

    public void ReceiveFile(String remoteDeviceAddress, File fileToReceive) {
        try {
            String connectionURL = "btspp://" + remoteDeviceAddress + ":4;authenticate=false;encrypt=false;master=false";
            StreamConnection streamConnection = (StreamConnection) Connector.open(connectionURL);

            InputStream inputStream = streamConnection.openInputStream();

            FileOutputStream fileOutputStream = new FileOutputStream(fileToReceive);
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
