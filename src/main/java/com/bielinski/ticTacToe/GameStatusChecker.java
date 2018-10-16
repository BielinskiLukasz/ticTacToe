package com.bielinski.ticTacToe;

class GameStatusChecker {

    private static int[][] winningCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    static boolean isWinner(int[] checkBoard) {
        boolean isWinner = false;
        for (int[] winningCombination : winningCombinations) {
            isWinner = isWinner || isItAWinningCombination(checkBoard[winningCombination[0]],
                    checkBoard[winningCombination[1]],
                    checkBoard[winningCombination[2]]);
        }
        return isWinner;
    }

    private static boolean isItAWinningCombination(int field1, int field2, int field3) {
        return field1 == field2 &&
                field1 == field3 &&
                !(field1 == Player.NONE_PLAYER_CODE);
    }

    static boolean isDraw(int[] checkBoard) {
        boolean isDraw = true;
        for (int[] winningCombination : winningCombinations) {
            isDraw = isDraw && isWinningCombinationUnavailable(checkBoard[winningCombination[0]],
                    checkBoard[winningCombination[1]],
                    checkBoard[winningCombination[2]]);
        }
        return isDraw;
    }

    private static boolean isWinningCombinationUnavailable(int field1, int field2, int field3) {
        return (!(field1 == Player.NONE_PLAYER_CODE) || !(field2 == Player.NONE_PLAYER_CODE) ||
                !(field3 == Player.NONE_PLAYER_CODE)) &&
                ((field1 == Player.PLAYER_X_CODE) || (field2 == Player.PLAYER_X_CODE) ||
                        (field3 == Player.PLAYER_X_CODE)) &&
                ((field1 == Player.PLAYER_Y_CODE) || (field2 == Player.PLAYER_Y_CODE) ||
                        (field3 == Player.PLAYER_Y_CODE));
    }

    static int[] takeWinningCombination(int[] checkBoard) {
        int[] currentWiningCombination = null;
        for (int[] winningCombination : winningCombinations) {
            if (isItAWinningCombination(checkBoard[winningCombination[0]],
                    checkBoard[winningCombination[1]],
                    checkBoard[winningCombination[2]]))
                currentWiningCombination = winningCombination;
        }
        return currentWiningCombination;
    }
}
