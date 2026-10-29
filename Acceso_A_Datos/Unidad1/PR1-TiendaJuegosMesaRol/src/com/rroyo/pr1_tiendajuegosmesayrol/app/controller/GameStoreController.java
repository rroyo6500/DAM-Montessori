package com.rroyo.pr1_tiendajuegosmesayrol.app.controller;

import com.rroyo.pr1_tiendajuegosmesayrol.app.gui.Window;
import com.rroyo.pr1_tiendajuegosmesayrol.app.model.GameStoreModel;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import com.rroyo.pr1_tiendajuegosmesayrol.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

public class GameStoreController implements ActionListener {

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
    }

    public void setGameStoreData(GameStore gameStoreData) {
        window.cbSavedGamesSelector.removeAllItems();
        for (Game game : gameStoreData.getGameList()) {
            window.cbSavedGamesSelector.addItem(game);
        }
    }

    private void loadConfiguration() {
    }

    public boolean hasBlankEntries() {
        return window.tfGameName.getText().isBlank() || window.tfGameDescription.getText().isBlank() ||
                (!window.rbTypeGameRol.isSelected() && !window.rbTypeGameTable.isSelected());
    }
    
    public void clearEntries() {
        window.rbTypeGameTable.setSelected(false);
        window.rbTypeGameRol.setSelected(false);
        window.tfGameDescription.setText(null);
        window.tfGameName.setText(null);
        window.tfGameName.requestFocus();
    }

    private void addActionListener(ActionListener actionListener) {
        window.imp.addActionListener(actionListener);
        window.exp.addActionListener(actionListener);
        window.bSave.addActionListener(actionListener);
        window.bLoadGame.addActionListener(actionListener);
    }
    
    private GameTypes getGameType() {
        boolean tableType = window.rbTypeGameTable.isSelected();
        boolean roleType = window.rbTypeGameRol.isSelected();
        if (tableType) return GameTypes.TABLE;
        if (roleType) return GameTypes.ROLE;
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "button_save": {
                String gameName = window.tfGameName.getText();
                String gameDescription = window.tfGameDescription.getText();
                GameTypes gameType = getGameType();

                if (!hasBlankEntries()) {
                    gameStoreModel.addGame(gameName, gameDescription, gameType);
                    clearEntries();
                    setGameStoreData(gameStoreModel.getGameStore());
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
                    }
                }
            } break;

            case "menu_import": {
                JFileChooser chooser = Util.createFileChooser(lastExportedRoute,
                        "XML Files", "xml"
                );
                int opt = chooser.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        gameStoreModel.importXML(chooser.getSelectedFile());
                    } catch (ParserConfigurationException | SAXException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                setGameStoreData(gameStoreModel.getGameStore());
            } break;

            case "menu_export": {
                JFileChooser chooser = Util.createFileChooser(lastExportedRoute,
                        "XML Files", "xml"
                );
                int opt = chooser.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        gameStoreModel.exportXML(chooser.getSelectedFile());
                        lastExportedRoute = chooser.getSelectedFile();
                    } catch (ParserConfigurationException | TransformerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } break;
        }

    }

}
