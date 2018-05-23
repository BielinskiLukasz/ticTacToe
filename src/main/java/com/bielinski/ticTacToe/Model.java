package com.bielinski.ticTacToe;

class Model {

    private final BoardController controller;
    AIminimax ai;

    private final int[] fields;
    private boolean playerX;
    private final boolean againstAI;

    Model(BoardController controller, boolean againstAI) {
        this.controller = controller;
        this.againstAI = againstAI;
        fields = new int[9];
        randomChooseFirstPlayer();
    }

    boolean isPlayerX() {
        return playerX;
    }

    int[] getFields() {
        return fields;
    }

    boolean isAgainstAI() {
        return againstAI;
    }

    private void randomChooseFirstPlayer() {
        playerX = (Math.random() < 0.5);
    }

    void takeField(int fieldNumber) {
        if (isAvailable(fieldNumber)) fields[fieldNumber] = playerX ? 1 : -1;
    }

    void afterMoveDataChange() {
        switchPlayer(playerX);
    }

    private void switchPlayer(boolean currentPlayerIsPlayerX) {
        playerX = !currentPlayerIsPlayerX;
    }

    boolean isAvailable(int fieldNumber) {
        return fields[fieldNumber] == 0;
    }

    boolean isWinner(int[] checkBoard) {
        return winningCombination(checkBoard, 0, 1, 2) ||
                winningCombination(checkBoard, 3, 4, 5) ||
                winningCombination(checkBoard, 6, 7, 8) ||
                winningCombination(checkBoard, 0, 3, 6) ||
                winningCombination(checkBoard, 1, 4, 7) ||
                winningCombination(checkBoard, 2, 5, 8) ||
                winningCombination(checkBoard, 0, 4, 8) ||
                winningCombination(checkBoard, 2, 4, 6);
    }

    private boolean winningCombination(int[] checkBoard, int field1, int field2, int field3) {
        return checkBoard[field1] == checkBoard[field2] &&
                checkBoard[field1] == checkBoard[field3] &&
                !(checkBoard[field1] == 0);
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
            if (winningCombination(fields, winningCombination[0], winningCombination[1], winningCombination[2]))
                return winningCombination;
        }
        return null;
    }

    void resetData() {
        randomChooseFirstPlayer();
        for (int i = 0; i < fields.length; i++) {
            fields[i] = 0;
        }
        if (ai != null) ai.resetAIData();
    }

    void initializeAIData() {
        ai = new AIminimax(this, controller);
    }
}
