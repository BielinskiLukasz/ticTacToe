package com.bielinski.ticTacToe;

class Model {

    static final int BOARD_FIELDS_NUMNER = 9;
    private final BoardController controller;
    private final int[] fields;
    Player currentPlayer;

    Model(BoardController controller) {
        this.controller = controller;
        fields = new int[BOARD_FIELDS_NUMNER];
        currentPlayer = Player.randomChooseFirstPlayer();
    }

    int[] getFields() {
        return fields;
    }

    void takeField(int fieldNumber) {
        if (isFieldAvailable(fieldNumber)) fields[fieldNumber] = currentPlayer == Player.X ? 1 : -1;
    }

    void afterMoveDataChange() {
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.nextPlayer();
    }

    private boolean isFieldAvailable(int fieldNumber) {
        return fields[fieldNumber] == 0;
    }

    void resetData() {
        currentPlayer = Player.randomChooseFirstPlayer();
        for (int i = 0; i < fields.length; i++) {
            fields[i] = 0;
        }
    }
}
