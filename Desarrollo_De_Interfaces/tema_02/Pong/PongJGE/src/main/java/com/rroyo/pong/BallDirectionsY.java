package com.rroyo.pong;

public enum BallDirectionsY {
    UP,
    DOWN;

    public BallDirectionsY getOposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
        };
    }
}
