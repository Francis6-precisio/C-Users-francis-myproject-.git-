package Server;

import java.io.IOException;

public class MainServer   {
    public static void main(String[] args) throws IOException {
        //1- new Serveur().start();
         UDPserver udpS = new UDPserver();
         udpS.chatServer();
        BroadcastServer server = new BroadcastServer();

    }
}
