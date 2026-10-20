package com.rroyo.mvc;

import javax.swing.*;

public class Vista {

    private JFrame frame;
    private JMenuItem itemAbrir, itemGuardar;

    private JPanel panel;
    private JTextArea textArea;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        generarMenu();

        frame.setVisible(true);
    }

    private void generarMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemGuardar = new JMenuItem("Guardar");
        itemAbrir = new JMenuItem("Abrir");

        itemGuardar.setActionCommand("guardar");
        itemAbrir.setActionCommand("abrir");

        menu.add(itemGuardar);
        menu.add(itemAbrir);

        bar.add(menu);
        frame.add(bar);
    }
}
