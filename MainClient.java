package Client;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException, Exception {
        //1-ClientMt customer = new ClientMt();
        //2-customer.conversation();
        UDPclient udpC = new UDPclient();
        udpC.chatClient();
        BroadcastClient client = new BroadcastClient();
    }

}
