package com.bielinski.ticTacToe;

import java.util.Arrays;

class AIMinimax {

    private final static int MINIMAX_ALGORITHM_DEPTH = 9;

    private static int[] fieldsScores;
    private static int[] algorithmCall; //TODO only for test

    static int chooseFieldForAI(int[] boardsFields) {
        algorithmCall = new int[9]; //TODO only for test;
        fieldsScores = new int[9];
        for (int i = 0; i < boardsFields.length; i++) {
//            System.out.println("----------" + i + "----------"); //TODO tests
            if (boardsFields[i] == 0) {
                int[] localBoardFields = new int[9];
                System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                localBoardFields[i] = -1;
                fieldsScores[i] = calculateMinimaxAlgorithmForActualBoard(localBoardFields,
                        Player.X,
                        0);
            } else {
                fieldsScores[i] = -99;
            }
//            for (int j = 0; j < 1; j++) //TODO tests
//                System.out.print("\t"); //TODO tests
//            System.out.println("\nFinal select: " + fieldsScores[i]); //TODO tests
        }

//        System.out.println("Scores: ");
//        for (int fieldPointValue : fieldsScores) { //TODO only for test
//            System.out.println(fieldPointValue);
//        }

        return findBestFieldIndex(fieldsScores);

    }

    private static int calculateMinimaxAlgorithmForActualBoard(int[] boardsFields,
                                                               Player actualPlayer,
                                                               int actualAlgorithmDepth) {
        if (GameStatusChecker.isWinner(boardsFields)) {
            int score = (MINIMAX_ALGORITHM_DEPTH - actualAlgorithmDepth + 1);
//            System.out.println("For below: " + (actualPlayer == Player.X ? score : -score)); // TODO tests...
//            for (int boardsField : boardsFields) { // TODO tests...
//                System.out.print(boardsField + " ");
//            }
//            System.out.println(); // TODO tests...
            return actualPlayer == Player.X ? score : -score;
        } else if (actualAlgorithmDepth < MINIMAX_ALGORITHM_DEPTH && !GameStatusChecker.isDraw(boardsFields)) {
            int[] scores = new int[9];
            for (int i = 0; i < boardsFields.length; i++) {
                if (boardsFields[i] == 0) {
//                    System.out.println(actualAlgorithmDepth);
//                    for (int boardsField : boardsFields) { // TODO tests...
//                        System.out.print(boardsField + " ");
//                    }
//                    System.out.println(); // TODO tests...
                    int[] localBoardFields = new int[9];
                    System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                    localBoardFields[i] = actualPlayer == Player.X ? 1 : -1;
                    scores[i] += calculateMinimaxAlgorithmForActualBoard(localBoardFields,
                            actualPlayer.nextPlayer(),
                            actualAlgorithmDepth + 1);
                } else {
                    scores[i] = actualPlayer == Player.X ? 99 : -99;
                }
            }
//            System.out.print("\nTaken " + (actualAlgorithmDepth + 4)); //TODO tests
//            if ((actualPlayer == Player.X ? // TODO Tests
//                    Arrays.stream(scores).min().getAsInt() : Arrays.stream(scores).max().getAsInt()) != 99 && // TODO Tests
//                    (actualPlayer == Player.X ? // TODO Tests
//                            Arrays.stream(scores).min().getAsInt() : Arrays.stream(scores).max().getAsInt()) != -99) { // TODO Tests
//                for (int j = 0; j < actualAlgorithmDepth + 1; j++) //TODO tests
//                    System.out.print("\t"); //TODO tests
//                for (int j = 0; j < 9; j++) { // TODO Tests
//                    System.out.print("\t" + scores[j]); // TODO Tests
//                } // TODO Tests
//                System.out.print("\t" + actualPlayer.name() + " selected: " + (actualPlayer == Player.X ?
//                        Arrays.stream(scores).min().getAsInt() : Arrays.stream(scores).max().getAsInt()));// TODO Tests
//            } // TODO Tests
            return actualPlayer == Player.X ?
                    Arrays.stream(scores).min().getAsInt() : Arrays.stream(scores).max().getAsInt();
        }
        return 0;
    }

    static int chooseFieldForAIOld(int[] boardsFields) {
//        System.out.println("START"); // TODO tests...
        algorithmCall = new int[9]; //TODO only for test
        fieldsScores = new int[boardsFields.length];
        for (int i = 0; i < fieldsScores.length; i++) {
            if (boardsFields[i] == 0) {
                int[] localBoardFields = new int[9];
                System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                localBoardFields[i] = -1;
                calculateMinimaxAlgorithmForActualBoardOld(
                        localBoardFields,
                        i,
                        (int) Math.pow(10, MINIMAX_ALGORITHM_DEPTH),
                        Player.X,
                        0);
            } else {
                fieldsScores[i] = Integer.MIN_VALUE;
            }
        }
//        for (int fieldPointValue : fieldsScores) { //TODO only for test
//            System.out.println(fieldPointValue);
//        }
//        for (int i : algorithmCall) { //TODO only for test
//            System.out.println(i);
//        }

        return findBestFieldIndex(fieldsScores);
    }

    private static void calculateMinimaxAlgorithmForActualBoardOld(
            int[] boardsFields,
            int checkedFieldIndex,
            int pointsForWining,
            Player actualPlayer,
            int actualAlgorithmDepth) {
        algorithmCall[checkedFieldIndex]++; //TODO only for test

        if (GameStatusChecker.isWinner(boardsFields)) {
            fieldsScores[checkedFieldIndex] += actualPlayer == Player.X ? pointsForWining : -pointsForWining;
//            System.out.println("For " + checkedFieldIndex + ": " + (actualPlayer == Player.X ? pointsForWining : -pointsForWining)); // TODO tests...
//            for (int boardsField : boardsFields) { // TODO tests...
//                System.out.print(boardsField + " ");
//            }
//            System.out.println(); // TODO tests...
        } else if (actualAlgorithmDepth < MINIMAX_ALGORITHM_DEPTH/* && !GameStatusChecker.isDraw(boardsFields)*/) {
            for (int i = 0; i < fieldsScores.length; i++) {
                if (boardsFields[i] == 0) {
                    int[] localBoardFields = new int[9];
                    System.arraycopy(boardsFields, 0, localBoardFields, 0, fieldsScores.length);
                    localBoardFields[i] = actualPlayer == Player.X ? 1 : -1;
//                    for (int boardsField : boardsFields) { // TODO tests...
//                        System.out.print(boardsField + " ");
//                    }
//                    System.out.println(); // TODO tests...
                    calculateMinimaxAlgorithmForActualBoardOld(
                            localBoardFields,
                            checkedFieldIndex,
                            pointsForWining / 10,
                            actualPlayer.nextPlayer(),
                            ++actualAlgorithmDepth);
                    System.out.println("------------------");
//                    localBoardFields[i] = 0;
                }
            }
        }
    }

    static int findBestFieldIndex(int[] fieldPointValue) {
        int maxValue = Arrays.stream(fieldPointValue).max().getAsInt();
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

//    public static int testPhase(int[] boardsFields) { //TODO Remove after debug
//        for (int i = 0; i < boardsFields.length; i++) {
//            if (boardsFields[i] == 0){
//                return i;
//            }
//        }
//        return 0;
//    }
}
