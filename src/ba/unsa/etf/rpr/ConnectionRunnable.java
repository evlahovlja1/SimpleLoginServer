package ba.unsa.etf.rpr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionRunnable implements Runnable {
    //dovoljan je package level
    ObjectOutputStream out;
    ObjectInputStream in;
    Socket clientSocket;

    public ConnectionRunnable(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        //out = new ObjectOutputStream(clientSocket.getOutputStream());
        //in = new ObjectInputStream(clientSocket.getInputStream());
    }


    @Override
    public void run() {
        System.out.println("Sa 2 sekunde pauze, pozz iz: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
