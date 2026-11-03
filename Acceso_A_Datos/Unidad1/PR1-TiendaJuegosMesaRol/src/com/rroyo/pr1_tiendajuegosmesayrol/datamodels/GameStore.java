package com.rroyo.pr1_tiendajuegosmesayrol.datamodels;

import java.util.ArrayList;
import java.util.List;

public class GameStore {

    private final List<Game> gameList = new ArrayList<>();

    public GameStore() {
    }

    public void addGame(Game game) {
        gameList.add(game);
    }

    public List<Game> getGameList() {
        return gameList;
    }
}
