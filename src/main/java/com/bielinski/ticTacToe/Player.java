package com.bielinski.ticTacToe;

public enum Player {
    X,
    O;

    public Player nextPlayer() {
        return this == X ? O : X;
    }
}
