package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            int portaServidor = 50000;
            ServerSocket server = new ServerSocket(portaServidor);
            System.out.println("Servidor ouvindo a porta: " + portaServidor);

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());

                new Thread(new MyThread(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

