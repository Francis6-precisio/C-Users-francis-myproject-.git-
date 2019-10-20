package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPserver {
    int PORT=5000;
    boolean bye = false;
    byte[] receivebuffer = new byte[1024];
    byte[] sendbuffer = new byte[1024];

    public void UDPserver() throws  IOException {

    }
    public void chatServer()throws SocketException, IOException{
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        while (true){


            DatagramPacket recvpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
            serverSocket.receive(recvpkt);
            InetAddress IP = recvpkt.getAddress();
            int noPort = recvpkt.getPort();

            String clientdata = new String(recvpkt.getData());
            System.out.println("\nClient : " + clientdata);
            System.out.print("\nServer : ");
            BufferedReader serverRead = new BufferedReader(new InputStreamReader(System.in));
            String serverdata = serverRead.readLine();

            sendbuffer = serverdata.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, noPort);
            serverSocket.send(sendPacket);
            //here the check condition for serverdata which must be "bye"
            if (serverdata.equalsIgnoreCase("bye")){
                System.out.println("connection ended by server");
                break;
            }
        }
        serverSocket.close();

    }

}
