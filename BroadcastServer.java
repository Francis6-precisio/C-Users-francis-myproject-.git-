package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastServer {

    BufferedReader in = null;
    String str = null;
    byte[] buffer;
    DatagramPacket packet;
    InetAddress address;
    int port = 5000;
    DatagramSocket socket;

    public BroadcastServer() throws IOException{
        System.out.println("Sending messages");
        // Create DatagramSocket object to receive client requests
        socket = new DatagramSocket();
        // Invoke the transmit() method to multicast a message to all the clients registered on a
        transmit();
    }

    public void transmit(){
        try{
            // Create an input stream to receive data from the console
            in = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                //System.out.println("String to transmit to all the clients is Morning ooohhhh");
                str = "Morning ooohhhh";
                buffer = str.getBytes();
                address = InetAddress.getByName("192.168.2.255");

                // Send the datagram packet at the port number 1502
                packet = new DatagramPacket(buffer, buffer.length, address, port);

                // Send the message to all the clients registered at group 233.0.0.1
                socket.send(packet);
            }
        } catch (Exception e){
            System.out.println("ERROR2: " + e);
        } finally {
            try{
                // Close the stream and the socket
                in.close();
                socket.close();
            } catch (Exception e){
                System.out.println("ERROR3: " + e);
            }
        }

    }
}
