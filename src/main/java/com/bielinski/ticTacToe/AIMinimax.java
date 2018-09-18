package com.bielinski.ticTacToe;

class AIMinimax {

    private final static int MINIMAX_ALGORITHM_DEPTH = 9;

    private static int[] fieldPointValue;

    static int chooseFieldForAI(int[] boardsFields) {
        fieldPointValue = new int[boardsFields.length];
        for (int i = 0; i < fieldPointValue.length; i++) {
            if (boardsFields[i] == 0) {
                int[] localBoardFields = new int[9];
                System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldPointValue.length);
                localBoardFields[i] = -1;
                calculateMinimaxAlgorithmForActualBoard(
                        localBoardFields,
                        i,
                        (int) Math.pow(9, 10),
                        Player.X,
                        0);
            } else {
                fieldPointValue[i] = Integer.MIN_VALUE;
            }
        }
        return findBestFieldIndex(fieldPointValue);
    }

    private static void calculateMinimaxAlgorithmForActualBoard(
            int[] boardsFields,
            int checkedFieldIndex,
            int pointsForWining,
            Player actualPlayer,
            int actualAlgorithmDepth) {

        if (WinnerFinder.isWinner(boardsFields)) {
            fieldPointValue[checkedFieldIndex] += actualPlayer == Player.X ? pointsForWining : -pointsForWining;
        } else if (actualAlgorithmDepth < MINIMAX_ALGORITHM_DEPTH && !WinnerFinder.isDraw(boardsFields)) {
            int[] localBoardFields = new int[9];
            System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldPointValue.length);
            for (int i = 0; i < fieldPointValue.length; i++) {
                if (localBoardFields[i] == 0) {
                    localBoardFields[i] = actualPlayer == Player.X ? 1 : -1;
                    calculateMinimaxAlgorithmForActualBoard(
                            localBoardFields,
                            checkedFieldIndex,
                            pointsForWining / 9,
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
