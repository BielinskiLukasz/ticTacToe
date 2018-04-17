package com.bielinski.ticTacToe;

class Model {

    private AI ai;

    private int[] fields;
    private boolean playerX;
    private int moveCounter;

    boolean isPlayerX() {
        return playerX;
    }

    Model() {
        this.ai = new AI();

        fields = new int[9];
        playerX = randomChooseFirstPlayer();
        moveCounter = 0;
    }

    private boolean randomChooseFirstPlayer() {
        return (Math.random() < 0.5);
    }

    void takeField(int fieldNumber) {
        if (isAvailable(fieldNumber)) {
            moveCounter++;
            switchPlayer(playerX);
            fields[fieldNumber] = playerX ? 1 : -1;
        }
    }

    private void switchPlayer(boolean currentPlayerIsPlayerX) {
        playerX = !currentPlayerIsPlayerX;
    }

    private boolean isAvailable(int fieldNumber) {
        return fields[fieldNumber] == 0;
    }


}
