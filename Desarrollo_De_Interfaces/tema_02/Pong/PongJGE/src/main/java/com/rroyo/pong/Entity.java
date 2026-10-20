package com.rroyo.pong;

import org.jetbrains.annotations.NotNull;
import rroyo.jge.core.assets.Sprite;
import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.GameObject;
import rroyo.jge.core.gameobjects.positionable.SpriteGameObject;
import rroyo.jge.interfaces.Script;

import java.awt.*;

public class Entity extends SpriteGameObject {
    public Entity(Scene scene, double x, double y, Script... script) {
        super(scene, x, y, 10, 100, new Sprite(Color.WHITE), new EntityScript());
        addScript(script);
    }
}

class EntityScript implements Script {

    @Override
    public void start(GameObject self) {

    }

    @Override
    public void update(GameObject self) {

    }

    @Override
    public void onCollide(GameObject self, GameObject objective) {
        if (objective instanceof Ball ball) {
            ball.setVelocityX(0);
            ball.setBallDirectionsX(ball.getBallDirectionsX().getOposite());
        }
    }
}
