package com.bielinski.ticTacToe;

class AIController {

    private final BoardControllerInPvAIMode controller;

    AIController(BoardControllerInPvAIMode controller) {
        this.controller = controller;
    }

    void moveAI() { //TODO How to test that?
        int[] boardsFields = new int[9]; //TODO Remove hardcode 9
        System.arraycopy(controller.model.getFields(), 0, boardsFields, 0, boardsFields.length); //TODO Refactor controller.model snake
        controller.moveAI(AIMinimax.chooseFieldForAI(boardsFields)); //TODO uncomment after tests
    }
}