/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.generateemails.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author emanuel
 */
public class Pessoa implements Serializable{
    private String nome;
    private String email;
    
    public Pessoa (String nome){
        this.nome = nome;
    }
    
    public Pessoa(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        return Objects.equals(this.email, other.email);
    }
    
    public boolean generateEmail(ArrayList<Pessoa> pessoas){
        String[] split = this.nome.toLowerCase().split(" ");
        this.email = split[0] + "." + split[1] + "@ufn.edu.br";
        if(pessoas.contains(this)){
            return false;
        }
        pessoas.add(this);
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", email=" + email + '}';
    }
    
    
}
