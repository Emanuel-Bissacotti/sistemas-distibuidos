/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sendList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class SendList {
    private int porta;
    private String address;
    private Socket client;



    public SendList(int porta, String address) {
        this.porta = porta;
        this.address = address;
        try{
            this.client = new Socket(this.address, this.porta);
        } catch(Exception e){
            System.out.println("e");
            
        }
    }
    
    public ArrayList<Integer> orderList(ArrayList<Integer> desorderList){
        ArrayList<Integer> sortList = new ArrayList<Integer>();
        try {
            ObjectInputStream entrada = new ObjectInputStream(this.client.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(this.client.getOutputStream());
            saida.writeObject(desorderList);
            
            sortList = (ArrayList<Integer>) entrada.readObject();
            return sortList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return sortList;
    }
}
