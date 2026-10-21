package com.rroyo.pong;

import rroyo.jge.core.gameessentials.GameWindow;
import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.GameObject;
import rroyo.jge.core.gameobjects.Group;
import rroyo.jge.core.gameobjects.positionable.PositionableGameObject;
import rroyo.jge.core.gameutils.Dimension;
import rroyo.jge.core.gameutils.Keyboard;
import rroyo.jge.interfaces.Script;

public class Main {

    private static Scene pong = new Scene() {

        private Ball ball = new Ball(this, 0, 0, BallDirectionsX.LEFT, BallDirectionsY.UP);

        private Entity enemy = new Entity(this, 400, 0,
                new Script() {
                    @Override
                    public void start(GameObject self) {

                    }

                    @Override
                    public void update(GameObject self) {
                        PositionableGameObject pgo = (PositionableGameObject) self;
                        pgo.getPosition().setY(ball.getPosition().getY());
                    }
                });
        private Entity player = new Entity(this, -400, 0,
                new Script() {
                    @Override
                    public void start(GameObject self) {

                    }

                    @Override
                    public void update(GameObject self) {
                        PositionableGameObject pgo = (PositionableGameObject) self;
                        if (Keyboard.key('s')) pgo.setVelocityY(175);
                        if (Keyboard.key('w')) pgo.setVelocityY(-175);
                    }
                });

        private Group walls = new Group(
                new Wall(this, 0, -310, 1000, 20, WallType.FLOOR),
                new Wall(this, 0, 310, 1000, 20, WallType.FLOOR),
                new Wall(this, -510, 0, 20, 600, WallType.WALL),
                new Wall(this, 510, 0, 20, 600, WallType.WALL)
        );

        @Override
        public void update() {

            ball.collide(walls);
            ball.collide(enemy);
            ball.collide(player);

            enemy.collide(walls);
            player.collide(walls);

        }
    };

    static void main() {

        GameWindow window = new GameWindow(30, new Dimension(1000, 600), pong);

    }

}
