package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur extends Thread {
    int PORT=5000;
    ServerSocket socketserver;
    int nbClients;

    @Override
    public void run(){
        try{
            //creer un socket serveur avec un numéro de port 234
             socketserver = new ServerSocket(PORT);
            while (true){
                //ecoutes pour une connexion à apporter à ce socket et l'accepte
                Socket s = socketserver.accept(); // attend la connexion des clients à n'importe quelle moment
                ++nbClients;
                new conversation(s,nbClients).start();
            }
        } catch (IOException e){
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class conversation extends Thread{
        private Socket socketduserveur;
        private int numeroClient;
        BufferedReader in;
        PrintWriter out;
        final Scanner sc = new Scanner(System.in); //pour lire à partir du clavier

        public conversation(Socket socket, int num){
            super();
            this.socketduserveur = socket;
            this.numeroClient = num;
        }

        @Override
        public void run(){
            try{
                //flux pour envoyer
                out = new PrintWriter(socketduserveur.getOutputStream());
                //flux pour recevoir
                in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
                PrintWriter pw = new PrintWriter(out, true);

                System.out.println("Connexion du client numéro " + numeroClient);
                pw.println("Bienvenue, vous êtes le client numéro " + numeroClient);


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
                            System.out.println("Client : " + msg);
                        }
                    }
                });
                recevoir.start();
            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }

/*    public Serveur(){
        System.out.println("Lancement du Serveur...");
        PORT = 5000;
         Thread connexion = new Thread(new Runnable() {
            @Override
            public void run() {
                //creer un socket serveur avec un numero de port 5000
                try {
                    socketserver = new ServerSocket(PORT);
                    while (true){
                        //ecoutes pour une connexion à apporter à ce socket et l'accepte
                        socketduserveur = socketserver.accept();
                        Thread traitement = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    //flux pour envoyer
                                    out = new PrintWriter(socketduserveur.getOutputStream());
                                    //flux pour recevoir
                                    in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
                                }catch (IOException e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        traitement.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connexion.start();
    }
*/

    /*
    * il faut creer deux processus pour l'envoi et la reception
    * elles se font simultanément cela nous permet d'envoyer et de recevoir des messages en même temps
     */

    /*public void conversation(){
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
                    System.out.println("Client : " + msg);
                }
            }
        });
        recevoir.start();
    }*/
}
