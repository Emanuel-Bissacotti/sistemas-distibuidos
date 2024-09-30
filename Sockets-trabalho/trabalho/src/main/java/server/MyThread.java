/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Emanuel
 */

class MyThread implements Runnable {
    private Socket client;

    public MyThread(Socket client) {
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
