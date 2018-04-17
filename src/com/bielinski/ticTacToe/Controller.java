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
        if (model.isWinner()) view.showWinner(model.isPlayerX());
//        else if (model.isDraw()) view.showDraw();
//        if(gameEnds) view.proposeNewGame();
    }
}
