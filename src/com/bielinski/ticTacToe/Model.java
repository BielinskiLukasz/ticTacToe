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

    boolean isWinner() {
        return winningsCombination(0, 1, 2) ||
                winningsCombination(3, 4, 5) ||
                winningsCombination(6, 7, 8) ||
                winningsCombination(0, 3, 6) ||
                winningsCombination(1, 4, 7) ||
                winningsCombination(2, 5, 8) ||
                winningsCombination(0, 4, 8) ||
                winningsCombination(2, 4, 6);
    }

    private boolean winningsCombination(int field1, int field2, int field3) {
        return fields[field1] == fields[field2] &&
                fields[field1] == fields[field3] &&
                !(fields[field1] == 0);
    }

    boolean isDraw() {
        return winningsCombinationClosed(0, 1, 2) &&
                winningsCombinationClosed(3, 4, 5) &&
                winningsCombinationClosed(6, 7, 8) &&
                winningsCombinationClosed(0, 3, 6) &&
                winningsCombinationClosed(1, 4, 7) &&
                winningsCombinationClosed(2, 5, 8) &&
                winningsCombinationClosed(0, 4, 8) &&
                winningsCombinationClosed(2, 4, 6);
    }

    private boolean winningsCombinationClosed(int field1, int field2, int field3) {
        return (!(fields[field1] == 0) || !(fields[field2] == 0) || !(fields[field3] == 0)) &&
                ((fields[field1] == 1) || (fields[field2] == 1) || (fields[field3] == 1)) &&
                ((fields[field1] == -1) || (fields[field2] == -1) || (fields[field3] == -1));
    }
}
