package com.rroyo.pr1_tiendajuegosmesayrol.app.controller;

import com.rroyo.pr1_tiendajuegosmesayrol.app.gui.Window;
import com.rroyo.pr1_tiendajuegosmesayrol.app.model.GameStoreModel;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameStoreController implements ActionListener, ListSelectionListener, WindowListener {

    private final GameStoreModel gameStoreModel;
    private final Window window;

    public GameStoreController(GameStoreModel gameStoreModel, Window window) {
        this.gameStoreModel = gameStoreModel;
        this.window = window;
        
        try {
            loadConfiguration();
        } catch (Exception ignored) {
        }
        
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
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
    
    public void update() {
        
    }

    private void addWindowListener(WindowListener windowListener) {
        
    }

    private void addListSelectionListener(ListSelectionListener listSelectionListener) {
        
    }

    private void addActionListener(ActionListener actionListener) {
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
