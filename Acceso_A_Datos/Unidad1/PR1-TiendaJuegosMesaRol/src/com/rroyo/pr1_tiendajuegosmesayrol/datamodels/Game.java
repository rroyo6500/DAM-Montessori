package com.rroyo.pr1_tiendajuegosmesayrol.datamodels;

import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;

import java.time.LocalDate;
import java.util.Date;

public class Game {

    private final String gameName;
    private final String Description;
    private final GameTypes gameType;
    private final LocalDate gameDate;
    private final double price;

    public Game(String gameName, String description, GameTypes gameType, LocalDate gameDate, double price) {
        this.gameName = gameName;
        Description = description;
        this.gameType = gameType;
        this.gameDate = gameDate;
        this.price = price;
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

    public LocalDate getGameDate() {
        return gameDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return gameName + " | " + gameType;
    }
}
