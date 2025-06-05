package com.senai;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class Calculadora implements ActionListener {
    private JTextField txt;
    private String s0, s1, s2;

    static ArrayList<String> historicoCalculos = new ArrayList<>();

    public Calculadora(JFrame janela) {
        s0 = s1 = s2 = "";
        txt = new JTextField(16);
        txt.setPreferredSize(new Dimension(340, 100));
        txt.setEditable(false);
        janela.add(txt, BorderLayout.NORTH);
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4));

        //Números.
        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        //Símbolos.
        JButton bSum = new JButton("+");
        JButton bMinus = new JButton("-");
        JButton bDiv = new JButton("/");
        JButton bMulti = new JButton("*");
        JButton bEqual = new JButton("=");
        JButton bClear = new JButton("C");
        JButton bPoint = new JButton(".");
        JButton bQuadNum = new JButton("x²");
        JButton bRaizNum = new JButton("Raiz");
        //Botão surpresa do programa.
        JButton bHist = new JButton("🐻");

        // Adicionar os botões ao painel

        //linha 1
        painel.add(bQuadNum);
        painel.add(bRaizNum);
        painel.add(bClear);
        painel.add(bHist);

        //linha 2
        painel.add(b7);
        painel.add(b8);
        painel.add(b9);
        painel.add(bMulti);

        //linha 3
        painel.add(b4);
        painel.add(b5);
        painel.add(b6);
        painel.add(bMinus);

        //linha 4
        painel.add(b1);
        painel.add(b2);
        painel.add(b3);
        painel.add(bSum);

        //linha 5
        painel.add(bDiv);
        painel.add(b0);
        painel.add(bPoint);
        painel.add(bEqual);

        // Adicionar o painel de botões ao frame
        janela.add(painel, BorderLayout.CENTER);

        // Adicionar os action listeners
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        bSum.addActionListener(this);
        bMinus.addActionListener(this);
        bDiv.addActionListener(this);
        bMulti.addActionListener(this);
        bEqual.addActionListener(this);
        bClear.addActionListener(this);
        bPoint.addActionListener(this);
        bRaizNum.addActionListener(this);
        bQuadNum.addActionListener(this);
        bHist.addActionListener(this);

        //Adicionando o uso do teclado para o uso da calculadora.
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char keyChar = e.getKeyChar();

                //Se a tecla digitada for um número.
                if ((keyChar >= '0' && keyChar <= '9') ||
                        keyChar == '.') {
                    if (!s1.isEmpty()) {
                        s2 = s2 + keyChar;
                    } else {
                        s0 = s0 + keyChar;
                    }
                    txt.setText(s0 + s1 + s2);

                    //Se a tecla digitada for um operador lógico.
                } else if (keyChar == '+' || keyChar == '-' ||
                        keyChar
                                == '*' || keyChar == '/') {
                    if (!s0.isEmpty()) {
                        s1 += keyChar;
                    }
                    txt.setText(s0 + s1 + s2);

                    //Se a tecla digitada for "C" para limpar.
                } else if (keyChar == 'c') {
                    s0 = s1 = s2 = "";

                    //Se a tecla digitada for o apagar.
                } else if (keyChar == KeyEvent.VK_BACK_SPACE) {
                    if (!s2.isEmpty()) {
                        s2 = s2.substring(0, s2.length() - 1);
                    } else if (!s1.isEmpty()) {
                        s1 = s1.substring(0, s1.length() - 1);
                    } else if (!s0.isEmpty()) {
                        s0 = s0.substring(0, s0.length() - 1);
                    }
                    txt.setText(s0 + s1 + s2);

                    //Se a tecla digitada for o enter.
                } else if (keyChar == KeyEvent.VK_ENTER) {
                    if (!s0.isEmpty() && !s1.isEmpty() &&
                            !s2.isEmpty()) {
                        double result = 0;
                        if (s1.equals("+"))
                            result = (Double.parseDouble(s0) +
                                    Double.parseDouble(s2));
                        else if (s1.equals("-"))
                            result = (Double.parseDouble(s0) -
                                    Double.parseDouble(s2));
                        else if (s1.equals("/"))
                            result = (Double.parseDouble(s0) /
                                    Double.parseDouble(s2));
                            if (Double.parseDouble(s2) == 0) {
                                s0 = "Não é possivel efetuar a divisão por 0.";
                                s1 = s2 = "";
                            }
                        else if (s1.equals("*"))
                            result = (Double.parseDouble(s0) *
                                    Double.parseDouble(s2));
                        s0 = String.valueOf(result);

                        s1 = s2 = "";
                    }
                }
                txt.setText(s0 + s1 + s2);
            }
        });
    }

    //Método voltado para o uso dos botões da calculadora.
    @Override
    public void actionPerformed(ActionEvent e) {
        String type = e.getActionCommand();

        //Leitura do botão dos números.
        if ((type.charAt(0) >= '0' && type.charAt(0) <= '9') ||
                type.charAt(0) == '.') {
            if (!s1.equals("")) {
                s2 = s2 + type;
            } else {
                s0 = s0 + type;
            }
            txt.setText(s0 + s1 + s2);

            //Leitura do botão de limpar.
        } else if (type.charAt(0) == 'C') {
            s0 = s1 = s2 = "";
            txt.setText(s0 + s1 + s2);

            //Leitura do botão de igual.
        } else if (type.charAt(0) == '=') {
            double store;
            if (s1.equals("+")) {
                store = (Double.parseDouble(s0) +
                        Double.parseDouble(s2));
            } else if (s1.equals("-")) {
                store = (Double.parseDouble(s0) -
                        Double.parseDouble(s2));
            } else if (s1.equals("/")) {
                store = (Double.parseDouble(s0) /
                        Double.parseDouble(s2));
                if (Double.parseDouble(s2) == 0) {
                    s0 = "Não é possivel efetuar a divisão por 0.";
                    s1 = s2 = "";
                }
            } else {
                store = (Double.parseDouble(s0) *
                        Double.parseDouble(s2));
            }
            txt.setText(s0 + s1 + s2 + "=" + store);
            s0 = Double.toString(store);
            s1 = s2 = "";

            //Leitura do botão do valor quadrado.
        } else if (type.equals("x²")) {
            double resultQuad;
            resultQuad = (Double.parseDouble(s0) *
                    Double.parseDouble(s0));
            txt.setText("" + resultQuad);

            //Leitura do botão da raiz quadrada.
        } else if (type.equals("Raiz")) {
            double numRaiz = Double.parseDouble(s0);
            double resultRaiz = Math.sqrt(numRaiz);
            txt.setText("" + resultRaiz);

            //Leitura do botão de sair.
        } else if (type.equals("🐻")) {
            //System.exit(0);

            // Tamanho da janela
            int larguraJanela = 340;
            int alturaJanela = 400;

            JFrame janelaImagem = new JFrame("Surpresinha");
            janelaImagem.setSize(larguraJanela, alturaJanela);
            janelaImagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Caminho da imagem
            ImageIcon imagemOriginal = new ImageIcon("src/catatau.jpg");

            // Pega o objeto Image da imagem original
            Image imagem = imagemOriginal.getImage();

            // Redimensiona a imagem para caber na janela (ajusta com escala proporcional)
            Image imagemRedimensionada = imagem.getScaledInstance(
                    larguraJanela, alturaJanela, Image.SCALE_SMOOTH
            );

            // Cria novo ImageIcon com a imagem redimensionada
            ImageIcon imagemFinal = new ImageIcon(imagemRedimensionada);

            // JLabel para exibir a imagem redimensionada
            JLabel labelImagem = new JLabel(imagemFinal);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            labelImagem.setVerticalAlignment(SwingConstants.CENTER);

            // Usamos um JPanel com layout para centralizar
            JPanel painel = new JPanel(new BorderLayout());
            painel.add(labelImagem, BorderLayout.CENTER);

            // Adiciona o painel à janela
            janelaImagem.add(painel);

            // Torna a janela visível
            janelaImagem.setVisible(true);

            //Leitura dos valores para as equações.
        } else {
            if (s1.equals("") || s2.equals(""))
                s1 = type;
            else {
                double te;
                if (s1.equals("+")) {
                    te = Double.parseDouble(s0) + Double.parseDouble(s2);
                } else if (s1.equals("-")) {
                    te = Double.parseDouble(s0) - Double.parseDouble(s2);
                } else if (s1.equals("/")) {
                    if (s2.equals("0")) {
                        System.out.println("Não é possivel dividir por 0.");
                        te = 0;
                        s1 = s2 = "";
                    } else {
                        te = Double.parseDouble(s0) / Double.parseDouble(s2);
                    }
                } else {
                    te = Double.parseDouble(s0) * Double.parseDouble(s2);
                }
                s0 = Double.toString(te);
                s1 = type;
                s2 = "";
            }
            txt.setText(s0 + s1 + s2);
        }
    }
}