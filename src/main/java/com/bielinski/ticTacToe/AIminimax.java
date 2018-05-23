package com.bielinski.ticTacToe;

class AIminimax {

    private final BoardController controller;
    private final Model model;

    private final int[] winningCombinationsForAI;
    private int[] aiFields;
    private int[] minimaxBoard;

    AIminimax(Model model, BoardController controller) {
        this.model = model;
        this.controller = controller;
        winningCombinationsForAI = new int[8];
        aiFields = new int[9];
        minimaxBoard = new int[9];
    }

    void moveAI() {
        controller.moveAI(chooseFieldForAI());
    }

    private int chooseFieldForAI() {
        minimaxBoard = model.getFields();
        int counter = -1;
        for (int field : minimaxBoard) {
            if (field == 0) counter++;
        }
        for (int i = 0; i < aiFields.length; i++) {
            minimaxBoard[i] = -1;
            aiFields[i] = checkField(minimaxBoard, counter, false);
            minimaxBoard = model.getFields();
        }
        int min = 0;
        for (int aiField : aiFields) {
            if (aiField < min) min = aiField;
        }
        for (int i = 0; i < aiFields.length; i++) {
            aiFields[i] = 0;
        }
        return min;
    }

    private int checkField(int[] minimaxBoard, int stepLeft, boolean ai) {
        int returned = 0;
        if (model.isWinner(minimaxBoard)) return (ai ? -1 : 1) * 10 * stepLeft;
        for (int i = 0; i < minimaxBoard.length; i++) {
            if (minimaxBoard[i] == 0) {
                minimaxBoard[i] = ai ? -1 : 1;
                returned += checkField(minimaxBoard, --stepLeft, !ai);
            }
        }
        return returned;
    }

    private int takeFieldFromCombination(int combinationNumber) {
        return 0;
    }

    void refreshWinningPossibility(int fieldNumber, boolean playerX) {

    }

    void resetAIData() {
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            winningCombinationsForAI[i] = 0;
            aiFields[i] = 0;
            minimaxBoard[i] = 0;
        }
    }
}
