package com.bielinski.ticTacToe;

public enum Player {
    X,
    O;

    public Player nextPlayer() {
        return this == X ? O : X;
    }

    public static Player randomChooseFirstPlayer() {
        return (Math.random() < 0.5) ? Player.X : Player.O;
    }
}
