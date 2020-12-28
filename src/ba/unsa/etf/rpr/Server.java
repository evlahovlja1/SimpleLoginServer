package ba.unsa.etf.rpr;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void acceptClients() throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //samo testirali da li thread connectionRunnable fkt radi 2 sekunde
        Future<?> futur = null;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //blocking poziv
                Socket clientSocket = serverSocket.accept();
                //ne cekaj pun buffer
                clientSocket.setTcpNoDelay(true);

                ConnectionRunnable connectionRunnable = new ConnectionRunnable(clientSocket);

                if (futur == null) {
                    futur = threadPool.submit(connectionRunnable);
                }
                else {
                    threadPool.execute(connectionRunnable);
                }

                System.out.println(futur.isDone());
            }
        } finally {
            threadPool.shutdown();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
