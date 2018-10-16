package com.bielinski.ticTacToe;

abstract class BoardController {

    final Model model;
    final BoardView view;

    BoardController() {
        this.model = new Model(this);
        this.view = new BoardView(this);
    }

    Player move(int buttonNumber) { //TODO Remove Player return (why its do it???)
        model.takeField(buttonNumber);
        if (model.currentPlayer == Player.X) return Player.X;
        else return Player.O;
    }

    abstract void afterMoveAction();

    void afterMove() {
        boolean gameEnds = false;

        if (GameStatusChecker.isWinner(model.getFields())) {
            gameEnds = true;
            view.showWinningCombination(GameStatusChecker.takeWinningCombination(model.getFields()));
            view.showWinner(model.currentPlayer == Player.X);
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
