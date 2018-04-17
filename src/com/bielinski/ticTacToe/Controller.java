package com.bielinski.ticTacToe;

public class Controller {

    Model model;
    private View view;

    private boolean gameEnds;

    public Controller() {
        this.model = new Model();
        this.view = new View(this);

        view.showStartingPlayer(model.isPlayerX());
    }

    Player move(int buttonNumber) {
        model.takeField(buttonNumber);
        if (model.isPlayerX()) return Player.PLAYER_X;
        else return Player.PLAYER_O;
    }

    void afterMove() {
        gameEnds = false;
        if (model.isWinner()) {
            gameEnds = true;
            view.showWinningCombination(model.takeWinningCombination());
            view.showWinner(model.isPlayerX());
        } else if (model.isDraw()) {
            gameEnds = true;
            view.showDraw();
        }
        model.afterMoveDataChange();
        if (gameEnds) {
            gameEnds = false;
            view.proposeNewGame();
        }
    }

    void restartGame() {
        model.resetData();
    }

    void startGameWithAI() {
        model.initializeAIData();
    }
}
