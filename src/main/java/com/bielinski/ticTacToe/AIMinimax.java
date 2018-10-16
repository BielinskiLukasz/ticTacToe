package com.bielinski.ticTacToe;

import java.util.Arrays;

class AIMinimax {

    private static int[] fieldsScores;
    private final static int MINIMAX_ALGORITHM_DEPTH = Model.BOARD_FIELDS_NUMNER;
    private final static int OCCUPIED_FIELD_SCORE = 99;

    static int chooseFieldForAI(int[] boardsFields) {
        fieldsScores = new int[Model.BOARD_FIELDS_NUMNER];
        for (int i = 0; i < boardsFields.length; i++) {
            if (boardsFields[i] == 0) {
                int[] localBoardFields = new int[Model.BOARD_FIELDS_NUMNER];
                System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                localBoardFields[i] = -1;
                fieldsScores[i] = calculateMinimaxAlgorithmForActualBoard(localBoardFields,
                        Player.X,
                        0);
            } else {
                fieldsScores[i] = -OCCUPIED_FIELD_SCORE;
            }
        }

        return findBestFieldIndex(fieldsScores);
    }

    private static int calculateMinimaxAlgorithmForActualBoard(int[] boardsFields,
                                                               Player actualPlayer,
                                                               int actualAlgorithmDepth) {
        if (GameStatusChecker.isWinner(boardsFields)) {
            int score = (MINIMAX_ALGORITHM_DEPTH - actualAlgorithmDepth + 1);
            return actualPlayer == Player.X ? score : -score;
        } else if (actualAlgorithmDepth < MINIMAX_ALGORITHM_DEPTH && !GameStatusChecker.isDraw(boardsFields)) {
            int[] scores = new int[Model.BOARD_FIELDS_NUMNER];
            for (int i = 0; i < boardsFields.length; i++) {
                if (boardsFields[i] == 0) {
                    int[] localBoardFields = new int[Model.BOARD_FIELDS_NUMNER];
                    System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                    localBoardFields[i] = actualPlayer == Player.X ? 1 : -1;
                    scores[i] += calculateMinimaxAlgorithmForActualBoard(localBoardFields,
                            actualPlayer.nextPlayer(),
                            actualAlgorithmDepth + 1);
                } else {
                    scores[i] = actualPlayer == Player.X ? OCCUPIED_FIELD_SCORE : -OCCUPIED_FIELD_SCORE;
                }
            }

            return actualPlayer == Player.X ?
                    Arrays.stream(scores).min().getAsInt() : Arrays.stream(scores).max().getAsInt();
        }
        return 0;
    }

    static int findBestFieldIndex(int[] fieldPointValue) {
        int maxValue = Arrays.stream(fieldPointValue).max().isPresent() ?
                Arrays.stream(fieldPointValue).max().getAsInt() :
                0;
        int bestFieldIndex = fieldPointValue.length / 2;

        if (fieldPointValue[bestFieldIndex] == Arrays.stream(fieldPointValue).max().getAsInt()) {
            return bestFieldIndex;
        }

        for (int i = 0; i < fieldPointValue.length; i++) {
            if (fieldPointValue[i] == maxValue) {
                bestFieldIndex = i;
            }
        }

        return bestFieldIndex;
    }
}
