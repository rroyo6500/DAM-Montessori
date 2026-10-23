package com.rroyo.pr1_tiendajuegosmesayrol.datamodels;

import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;

public class Game {

    private final String gameName;
    private final String Description;
    private final GameTypes gameType;

    public Game(String gameName, String description, GameTypes gameType) {
        this.gameName = gameName;
        Description = description;
        this.gameType = gameType;
    }

    public String getGameName() {
        return gameName;
    }

    public String getDescription() {
        return Description;
    }

    public GameTypes getGameType() {
        return gameType;
    }

    @Override
    public String toString() {
        return gameName + " | " + gameType;
    }
}
