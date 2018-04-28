package com.bielinski.ticTacToe;

public class Controller {

    Model model;
    private View view;

    private boolean gameEnds;

    public Controller() {
        this.model = new Model(this);
        this.view = new View(this);
    }

    Player move(int buttonNumber) {
        model.takeField(buttonNumber);
        if (model.isPlayerX()) return Player.PLAYER_X;
        else return Player.PLAYER_O;
    }

    void afterMove() {
        gameEnds = false;
        if (model.getMoveCounter() >= 4) {
            if (model.isWinner()) {
                gameEnds = true;
                view.showWinningCombination(model.takeWinningCombination());
                view.showWinner(model.isPlayerX());
            } else if (model.isDraw()) {
                gameEnds = true;
                view.showDraw();
            }
            if (gameEnds) {
                gameEnds = false;
                view.proposeNewGame();
            }
        }
        model.afterMoveDataChange();
        if (model.ai != null && !model.isPlayerX()) model.ai.moveAI();
    }

    void restartGame() {
        model.resetData();
    }

    void startGameWithAI() {
        model.initializeAIData();
        if (!model.isPlayerX()) model.ai.moveAI(); //zapewne muszę zastąpić option pane osobnym widokiem, żeby to działało tak jak chciałem, ALE MOŻE ZNAJDĘ JAKIEś DZIAłAJĄCE ROZWIĄZANIE
    }

    void moveAI(int fieldNumber) {
        move(fieldNumber);
        view.viewAIMove(fieldNumber);
        afterMove();
    }
}
