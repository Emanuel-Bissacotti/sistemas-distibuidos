package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server {
    public static void main(String[] args) {
        try {
            int portaServidor = 50000;
            ServerSocket server = new ServerSocket(portaServidor);
            System.out.println("Servidor ouvindo a porta: " + portaServidor);

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());

                // Cria uma nova thread para lidar com o cliente
                new Thread(new ClientHandler(client)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());

            ArrayList<Integer> list = (ArrayList<Integer>) entrada.readObject();
            Collections.sort(list);

            saida.flush();
            saida.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
