package ba.unsa.etf.rpr;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            //aplikacija ce se pokretati preko terminala
            //pa ovaj hook hvata CTRL C da ugasi server socket
            Server server = new Server(12345);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("EXIT HOOK");
                try {
                    server.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            server.acceptClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
