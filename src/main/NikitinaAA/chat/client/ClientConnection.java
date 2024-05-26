package NikitinaAA.chat.client;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

public class ClientConnection {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 29016;
    private static ExecutorService executorService;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Подключение к серверу " + SERVER_HOST + ":" + SERVER_PORT);

            /*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            executorService = Executors.newFixedThreadPool(5);

            executorService.execute(new ReadThread(in));
            executorService.execute(new WriteThread(out));
                            Задание 3
            */

            //Поток для отправки сообщений
            WriteThread writeThread = new WriteThread(socket.getOutputStream());
            writeThread.start();

            //Поток для получения и вывода сообщений
            ReadThread readThread = new ReadThread(socket.getInputStream());
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            shutdownExecutorService();
        }
    }
    private static void shutdownExecutorService() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
}