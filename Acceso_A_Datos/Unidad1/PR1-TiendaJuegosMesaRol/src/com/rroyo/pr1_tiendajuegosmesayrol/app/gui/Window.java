package com.rroyo.pr1_tiendajuegosmesayrol.app.gui;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;

import javax.swing.*;

public class Window {

    private final JFrame frame;

    public JMenuItem exp;
    public JMenuItem imp;

    private JPanel panel;
    public JComboBox<Game> cbSavedGamesSelector;
    public JTextField tfGameName;
    public JTextArea tfGameDescription;
    public JRadioButton rbTypeGameTable;
    public JRadioButton rbTypeGameRol;
    public JButton bLoadGame;
    public JButton bSave;

    public Window() {
        frame = new JFrame("Tienda de Juegos de mesa y Rol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();

        setMenu();

        bSave.setActionCommand("button_save");
        bLoadGame.setActionCommand("button_load");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setMenu() {
        JMenuBar bar =  new JMenuBar();
        JMenu menu = new JMenu("File");
        imp = new JMenuItem("Import");
        exp = new JMenuItem("Export");

        imp.setActionCommand("menu_import");
        exp.setActionCommand("menu_export");

        menu.add(imp);
        menu.add(exp);
        bar.add(menu);
        frame.setJMenuBar(bar);
    }

}
