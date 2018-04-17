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
        randomChooseFirstPlayer();
        moveCounter = 0;
    }

    private void randomChooseFirstPlayer() {
        playerX = (Math.random() < 0.5);
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
        return winningCombination(0, 1, 2) ||
                winningCombination(3, 4, 5) ||
                winningCombination(6, 7, 8) ||
                winningCombination(0, 3, 6) ||
                winningCombination(1, 4, 7) ||
                winningCombination(2, 5, 8) ||
                winningCombination(0, 4, 8) ||
                winningCombination(2, 4, 6);
    }

    private boolean winningCombination(int field1, int field2, int field3) {
        return fields[field1] == fields[field2] &&
                fields[field1] == fields[field3] &&
                !(fields[field1] == 0);
    }

    boolean isDraw() {
        return winningCombinationClosed(0, 1, 2) &&
                winningCombinationClosed(3, 4, 5) &&
                winningCombinationClosed(6, 7, 8) &&
                winningCombinationClosed(0, 3, 6) &&
                winningCombinationClosed(1, 4, 7) &&
                winningCombinationClosed(2, 5, 8) &&
                winningCombinationClosed(0, 4, 8) &&
                winningCombinationClosed(2, 4, 6);
    }

    private boolean winningCombinationClosed(int field1, int field2, int field3) {
        return (!(fields[field1] == 0) || !(fields[field2] == 0) || !(fields[field3] == 0)) &&
                ((fields[field1] == 1) || (fields[field2] == 1) || (fields[field3] == 1)) &&
                ((fields[field1] == -1) || (fields[field2] == -1) || (fields[field3] == -1));
    }

    int[] takeWinningCombination() {
        int[][] winningCombinations = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };
        for (int[] winningCombination : winningCombinations) {
            if (winningCombination(winningCombination[0], winningCombination[1], winningCombination[2]))
                return winningCombination;
        }
        return null;
    }

    void resetData() {
        randomChooseFirstPlayer();
        moveCounter = 0;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = 0;
        }
    }
}
