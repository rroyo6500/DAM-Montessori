package com.rroyo.pong;

import rroyo.jge.core.assets.Sprite;
import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.GameObject;
import rroyo.jge.core.gameobjects.positionable.SpriteGameObject;
import rroyo.jge.interfaces.Script;

import java.awt.*;

public class Ball extends SpriteGameObject {

    private BallDirectionsX ballDirectionsX;
    private BallDirectionsY ballDirectionsY;

    public Ball(Scene scene, double x, double y, BallDirectionsX bdx, BallDirectionsY bdy) {
        super(scene, x, y, 25, 25, new Sprite(Color.WHITE), new BallScript());
        this.ballDirectionsX = bdx;
        this.ballDirectionsY = bdy;
    }

    public BallDirectionsX getBallDirectionsX() {
        return ballDirectionsX;
    }

    public void setBallDirectionsX(BallDirectionsX ballDirectionsX) {
        this.ballDirectionsX = ballDirectionsX;
    }

    public BallDirectionsY getBallDirectionsY() {
        return ballDirectionsY;
    }

    public void setBallDirectionsY(BallDirectionsY ballDirectionsY) {
        this.ballDirectionsY = ballDirectionsY;
    }
}

class BallScript implements Script {

    @Override
    public void start(GameObject self) {

    }

    @Override
    public void update(GameObject self) {
        Ball ball = (Ball) self;

        ball.setVelocity(
                switch (ball.getBallDirectionsX()) {
                    case LEFT -> -200;
                    case RIGHT -> 200;
                },
                switch (ball.getBallDirectionsY()) {
                    case UP -> -200;
                    case DOWN -> 200;
                }
        );
    }

    @Override
    public void onCollide(GameObject self, GameObject objective) {
        Ball ball = (Ball) self;
        Wall wall = null;
        if (objective instanceof Wall) wall = (Wall) objective;

        if (wall != null) {

            if (wall.getWallType() == WallType.WALL) {
                ball.setVelocityX(0);
                ball.setBallDirectionsX(ball.getBallDirectionsX().getOposite());
            }
            if (wall.getWallType() == WallType.FLOOR) {
                ball.setVelocityY(0);
                ball.setBallDirectionsY(ball.getBallDirectionsY().getOposite());
            }

        }
    }
}
