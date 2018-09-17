package com.bielinski.ticTacToe;

class BoardControllerInPvPMode extends BoardController {

    private BoardControllerInPvPMode() {
        super();
    }

    static void startNewGame() {
        new BoardControllerInPvPMode();
    }

    void afterMoveAction() {
        afterMove();
    }
}
