package com.rroyo.pr1_tiendajuegosmesayrol.gui.windows;

import javax.swing.*;

public class Dashboard {

    private final JFrame frame;

    private JPanel dashboardPanel;

    public Dashboard() {
        frame = new JFrame("Tienda de Juegos de mesa y Rol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(dashboardPanel);
        frame.pack();


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
