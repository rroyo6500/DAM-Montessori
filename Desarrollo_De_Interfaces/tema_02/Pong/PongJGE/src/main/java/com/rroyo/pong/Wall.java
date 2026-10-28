package com.rroyo.pong;

import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.positionable.PositionableGameObject;

public class Wall extends PositionableGameObject {

    public Wall(Scene scene, double x, double y, double width, double height) {
        super(scene, x, y, width, height);
    }

}
