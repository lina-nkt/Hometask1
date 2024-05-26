package NikitinaAA.chat.client;

import java.io.*;

public class WriteThread extends Thread{
        //implements Runnable{          Задание 3
    private final PrintWriter out;

    //private BufferedWriter out;       Задание 3

    WriteThread(/*BufferedWriter*/ OutputStream out) {
        this.out = new PrintWriter(out, true);
        //this.out = out;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String message = reader.readLine();

                /*out.write(message);
                out.newLine();
                out.flush();*/

                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}