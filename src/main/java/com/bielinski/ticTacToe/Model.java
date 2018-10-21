package com.bielinski.ticTacToe;

class Model {

    static final int BOARD_WIDTH = 3;
    private final BoardController controller;
    private final int[] fields;
    static final int NUMBER_OF_BOARD_FIELDS = (int) Math.pow(BOARD_WIDTH, 2);
    Player currentPlayer;

    Model(BoardController controller) {
        this.controller = controller;
        fields = new int[NUMBER_OF_BOARD_FIELDS];
        selectFirstPlayer();
    }

    int[] getFields() {
        return fields;
    }

    void takeField(int fieldNumber) {
        if (isFieldAvailable(fieldNumber)) {
            takeFieldByPlayer(fieldNumber);
        }
    }

    private void takeFieldByPlayer(int fieldNumber) {
        fields[fieldNumber] = currentPlayer.fieldCode();
    }

    void afterMoveDataUpdate() {
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.nextPlayer();
    }

    private boolean isFieldAvailable(int fieldNumber) {
        return fields[fieldNumber] == Player.NONE_PLAYER_CODE;
    }

    void resetData() {
        clearFields();
        selectFirstPlayer();
    }

    private void selectFirstPlayer() {
        currentPlayer = Player.randomChooseFirstPlayer();
    }

    private void clearFields() {
        for (int i = 0; i < fields.length; i++) {
            fields[i] = Player.NONE_PLAYER_CODE;
        }
    }
}
