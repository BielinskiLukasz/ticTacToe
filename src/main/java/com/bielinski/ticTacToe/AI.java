package com.bielinski.ticTacToe;

class AI {

    private final BoardController controller;
    private final Model model;

    private final int[] winningCombinationsForAI;

    AI(Model model, BoardController controller) {
        this.model = model;
        this.controller = controller;
        winningCombinationsForAI = new int[8];
    }

    void moveAI() {
        controller.moveAI(chooseFieldForAI());
    }

    private int chooseFieldForAI() {
        if (model.isAvailable(4)) return 4;
        int[] fields = model.getFields();
        if ((model.getMoveCounter() < 4) && ((fields[0] == fields[8] && fields[0] == 1) || (fields[2] == fields[6] && fields[2] == 1))) {
            int random = ((int) (Math.random() * 4)) * 2 + 1;
            while (!model.isAvailable(random)) {
                random = ((int) (Math.random() * 4)) * 2 + 1;
            }
            return random;
        }
        for (int i = winningCombinationsForAI.length - 1; i >= 0; i--) {
            if (winningCombinationsForAI[i] == -2)
                if (takeFieldFromCombination(i) != -1) return takeFieldFromCombination(i);
        }
        for (int i = winningCombinationsForAI.length - 1; i >= 0; i--) {
            if (winningCombinationsForAI[i] == 2)
                if (takeFieldFromCombination(i) != -1) return takeFieldFromCombination(i);
        }
        for (int i = winningCombinationsForAI.length - 1; i >= 0; i--) {
            if (winningCombinationsForAI[i] == -1)
                if (takeFieldFromCombination(i) != -1) return takeFieldFromCombination(i);
        }
        for (int i = winningCombinationsForAI.length - 1; i >= 0; i--) {
            if (winningCombinationsForAI[i] == 1)
                if (takeFieldFromCombination(i) != -1) return takeFieldFromCombination(i);
        }
        int random = (int) (Math.random() * 9);
        while (!model.isAvailable(random)) {
            random = (int) (Math.random() * 9);
        }
        return random;
    }

    private int takeFieldFromCombination(int combinationNumber) {
        if (combinationNumber == 0 || combinationNumber == 1 || combinationNumber == 2) {
            if (model.isAvailable(combinationNumber * 3 + 1)) return combinationNumber * 3 + 1;
            else if (model.isAvailable(combinationNumber * 3)) return combinationNumber * 3;
            else if (model.isAvailable(combinationNumber * 3 + 2)) return combinationNumber * 3 + 2;
        } else if (combinationNumber == 3 || combinationNumber == 4 || combinationNumber == 5) {
            if (model.isAvailable(combinationNumber)) return combinationNumber;
            else if (model.isAvailable(combinationNumber - 3)) return combinationNumber - 3;
            else if (model.isAvailable(combinationNumber + 3)) return combinationNumber + 3;
        } else if (combinationNumber == 6) {
            if (model.isAvailable(4)) return 4;
            else if (model.isAvailable(0)) return 0;
            else if (model.isAvailable(8)) return 8;
        } else {
            if (model.isAvailable(4)) return 4;
            else if (model.isAvailable(2)) return 2;
            else if (model.isAvailable(6)) return 6;
        }
        return -1;
    }

    void refreshWinningPossibility(int fieldNumber, boolean playerX) {
        int value = playerX ? 1 : -1;
        winningCombinationsForAI[fieldNumber / 3] += value;
        winningCombinationsForAI[fieldNumber % 3 + 3] += value;
        if (fieldNumber % 4 == 0) winningCombinationsForAI[6] += value;
        if (fieldNumber == 2 || fieldNumber == 4 || fieldNumber == 6) winningCombinationsForAI[7] += value;
    }

    void resetAIData() {
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            winningCombinationsForAI[i] = 0;
        }
    }
}
