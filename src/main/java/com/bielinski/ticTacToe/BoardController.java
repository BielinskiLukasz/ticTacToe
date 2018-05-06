package com.bielinski.ticTacToe;

class BoardController {

    final Model model;
    private final BoardView view;

    BoardController(boolean againstAI) {
        this.model = new Model(this, againstAI);
        this.view = new BoardView(this);
        if (model.isAgainstAI()) startGameWithAI();
    }

    Player move(int buttonNumber) {
        model.takeField(buttonNumber);
        if (model.isPlayerX()) return Player.PLAYER_X;
        else return Player.PLAYER_O;
    }

    void afterMove() {
        boolean gameEnds = false;
        if (model.isWinner()) {
            gameEnds = true;
            view.showWinningCombination(model.takeWinningCombination());
            view.showWinner(model.isPlayerX());
        } else if (model.isDraw()) {
            gameEnds = true;
            view.showDraw();
        }
        if (gameEnds)
            view.proposeNewGame();
        else
            model.afterMoveDataChange();
        if (model.ai != null && !model.isPlayerX()) model.ai.moveAI();
    }

    void restartGame() {
        model.resetData();
    }

    private void startGameWithAI() {
        model.initializeAIData();
        if (!model.isPlayerX())
            model.ai.moveAI();
    }

    void moveAI(int fieldNumber) {
        move(fieldNumber);
        view.viewAIMove(fieldNumber);
        afterMove();
    }
}
