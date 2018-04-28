package com.bielinski.ticTacToe;

class AI {

    private Controller controller;
    private Model model;

    private int[] winningCombinationsForAI;

    AI(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        winningCombinationsForAI = new int[8];
    }

    void moveAI() {
        controller.moveAI(chooseFieldForAI());
    }

    private int chooseFieldForAI() {
        if (model.isAvailable(4)) return 4;
        if (model.getMoveCounter() == 1) {
            int random = 4;
            while (random == 4) {
                random = (int) (Math.random() * 4) * 2;
            }
            return random;
        }
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            if (winningCombinationsForAI[i] == -2) return takeFieldFromCombination(i);
        }
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            if (winningCombinationsForAI[i] == 2) return takeFieldFromCombination(i);
        }
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            if (winningCombinationsForAI[i] == -1) return takeFieldFromCombination(i);
        }
        for (int i = 0; i < winningCombinationsForAI.length; i++) {
            if (winningCombinationsForAI[i] == 1) return takeFieldFromCombination(i);
        }
        return (int) (Math.random() * 9);
    }

    private int takeFieldFromCombination(int combinationNumber) {
        if (combinationNumber == 0 || combinationNumber == 1 || combinationNumber == 2) {
            if (model.isAvailable(combinationNumber * 3 + 1)) return combinationNumber * 3 + 1;
            else if (model.isAvailable(combinationNumber * 3)) return combinationNumber * 3;
            else return combinationNumber * 3 + 2;
        } else if (combinationNumber == 3 || combinationNumber == 4 || combinationNumber == 5) {
            if (model.isAvailable(combinationNumber)) return combinationNumber;
            else if (model.isAvailable(combinationNumber - 3)) return combinationNumber - 3;
            else return combinationNumber + 3;
        } else if (combinationNumber == 6) {
            if (model.isAvailable(4)) return 4;
            else if (model.isAvailable(0)) return 0;
            else return 8;
        } else {
            if (model.isAvailable(4)) return 4;
            else if (model.isAvailable(2)) return 2;
            else return 6;
        }

    }

    void refreshWinningPossibility(int fieldNumber, boolean playerX) {
        int value = playerX ? 1 : -1;
        winningCombinationsForAI[fieldNumber / 3] += value;
        winningCombinationsForAI[fieldNumber % 3 + 3] += value;
        if (fieldNumber % 4 == 0) winningCombinationsForAI[6] += value;
        if (fieldNumber == 2 || fieldNumber == 4 || fieldNumber == 6) winningCombinationsForAI[7] += value;
    }
}
