package com.bielinski.ticTacToe;

class WinnerFinder {

    static boolean isWinner(int[] checkBoard) {
        return winningCombination(checkBoard, 0, 1, 2) ||
                winningCombination(checkBoard, 3, 4, 5) ||
                winningCombination(checkBoard, 6, 7, 8) ||
                winningCombination(checkBoard, 0, 3, 6) ||
                winningCombination(checkBoard, 1, 4, 7) ||
                winningCombination(checkBoard, 2, 5, 8) ||
                winningCombination(checkBoard, 0, 4, 8) ||
                winningCombination(checkBoard, 2, 4, 6);
    }

    private static boolean winningCombination(int[] checkBoard, int field1, int field2, int field3) {
        return checkBoard[field1] == checkBoard[field2] &&
                checkBoard[field1] == checkBoard[field3] &&
                !(checkBoard[field1] == 0);
    }

    static boolean isDraw(int[] checkBoard) {
        return winningCombinationClosed(checkBoard, 0, 1, 2) &&
                winningCombinationClosed(checkBoard, 3, 4, 5) &&
                winningCombinationClosed(checkBoard, 6, 7, 8) &&
                winningCombinationClosed(checkBoard, 0, 3, 6) &&
                winningCombinationClosed(checkBoard, 1, 4, 7) &&
                winningCombinationClosed(checkBoard, 2, 5, 8) &&
                winningCombinationClosed(checkBoard, 0, 4, 8) &&
                winningCombinationClosed(checkBoard, 2, 4, 6);
    }

    private static boolean winningCombinationClosed(int[] checkBoard, int field1, int field2, int field3) {
        return (!(checkBoard[field1] == 0) || !(checkBoard[field2] == 0) || !(checkBoard[field3] == 0)) &&
                ((checkBoard[field1] == 1) || (checkBoard[field2] == 1) || (checkBoard[field3] == 1)) &&
                ((checkBoard[field1] == -1) || (checkBoard[field2] == -1) || (checkBoard[field3] == -1));
    }
}
