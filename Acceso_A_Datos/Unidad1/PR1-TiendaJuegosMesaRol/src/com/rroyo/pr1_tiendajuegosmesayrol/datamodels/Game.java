package com.rroyo.pr1_tiendajuegosmesayrol.datamodels;

import com.rroyo.pr1_tiendajuegosmesayrol.enums.GameTypes;

import java.time.LocalDate;
import java.util.Date;

public class Game {

    private String gameName;
    private String Description;
    private GameTypes gameType;
    private LocalDate gameDate;
    private double price;

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

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setGameType(GameTypes gameType) {
        this.gameType = gameType;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return gameName + " | " + gameType;
    }
}
