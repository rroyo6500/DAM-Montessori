package com.rroyo.pr1_tiendajuegosmesayrol.app.controller;

import com.rroyo.pr1_tiendajuegosmesayrol.app.gui.Window;
import com.rroyo.pr1_tiendajuegosmesayrol.app.model.GameStoreModel;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.LogLevel;
import com.rroyo.pr1_tiendajuegosmesayrol.util.Util;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
import java.util.Properties;

public class GameStoreController implements ActionListener, WindowListener {

    private final GameStoreModel gameStoreModel;
    private final Window window;

    private File lastExportedRoute;

    public GameStoreController(GameStoreModel gameStoreModel, Window window) {
        this.gameStoreModel = gameStoreModel;
        this.window = window;
        
        try {
            loadConfiguration();
        } catch (Exception ignored) {
        }
        
        addActionListener(this);
        addWindowListener(this);
    }

    public void setGameStoreData(GameStore gameStoreData) {
        window.cbSavedGamesSelector.removeAllItems();
        for (Game game : gameStoreData.getGameList()) {
            window.cbSavedGamesSelector.addItem(game);
        }
    }

    private void loadConfiguration() throws IOException {
        Properties config = new Properties();
        config.load(new FileReader("game_store.conf"));
        lastExportedRoute=new File(config.getProperty("last_exported_route"));
    }

    private void saveConfiguration() throws IOException, NullPointerException {
        Properties config = new Properties();
        config.setProperty("last_exported_route",lastExportedRoute.getAbsolutePath());
        config.store(new PrintWriter("game_store.conf"),"GameStore configuration");
    }

    public boolean hasBlankEntries() {
        return window.tfGameName.getText().isBlank() || window.tfGameDescription.getText().isBlank() ||
                (!window.rbTypeGameRol.isSelected() &&
                        !window.rbTypeGameTable.isSelected() &&
                        !window.rbTypeGameVideogame.isSelected()) ||
                window.dpDateSelector.getDate() == null || window.tfPrice.getText().isBlank();
    }
    
    public void clearEntries() {
        window.typeSelectionGroup.clearSelection();
        window.tfGameDescription.setText(null);
        window.tfGameName.setText(null);
        window.tfGameName.requestFocus();
        window.dpDateSelector.clear();
        window.tfPrice.setText(null);
    }

    private void addActionListener(ActionListener actionListener) {
        window.imp.addActionListener(actionListener);
        window.exp.addActionListener(actionListener);
        window.bSave.addActionListener(actionListener);
        window.bLoadGame.addActionListener(actionListener);
        window.bEdit.addActionListener(actionListener);
        window.bRemove.addActionListener(actionListener);
    }

    public void addWindowListener(WindowListener windowListener) {
        window.frame.addWindowListener(windowListener);
    }
    
    private GameTypes getGameType() {
        boolean tableType = window.rbTypeGameTable.isSelected();
        boolean roleType = window.rbTypeGameRol.isSelected();
        boolean videogame = window.rbTypeGameVideogame.isSelected();
        if (tableType) return GameTypes.TABLE;
        if (roleType) return GameTypes.ROLE;
        if (videogame) return GameTypes.VIDEOGAME;
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "button_save": {
                try {
                    String gameName = window.tfGameName.getText();
                    String gameDescription = window.tfGameDescription.getText();
                    GameTypes gameType = getGameType();
                    LocalDate gameDate = window.dpDateSelector.getDate();
                    double gamePrice = Double.parseDouble(window.tfPrice.getText());

                    if (!hasBlankEntries()) {
                        gameStoreModel.addGame(gameName, gameDescription, gameType, gameDate, gamePrice);
                        clearEntries();
                        setGameStoreData(gameStoreModel.getGameStore());
                    } else {
                        Util.log("No se pueden dejar campos vacíos", LogLevel.ERROR);
                    }
                } catch (Exception ignored) {
                    Util.log("El precio del juego debe ser un número válido", LogLevel.ERROR);
                }
            } break;

            case "button_load": {
                Game game = (Game) window.cbSavedGamesSelector.getSelectedItem();
                if (game != null) {
                    window.tfGameName.setText(game.getGameName());
                    window.tfGameDescription.setText(game.getDescription());
                    switch (game.getGameType()) {
                        case TABLE:
                            window.rbTypeGameTable.setSelected(true);
                            break;
                        case ROLE:
                            window.rbTypeGameRol.setSelected(true);
                            break;
                        case VIDEOGAME:
                            window.rbTypeGameVideogame.setSelected(true);
                            break;
                    }
                    window.dpDateSelector.setDate(game.getGameDate());
                    window.tfPrice.setText(String.valueOf(game.getPrice()));
                }
            } break;

            case "button_edit": {
                Game game = (Game) window.cbSavedGamesSelector.getSelectedItem();
                if (game != null) {
                    game.setGameName(window.tfGameName.getText());
                    game.setDescription(window.tfGameDescription.getText());
                    game.setGameType(getGameType());
                    game.setGameDate(window.dpDateSelector.getDate());
                    game.setPrice(Double.parseDouble(window.tfPrice.getText()));
                    window.cbSavedGamesSelector.setSelectedItem(game);
                    clearEntries();
                }
            } break;

            case "button_remove": {
                Game game = (Game) window.cbSavedGamesSelector.getSelectedItem();
                if (game != null) {
                    clearEntries();
                    window.cbSavedGamesSelector.removeItem(game);
                    gameStoreModel.getGameStore().removeGame(game);
                }
            } break;

            case "menu_import": {
                JFileChooser chooser = Util.createFileChooser(lastExportedRoute,
                        "XML or JSON Files", "xml", "json"
                );
                int opt = chooser.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        gameStoreModel.importData(chooser.getSelectedFile());
                    } catch (Exception ignored) {
                        Util.log("Error al importar el archivo", LogLevel.ERROR);
                    }
                }
                setGameStoreData(gameStoreModel.getGameStore());
            } break;

            case "menu_export": {
                JFileChooser chooser = Util.createFileChooser(lastExportedRoute,
                        "XML or JSON Files", "xml", "json"
                );
                int opt = chooser.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        gameStoreModel.exportData(chooser.getSelectedFile());
                        lastExportedRoute = chooser.getSelectedFile();
                        window.cbSavedGamesSelector.removeAllItems();
                    } catch (Exception ex) {
                        Util.log("Error al exportar el archivo", LogLevel.ERROR);
                    }
                }
            } break;
        }

    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int res = Util.confirmMessage("Deseas cerrar la ventana","Salir");
        if (res ==JOptionPane.OK_OPTION) {
            try {
                saveConfiguration();
            } catch (Exception ignored) {}
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
