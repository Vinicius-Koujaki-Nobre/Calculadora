package com.senai;

//Usado no JFrame.
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Titulo.
        JFrame janela = new JFrame("Calculadora");

        //Ações relacionadas a janela.
        janela.setBounds(0, 0, 340, 550);

        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Chamando a calculadora.
        new Calculadora(janela);

        //Janela vísivel.
        janela.setVisible(true);
    }
}