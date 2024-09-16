/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.generateemails.client;

import com.mycompany.generateemails.entity.Pessoa;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author emanuel
 */
public class Client {
    public static void main(String[] args) {
        try {
            int porta = 50000;
            Socket cliente = new Socket("localhost",porta);

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            
            
            Pessoa pessoa = new Pessoa(JOptionPane.showInputDialog(null, "Informe o seu nome"));
            saida.writeObject(pessoa);
            
            Object pessoaRecebida = (Object) entrada.readObject();
            if (pessoaRecebida instanceof Pessoa) {
                Pessoa pessoaInstanciada = (Pessoa) pessoaRecebida;
                System.out.println("Email criado:" + ((Pessoa) pessoaRecebida).getEmail());
            } else {
                System.out.println(pessoaRecebida);
            }
            
            entrada.close();
            saida.close();
            System.out.println("Conexão encerrada");
        }
        catch(HeadlessException | IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
  }
}
