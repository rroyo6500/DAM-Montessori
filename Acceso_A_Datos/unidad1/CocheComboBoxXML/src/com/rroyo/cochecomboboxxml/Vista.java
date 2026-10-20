package com.rroyo.cochecomboboxxml;

import com.rroyo.cochecomboboxxml.base.Coche;

import javax.swing.*;
import java.util.LinkedList;

public class Vista {

    private JFrame frame;

    private JPanel P_CCBXML;
    private JTextField JTF_marcaCoche;
    private JLabel JL_marcaCoche;
    private JLabel JL_modeloCoche;
    private JTextField JTF_modeloCoche;
    private JComboBox JComboBox;
    private JButton BTN_altaCoche;
    private JButton BTN_mostrarCoche;
    private JLabel JL_coche;

    private LinkedList<Coche> lista;
    private DefaultComboBoxModel<Coche> dcbm;

    public Vista() {
        frame = new JFrame("Vista");
        frame.setContentPane(P_CCBXML);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Vista vista = new Vista();
    }

}
