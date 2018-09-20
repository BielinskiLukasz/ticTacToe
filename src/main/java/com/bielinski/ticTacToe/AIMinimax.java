package com.bielinski.ticTacToe;

class AIMinimax {

    private final static int MINIMAX_ALGORITHM_DEPTH = 9;

    private static int[] fieldPointValues;

    static int chooseFieldForAI(int[] boardsFields) {
        fieldPointValues = new int[boardsFields.length];
        for (int i = 0; i < fieldPointValues.length; i++) {
            if (boardsFields[i] == 0) {
                int[] localBoardFields = new int[9];
                System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldPointValues.length);
                localBoardFields[i] = -1;
                calculateMinimaxAlgorithmForActualBoard(
                        localBoardFields,
                        i,
                        (int) Math.pow(10, 9),
                        Player.X,
                        0);
            } else {
                fieldPointValues[i] = Integer.MIN_VALUE;
            }
        }
        for (int fieldPointValue : fieldPointValues) { //TODO only for test
            System.out.println(fieldPointValue);
        }
        return findBestFieldIndex(fieldPointValues);
    }

    private static void calculateMinimaxAlgorithmForActualBoard(
            int[] boardsFields,
            int checkedFieldIndex,
            int pointsForWining,
            Player actualPlayer,
            int actualAlgorithmDepth) {
        if (GameStatusChecker.isWinner(boardsFields)) {
            fieldPointValues[checkedFieldIndex] += actualPlayer == Player.X ? pointsForWining : -pointsForWining;
        } else if (actualAlgorithmDepth < MINIMAX_ALGORITHM_DEPTH && !GameStatusChecker.isDraw(boardsFields)) {
            int[] localBoardFields = new int[9];
            System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldPointValues.length);
            for (int i = 0; i < fieldPointValues.length; i++) {
                if (localBoardFields[i] == 0) {
                    localBoardFields[i] = actualPlayer == Player.X ? 1 : -1;
                    calculateMinimaxAlgorithmForActualBoard(
                            localBoardFields,
                            checkedFieldIndex,
                            pointsForWining / 10,
                            actualPlayer.nextPlayer(),
                            ++actualAlgorithmDepth);
                    localBoardFields[i] = 0;
                }
            }
        }
    }

    static int findBestFieldIndex(int[] fieldPointValue) {
        int maxValue = Integer.MIN_VALUE;
        int bestFieldIndex = fieldPointValue.length / 2;

        for (int i = 0; i < fieldPointValue.length; i++) {
            if (fieldPointValue[i] > maxValue) {
                maxValue = fieldPointValue[i];
                bestFieldIndex = i;
            }
        }

        return bestFieldIndex;
    }

//    public static int testPhase(int[] boardsFields) { //TODO Remove after debug
//        for (int i = 0; i < boardsFields.length; i++) {
//            if (boardsFields[i] == 0){
//                return i;
//            }
//        }
//        return 0;
//    }
}
