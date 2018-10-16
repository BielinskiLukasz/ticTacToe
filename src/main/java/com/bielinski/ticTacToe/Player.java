package com.bielinski.ticTacToe;

public enum Player {
    X,
    O;

    static final int PLAYER_X_CODE = 1;
    static final int PLAYER_Y_CODE = -1;
    static final int NONE_PLAYER_CODE = 0;

    public Player nextPlayer() {
        return this == X ? O : X;
    }

    public static Player randomChooseFirstPlayer() {
        return (Math.random() < 0.5) ? Player.X : Player.O;
    }

    public int fieldCode() {
        return this == X ? PLAYER_X_CODE : PLAYER_Y_CODE;
    }
}
