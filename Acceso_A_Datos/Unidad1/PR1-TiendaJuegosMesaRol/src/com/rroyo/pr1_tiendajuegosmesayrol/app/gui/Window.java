package com.rroyo.pr1_tiendajuegosmesayrol.app.gui;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;

import javax.swing.*;

public class Window {
    JFrame frame;

    private JPanel panel;
    public JComboBox cbSavedGamesSelector;
    public JTextField tfGameName;
    public JTextArea tfGameDescription;
    public JRadioButton rbTypeGameTable;
    public JRadioButton rbTypeGameRol;

    public Window() {
        frame = new JFrame("Tienda de Juegos de mesa y Rol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();

        setMenu();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setMenu() {
        JMenuBar bar =  new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem imp = new JMenuItem("Import");
        JMenuItem exp = new JMenuItem("Export");

        imp.setActionCommand("import");
        exp.setActionCommand("export");

        menu.add(imp);
        menu.add(exp);
        bar.add(menu);
        frame.setJMenuBar(bar);
    }

    public void setData(GameStore gameStoreData) {
        for (Game game : gameStoreData.getGameList()) {
            
        }
    }
}
