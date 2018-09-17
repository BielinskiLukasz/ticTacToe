package com.bielinski.ticTacToe;

class BoardControllerInPvAIMode extends BoardController {

    private AIController ai;

    private BoardControllerInPvAIMode() {
        super();
        ai = new AIController(this);
        if (model.currentPlayer == Player.O)
            ai.moveAI();
    }

    static void startNewGame() {
        new BoardControllerInPvAIMode();
    }

    void moveAI(int fieldNumber) {
        move(fieldNumber);
        view.viewAIMove(fieldNumber);
        afterMove();
    }

    void afterMoveAction() {
        afterMove();
        if (model.currentPlayer == Player.O) ai.moveAI();
    }
}
