package com.bielinski.ticTacToe;

abstract class BoardController {

    final Model model;
    final BoardView view;

    BoardController() {
        this.model = new Model(this);
        this.view = new BoardView(this);
    }

    void move(int buttonNumber) {
        model.takeField(buttonNumber);
    }

    Player getCurrentPlayer() {
        return model.currentPlayer;
    }

    abstract void afterMoveAction();

    void afterMove() {
        boolean gameEnds = false;

        if (GameStatusChecker.isWinner(model.getFields())) {
            gameEnds = true;
            view.showWinningCombination(GameStatusChecker.takeWinningCombination(model.getFields()));
            view.showWinner(model.currentPlayer);
        } else if (GameStatusChecker.isDraw(model.getFields())) {
            gameEnds = true;
            view.showDraw();
        }

        if (gameEnds)
            view.proposeNewGame();
        else
            model.afterMoveDataChange();
    }

    void restartGame() {
        model.resetData();
    }
}
