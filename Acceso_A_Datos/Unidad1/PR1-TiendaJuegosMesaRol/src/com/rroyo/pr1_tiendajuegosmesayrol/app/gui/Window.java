package com.rroyo.pr1_tiendajuegosmesayrol.app.gui;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;

import javax.swing.*;

public class Window {
    JFrame frame;

    private JPanel panel;
    public JComboBox<Game> cbSavedGamesSelector;
    public JTextField tfGameName;
    public JTextArea tfGameDescription;
    public JRadioButton rbTypeGameTable;
    public JRadioButton rbTypeGameRol;
    public JButton bLoadGame;
    public JButton bSave;
    public JButton bSaveNew;

    public Window() {
        frame = new JFrame("Tienda de Juegos de mesa y Rol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();

        setMenu();
        setListeners();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setListeners() {
        bLoadGame.addActionListener(e -> {
            Game game = (Game) cbSavedGamesSelector.getSelectedItem();
            assert game != null;
            tfGameName.setText(game.getGameName());
            tfGameDescription.setText(game.getDescription());
            switch (game.getGameType()) {
                case TABLE:
                    rbTypeGameTable.setSelected(true);
                    break;
                case ROLE:
                    rbTypeGameRol.setSelected(true);
                    break;
            }
        });
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
            cbSavedGamesSelector.addItem(game);
        }
    }
}
