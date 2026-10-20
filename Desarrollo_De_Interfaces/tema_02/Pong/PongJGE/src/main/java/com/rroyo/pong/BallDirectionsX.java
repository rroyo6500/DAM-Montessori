package com.rroyo.pong;

public enum BallDirectionsX {
    LEFT,
    RIGHT;

    public BallDirectionsX getOposite() {
        return switch (this) {
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
