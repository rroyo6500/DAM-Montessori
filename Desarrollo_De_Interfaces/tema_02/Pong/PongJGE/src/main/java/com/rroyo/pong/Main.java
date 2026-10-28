package com.rroyo.pong;

import rroyo.jge.core.gameessentials.GameWindow;
import rroyo.jge.core.gameessentials.Scene;
import rroyo.jge.core.gameobjects.GameObject;
import rroyo.jge.core.gameobjects.Group;
import rroyo.jge.core.gameobjects.Text;
import rroyo.jge.core.gameobjects.positionable.PositionableGameObject;
import rroyo.jge.core.gameobjects.positionable.Trigger;
import rroyo.jge.core.gameutils.Camera;
import rroyo.jge.core.gameutils.Dimension;
import rroyo.jge.core.gameutils.Keyboard;
import rroyo.jge.interfaces.Script;

public class Main {

    private static Scene pong = new Scene() {

        private Ball ball = new Ball(this, 0, 0, BallDirectionsX.LEFT, BallDirectionsY.UP);

        private Entity player1 = new Entity(this, -400, 0,
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

        private Entity player2 = new Entity(this, 400, 0,
                new Script() {
                    @Override
                    public void start(GameObject self) {

                    }

                    @Override
                    public void update(GameObject self) {
                        PositionableGameObject pgo = (PositionableGameObject) self;
                        if (Keyboard.keyDown()) pgo.setVelocityY(175);
                        if (Keyboard.keyUp()) pgo.setVelocityY(-175);
                    }
                });

        private Group walls = new Group(
                new Wall(this, 0, -310, 1000, 20),
                new Wall(this, 0, 310, 1000, 20)
        );

        private Group deathTriggers = new Group(
                new Trigger(this, -525, 0, 50, 600) {
                    @Override
                    public void onTrigger(PositionableGameObject triggeredObject) {
                        if (triggeredObject instanceof Ball) {
                            ball.getPosition().setPoint(0, 0);
                        }
                    }
                },
                new Trigger(this, 525, 0, 50, 600) {
                    @Override
                    public void onTrigger(PositionableGameObject triggeredObject) {
                        if (triggeredObject instanceof Ball) {
                            ball.getPosition().setPoint(0, 0);
                        }
                    }
                }
        );

        @Override
        public void update() {

            ball.collide(walls);
            ball.collide(player2);
            ball.collide(player1);
            ball.overlap(deathTriggers);

            player2.collide(walls);
            player1.collide(walls);

        }
    };

    static void main() {
        GameWindow window = new GameWindow(30, new Dimension(1000, 600), pong);
    }

}
