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

        if (model.isWinner(model.getFields())) { //TODO Uncomment that piece of code after debug
            gameEnds = true;
            view.showWinningCombination(model.takeWinningCombination());
            view.showWinner(model.currentPlayer == Player.X);
        } else if (model.isDraw()) {
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
