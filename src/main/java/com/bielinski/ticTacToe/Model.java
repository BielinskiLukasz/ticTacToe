package com.bielinski.ticTacToe;

class Model {

    private final BoardController controller;
    AIMinimax ai;

    private final int[] fields;
    //    private final boolean gameAgainstAI;
    Player currentPlayer;

    Model(BoardController controller) {
        this.controller = controller;
//        this.gameAgainstAI = false;
        fields = new int[9];
        randomChooseFirstPlayer();
    }

//    Model(BoardController controller, boolean gameAgainstAI) {
//        this.controller = controller;
////        this.gameAgainstAI = gameAgainstAI;
//        fields = new int[9];
//        randomChooseFirstPlayer();
//    }

    private void randomChooseFirstPlayer() {
        currentPlayer = (Math.random() < 0.5) ? Player.X : Player.O; //TODO New code
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    int[] getFields() {
        return fields;
    }

//    boolean isGameAgainstAI() {
//        return gameAgainstAI;
//    }

    void takeField(int fieldNumber) {
        if (isFieldAvailable(fieldNumber)) fields[fieldNumber] = currentPlayer == Player.X ? 1 : -1;
    }

    void afterMoveDataChange() {
        switchPlayer();
    }

    private void switchPlayer() {
//        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
        currentPlayer = currentPlayer.nextPlayer();
    }

    boolean isFieldAvailable(int fieldNumber) {
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
//        if (ai != null) ai.resetAIData(); //TODO Correct this
    }
}
