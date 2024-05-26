package NikitinaAA.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    private final ChatLog chatLog;

    private BufferedReader in;

    private BufferedWriter out;
    private String nickName;
    public ClientHandler(Socket clientSocket, ChatLog chatLog){
        this.clientSocket = clientSocket;
        this.chatLog = chatLog;
    }

    @Override
    public void run() {
        try {

            in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            this.nickName = in.readLine();

            chatLog.put(nickName + " connected to chat", this);

            while (!Thread.currentThread().isInterrupted()) {
                String message = in.readLine();

                if(Objects.isNull(message)){
                    break;
                }

                System.out.println(nickName + ":" + message);

                chatLog.put(nickName + ":" + message, this);
            }
            chatLog.put(nickName + " disconnected from chat", this);
        } catch (IOException e){
            e.printStackTrace();
        } /*finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerListener.removeClient(this);
        }
                    Задание 3*/

        ServerListener.removeClient(this);
    }

    public void sendMessageToClient(String msg) throws IOException {
        if(!clientSocket.isOutputShutdown()) {
            out.write(msg);
            out.newLine();
            out.flush();
        }
    }
    @Override
    public String toString() {
        return nickName;
    }
}