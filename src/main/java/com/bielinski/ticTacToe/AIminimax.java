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
        for (int i = 0; i < minimaxBoard.length; i++) minimaxBoard[i] = model.getFields()[i];
        int counter = 0;
        for (int field : minimaxBoard) {
            if (field == 0) counter++;
        }
        for (int i = 0; i < aiFields.length; i++) {
            if (model.isAvailable(i)) {
                minimaxBoard[i] = -1;
                if (model.isWinner(minimaxBoard)) return i;
                minimaxBoard[i] = 0;
            }
        }
        for (int i = 0; i < aiFields.length; i++) {
            if (model.isAvailable(i)) {
                minimaxBoard[i] = 1;
                if (model.isWinner(minimaxBoard)) return i;
                minimaxBoard[i] = 0;
            }
        }
        for (int i = 0; i < aiFields.length; i++)
            if (model.isAvailable(i)) aiFields[i] = checkField(minimaxBoard, i, counter, true);
        for (int i = 0; i < aiFields.length; i++) {
            for (int j = 0; j < minimaxBoard.length; j++) minimaxBoard[j] = model.getFields()[j];
            if (!model.isAvailable(i)) aiFields[i] = Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        int iMin = 4;
        for (int i = 0; i < aiFields.length; i++) {
            if (aiFields[i] < min) {
                min = aiFields[i];
                iMin = i;
            }
        }
        for (int i = 0; i < aiFields.length; i++) aiFields[i] = 0;
        return iMin;
    }

    private int checkField(int[] minimaxBoard, int fieldChecked, int counter, boolean ai) {
        int returned = 0;
        if (minimaxBoard[fieldChecked] == 0) {
            minimaxBoard[fieldChecked] = ai ? -1 : 1;
            if (model.isWinner(minimaxBoard)) return (ai ? -1 : 1) * counter;
            else {
                for (int i = 0; i < minimaxBoard.length; i++) {
                    if (minimaxBoard[i] == 0 && counter-- > 1) {
                        returned += checkField(minimaxBoard, i, counter, !ai);
                        minimaxBoard[i] = 0;
                    }
                }
            }
            minimaxBoard[fieldChecked] = 0;
        }
        return returned;
    }

    void resetAIData() {
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            winningCombinationsForAI[i] = 0;
            aiFields[i] = 0;
            minimaxBoard[i] = 0;
        }
    }
}
