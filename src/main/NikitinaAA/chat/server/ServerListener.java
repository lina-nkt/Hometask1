package NikitinaAA.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListener {

    private static ArrayList<ClientHandler> clients;
    private ServerSocket serverSocket;

    /*private ExecutorService executorService;
                *Задание 3*/

    public void start() throws IOException {

        serverSocket = new ServerSocket(29016);

        System.out.println("Server started. Waiting for clients...");

        ChatLog log = new ChatLog();

        /*executorService = Executors.newFixedThreadPool(5);
                        Задание 3
         */

        while (true){
                Socket incomingConnection = serverSocket.accept();

                ClientHandler client = new ClientHandler(incomingConnection, log);

                clients.add(client);

                Thread clientThread = new Thread(client);
                clientThread.start();

                /*executorService.execute(client);
                            *Задание 3 */
            }
        }

    public ServerListener(){
        clients = new ArrayList<>();
    }

    public static List<ClientHandler> getClients(){
        return clients;
    }

    public static void removeClient(ClientHandler clientHandler){
        System.out.println("Client "+clientHandler+" is deleted");
        clients.remove(clientHandler);
    }

}