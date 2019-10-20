package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import Server.Serveur;


public class ClientMt {
    int PORT;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    final Scanner sc = new Scanner(System.in); //pour lire à partir du clavier

    public ClientMt(){
        PORT = 5000;
        try{
            /**
             * contenant les informations du serveur (port et adresse IP ou nom d'hôte
             * 127.0.0.1 est l'adresse local de la machine
             */
            socket = new Socket("192.168.2.49", PORT);

            //flux pour envoyer
            out = new PrintWriter(socket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
     * il faut creer deux processus pour l'envoi et la reception
     * elles se font simultanément cela nous permet d'envoyer et de recevoir des messages en même temps
     */
    public void conversation(){
        Thread envoyer = new Thread(new Runnable() {

            String msg;
            @Override
            public void run() {
                //TODO Auto-generated method stub
                //On considère que l'envoi et la réception des messages
                //sont infinis, on le fait avec la boucle while
                while (true){

                    msg = sc.nextLine();

                    out.println(msg);
                    //System.out.print("Client : ");
                    out.flush();

                }

            }
        });
        envoyer.start();

        Thread recevoir = new Thread(new Runnable() {

            String msg;
            @Override
            public void run() {
                //TODO Auto-generated method stub
                //On considère que l'envoi et la réception des messages
                //sont infinis, on le fait avec la boucle while
                while (true){
                    try{
                        msg = in.readLine();
                    } catch (IOException e){
                        //TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Serveur : " + msg);
                }
            }
        });
        recevoir.start();
    }



}
