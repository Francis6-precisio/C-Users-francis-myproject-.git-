package Client;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BroadcastClient {

    public InetAddress address;

    public byte[] buffer;

    public int port = 5000;

    public String str, str2;

    public DatagramPacket packet;

    public MulticastSocket socket;

    public BroadcastClient() throws Exception{
        socket = new MulticastSocket(port);
        System.out.println("Waiting for messages from the server");
        transmit();
    }
    public void transmit(){
        try{
            // Create a MulticastSocket object to receive data from the group using the port number
            address = InetAddress.getByName("192.168.2.255");
            // Registering the client at the group 233.0.0.1
            socket.joinGroup(address);

            while (true){
                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);
                //receive data from the server
                socket.receive(packet);
                str = new String(packet.getData());
                System.out.println("Receive message : " + str);
            }

        } catch (Exception e){
            System.out.println("Error");
        } finally {
            try{
                // Remove the client from the group
                socket.leaveGroup(address);
                // Close the socket object
                socket.close();
            } catch (Exception e){
                System.out.println("Error " + e);
            }
        }
    }
}
