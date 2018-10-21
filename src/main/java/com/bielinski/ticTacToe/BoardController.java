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
        boolean gameEnds = true;

        if (isWinner()) {
            endTheGameWithAWinner();
        } else if (isDraw()) {
            endTheGameWithADraw();
        } else {
            gameEnds = false;
        }

        if (gameEnds) {
            proposeNewGame();
        } else {
            continueGame();
        }
    }

    private boolean isWinner() {
        return GameStatusChecker.isWinner(model.getFields());
    }

    private void endTheGameWithAWinner() {
        view.showWinningCombination(GameStatusChecker.takeWinningCombination(model.getFields()));
        view.showWinner(model.currentPlayer);
    }

    private boolean isDraw() {
        return GameStatusChecker.isDraw(model.getFields());
    }

    private void endTheGameWithADraw() {
        view.showDraw();
    }

    private void proposeNewGame() {
        view.proposeNewGame();
    }

    private void continueGame() {
        model.afterMoveDataUpdate();
    }

    void restartGame() {
        model.resetData();
    }
}
