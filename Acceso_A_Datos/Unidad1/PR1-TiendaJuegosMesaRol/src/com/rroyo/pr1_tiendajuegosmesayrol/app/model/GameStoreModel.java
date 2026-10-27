package com.rroyo.pr1_tiendajuegosmesayrol.app.model;

import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.Game;
import com.rroyo.pr1_tiendajuegosmesayrol.datamodels.GameStore;
import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;
import com.rroyo.pr1_tiendajuegosmesayrol.util.ImporterExporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class GameStoreModel {

    private GameStore gameStore;

    public GameStoreModel() {
        gameStore = new GameStore();
    }

    public GameStore getGameStore() {
        return gameStore;
    }

    public void addGame(String gameName, String description, GameTypes gameType) {
        Game game = new Game(gameName, description, gameType);
        gameStore.addGame(game);
    }

    public void addGame(Game game) {
        gameStore.addGame(game);
    }

    public void importXML(File file) throws ParserConfigurationException, IOException, SAXException {
        gameStore = ImporterExporter.importData(file);

    }

    public void exportXML(File file) throws ParserConfigurationException, TransformerException {
        ImporterExporter.exportData(file, gameStore);
    }

}
