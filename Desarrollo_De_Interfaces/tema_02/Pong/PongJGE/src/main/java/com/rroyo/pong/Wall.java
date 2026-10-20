package com.rroyo.pong;

import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.positionable.PositionableGameObject;

public class Wall extends PositionableGameObject {

    private final WallType wallType;

    public Wall(Scene scene, double x, double y, double width, double height, WallType wallType) {
        super(scene, x, y, width, height);
        this.wallType = wallType;
    }

    public WallType getWallType() {
        return wallType;
    }
}
