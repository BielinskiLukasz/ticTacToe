package com.bielinski.ticTacToe;

class AIController {

    private final BoardControllerInPvAIMode controller;

//    private final int[] winningCombinationsForAI;
//    private int[] aiFields;
//    private int[] minimaxBoard;

    AIController(BoardControllerInPvAIMode controller) {
        this.controller = controller;
//        winningCombinationsForAI = new int[8];
//        aiFields = new int[9];
//        minimaxBoard = new int[9];
    }

    void moveAI() { //TODO How to test that?
        int[] boardsFields = new int[9]; //TODO Remove hardcode 9
        System.arraycopy(controller.model.getFields(), 0, boardsFields, 0, boardsFields.length); //TODO Refactor controller.model snake
        controller.moveAI(AIMinimax.chooseFieldForAI(boardsFields)); //TODO uncomment after tests
//        controller.moveAI(AIMinimax.testPhase(boardsFields)); //TODO Remove after debug
    }

//    private int chooseFieldForAI() {
//        int[] boardsFields = controller.model.getFields(); //TODO Refactor this!!!
//        for (int i = 0; i < minimaxBoard.length; i++) minimaxBoard[i] = boardsFields[i];
//        int counter = 0;
//        for (int field : minimaxBoard) {
//            if (field == 0) counter++;
//        }
//        for (int i = 0; i < aiFields.length; i++) {
//            if (controller.model.isFieldAvailable(i)) { //TODO Refactor this!!!
//                minimaxBoard[i] = -1;
//                if (controller.model.isWinner(minimaxBoard)) return i; //TODO Refactor this!!!
//                minimaxBoard[i] = 0;
//            }
//        }
//        for (int i = 0; i < aiFields.length; i++) {
//            if (controller.model.isFieldAvailable(i)) { //TODO Refactor this!!!
//                minimaxBoard[i] = 1;
//                if (controller.model.isWinner(minimaxBoard)) return i; //TODO Refactor this!!!
//                minimaxBoard[i] = 0;
//            }
//        }
//        for (int i = 0; i < aiFields.length; i++)
//            if (controller.model.isFieldAvailable(i)) aiFields[i] = checkField(minimaxBoard, i, counter, true); //TODO Refactor this!!!
//        for (int i = 0; i < aiFields.length; i++) {
//            for (int j = 0; j < minimaxBoard.length; j++) minimaxBoard[j] = controller.model.getFields()[j]; //TODO Refactor this!!!
//            if (!controller.model.isFieldAvailable(i)) aiFields[i] = Integer.MAX_VALUE; //TODO Refactor this!!!
//        }
//        int min = Integer.MAX_VALUE;
//        int iMin = 4;
//        for (int i = 0; i < aiFields.length; i++) {
//            if (aiFields[i] < min) {
//                min = aiFields[i];
//                iMin = i;
//            }
//        }
//        for (int i = 0; i < aiFields.length; i++) aiFields[i] = 0;
//        return iMin;
//    }

//    private int checkField(int[] minimaxBoard, int fieldChecked, int counter, boolean ai) {
//        int returned = 0;
//        if (minimaxBoard[fieldChecked] == 0) {
//            minimaxBoard[fieldChecked] = ai ? -1 : 1;
//            if (controller.model.isWinner(minimaxBoard)) return (ai ? -1 : 1) * counter; //TODO Refactor this!!!
//            else {
//                for (int i = 0; i < minimaxBoard.length; i++) {
//                    if (minimaxBoard[i] == 0 && counter-- > 1) {
//                        returned += checkField(minimaxBoard, i, counter, !ai);
//                        minimaxBoard[i] = 0;
//                    }
//                }
//            }
//            minimaxBoard[fieldChecked] = 0;
//        }
//        return returned;
//    }

//    void resetAIData() {
//        for (int i = 0; i < winningCombinationsForAI.length; i++) {
//            winningCombinationsForAI[i] = 0;
//            aiFields[i] = 0;
//            minimaxBoard[i] = 0;
//        }
//    }
}
