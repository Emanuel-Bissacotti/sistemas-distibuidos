/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.generateemails.server;

import com.mycompany.generateemails.entity.Pessoa;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author emanuel
 */
public class Server {
    public static void main(String[] args) {
        try {
            int portaServidor = 50000;
            ServerSocket servidor = new ServerSocket(portaServidor);
            System.out.println("Servidor ouvindo a porta: " + portaServidor);
            Socket cliente; //procurador do cliente no lado do servidor
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            
            while (true) {
                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                cliente = servidor.accept();
                
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                try{
                    Pessoa pessoa = (Pessoa) entrada.readObject();
                    if(pessoa.generateEmail(pessoas)){
                        saida.flush();
                        saida.writeObject(pessoa);
                    }else {
                        saida.writeObject("Pessoa ja na lista de emails");
                    }
                    for (Pessoa pessoa1 : pessoas) {
                        System.out.println(pessoa);
                    }
                    saida.close();
                    entrada.close();
                    cliente.close();
                } catch(Exception e){
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
    }
}
