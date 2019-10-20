package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPclient {
    String add = "127.0.0.1";
    int PORT=5000;
    byte[] sendbuffer = new byte[1024];
    byte[] receivebuffer = new byte[1024];

    public void UDPclient()throws SocketException, IOException {

    }

    public void chatClient() throws SocketException, IOException{
        InetAddress IP = InetAddress.getByName(add);
        BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        while (true){


            System.out.print("\nClient : ");
            String clientData = clientRead.readLine();
            sendbuffer = clientData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, PORT);
            clientSocket.send(sendPacket);

            if (clientData.equalsIgnoreCase("bye")){
                System.out.println("Connection ended by client");
                break;
            }
            DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
            clientSocket.receive(receivePacket);
            String serverData = new String(receivePacket.getData());
            System.out.println("\nServer : " + serverData);

            //checking condition for equals with serverData which is my string
        }
        clientSocket.close();

    }

}
