package com.bielinski.ticTacToe;

class WinnerFinder {

    static boolean isWinner(int[] checkBoard) {
        return isItAWinningCombination(checkBoard[0], checkBoard[1], checkBoard[2]) ||
                isItAWinningCombination(checkBoard[3], checkBoard[4], checkBoard[5]) ||
                isItAWinningCombination(checkBoard[6], checkBoard[7], checkBoard[8]) ||
                isItAWinningCombination(checkBoard[0], checkBoard[3], checkBoard[6]) ||
                isItAWinningCombination(checkBoard[1], checkBoard[4], checkBoard[7]) ||
                isItAWinningCombination(checkBoard[2], checkBoard[5], checkBoard[8]) ||
                isItAWinningCombination(checkBoard[0], checkBoard[4], checkBoard[8]) ||
                isItAWinningCombination(checkBoard[2], checkBoard[4], checkBoard[6]);
    }

    private static boolean isItAWinningCombination(int field1, int field2, int field3) {
        return field1 == field2 &&
                field1 == field3 &&
                !(field1 == 0);
    }

    static boolean isDraw(int[] checkBoard) {
        return isWinningCombinationUnavailable(checkBoard[0], checkBoard[1], checkBoard[2]) &&
                isWinningCombinationUnavailable(checkBoard[3], checkBoard[4], checkBoard[5]) &&
                isWinningCombinationUnavailable(checkBoard[6], checkBoard[7], checkBoard[8]) &&
                isWinningCombinationUnavailable(checkBoard[0], checkBoard[3], checkBoard[6]) &&
                isWinningCombinationUnavailable(checkBoard[1], checkBoard[4], checkBoard[7]) &&
                isWinningCombinationUnavailable(checkBoard[2], checkBoard[5], checkBoard[8]) &&
                isWinningCombinationUnavailable(checkBoard[0], checkBoard[4], checkBoard[8]) &&
                isWinningCombinationUnavailable(checkBoard[2], checkBoard[4], checkBoard[6]);
    }

    private static boolean isWinningCombinationUnavailable(int field1, int field2, int field3) {
        return (!(field1 == 0) || !(field2 == 0) || !(field3 == 0)) &&
                ((field1 == 1) || (field2 == 1) || (field3 == 1)) &&
                ((field1 == -1) || (field2 == -1) || (field3 == -1));
    }
}
