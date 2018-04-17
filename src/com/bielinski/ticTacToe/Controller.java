package com.bielinski.ticTacToe;

public class Controller {

    private Model model;
    private View view;

    public Controller() {
        this.model = new Model();
        this.view = new View(this);
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
        if (gameEnds) view.proposeNewGame();
    }

    void restartGame() {
        model.resetData();
    }
}
