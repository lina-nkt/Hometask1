package NikitinaAA.chat.client;

import java.io.*;



public class ReadThread extends Thread {
            //implements Runnable{         Задание 3

    private final BufferedReader in;

    ReadThread(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    /*ReadThread(BufferedReader in){
        this.in = in;
    }*/

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                System.out.println("Received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


