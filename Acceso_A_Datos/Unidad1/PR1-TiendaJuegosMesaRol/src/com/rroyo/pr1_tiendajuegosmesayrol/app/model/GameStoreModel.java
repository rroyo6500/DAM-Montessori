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
import java.time.LocalDate;

public class GameStoreModel {

    private GameStore gameStore;

    public GameStoreModel() {
        gameStore = new GameStore();
    }

    public GameStore getGameStore() {
        return gameStore;
    }

    public void addGame(String gameName, String description, GameTypes gameType, LocalDate gameDate) {
        Game game = new Game(gameName, description, gameType, gameDate);
        gameStore.addGame(game);
    }

    public void addGame(Game game) {
        gameStore.addGame(game);
    }

    public void importData(File file) throws ParserConfigurationException, IOException, SAXException {
        gameStore = ImporterExporter.importData(file);
    }

    public void exportData(File file) throws ParserConfigurationException, TransformerException, IOException {
        ImporterExporter.exportData(file, gameStore);
    }

}
