package com.rroyo.pr1_tiendajuegosmesayrol.app.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;

import javax.swing.*;

public class Window {

    public final JFrame frame;

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
    public DatePicker dpDateSelector;
    public JRadioButton rbTypeGameVideogame;
    public JTextField tfPrice;

    public Window() {
        frame = new JFrame("Tienda de Juegos");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
