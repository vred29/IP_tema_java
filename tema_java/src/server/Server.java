package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        // NU UITATI SA MODIFICATI RUN CONFIG
        int port = Integer.parseInt(args[0]);

        try{
            ServerSocket server = new ServerSocket(port);
            server.setReuseAddress(true);

            while (true){
                // socket obj to receive incoming client
                Socket client = server.accept();

                // signal when a client connected
                System.out.println("New client connected "+client.getInetAddress().getHostAddress());

                MyThread clientHandler = new MyThread(client);
                clientHandler.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
