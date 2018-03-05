package com.bielinski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeFrame extends JFrame implements ActionListener {

    private int counter = 0;
    private List<JButton> buttons = new ArrayList<>(); // tworzy listę buttonów
    private boolean[] availableButtons = new boolean[9];
    //private String[] board = new String[9]; TODO delete if all will be ok
    private int[] buttonsOwner = new int[9];
    private int[] winningButtonCombination = {0, 0, 0, 0, 0, 0, 0, 0}; //for AI
    private final String PLAYER_X = "X";
    private final String PLAYER_O = "O";
    private boolean isPlayerXMoveNow;
    private boolean playerOIsAI;

    TicTacToeFrame(String title, int width) {
        super(title);
        setSize(width, width); // ustawienie wymiarów okna (metoda JFrame)
        setVisible(true); // ustawienie widoczności (metoda JFrame)
        for (int i = 0; i < 9; i++) {
            JButton jButton = new JButton("");
            jButton.addActionListener(this); // "nasłuchuje kliknięcie"
            add(jButton); // dodaje nowy button
            buttons.add(jButton); // dodaje button do listy buttonów
            availableButtons[i] = true;
        }
        setLayout(new GridLayout(3, 3)); // ustawienie layoutu przycisków (najpierw pojawia się górny rząd od lewej do prawej, a potem niższe rzędy
        isPlayerXMoveNow = randomChooseFirstPlayer();
        System.out.println(isPlayerXMoveNow); //TODO delete this after tests
        chooseGameMode();
    }

    private void chooseGameMode() {
        final String MODE_PvP = "PvP";
        final String MODE_PvAI = "PvAI";
        final String MODE_AIvAI = "AIvAI";
        Object[] possibleValues = {MODE_PvP, MODE_PvAI, MODE_AIvAI};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, possibleValues, possibleValues[0]);
        if (selectedValue == null) {
            System.exit(0);
        } else if (selectedValue.equals(MODE_PvAI)) {
            playerOIsAI = true;
            if (!isPlayerXMoveNow) moveAI(PLAYER_O, PLAYER_X);
        } else if (selectedValue.equals(MODE_AIvAI)) {
            for (int i = 0; i < 9; i++) {
                if (isPlayerXMoveNow) moveAI(PLAYER_X, PLAYER_O);
                else moveAI(PLAYER_O, PLAYER_X);
            }
        }
    }

    private boolean randomChooseFirstPlayer() {
        return (Math.random() < 0.5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource(); // pokazuje, który obiekt jest wciśnięty
        if (isPlayerXMoveNow) {
            button.setText(PLAYER_X); // pętla zmieniająca gracza naprzemiennie (X i O)
        } else {
            button.setText(PLAYER_O);
        }
        afterMove(buttons.indexOf(button));
        if (playerOIsAI) moveAI(PLAYER_O, PLAYER_X);
    }

    private void afterMove(int buttonNumber) {
        buttonsOwner[buttonNumber] = isPlayerXMoveNow ? 1 : -1;
        buttons.get(buttonNumber).setEnabled(false); // blokuje przycisk wybrany przez gracza bądź AI
        if (counter > 4) {
            if (isDraw()) endsGame(false);
            if (isWinner()) endsGame(true);
        }
        refreashWinningPossibility(buttonNumber, isPlayerXMoveNow);
        isPlayerXMoveNow = !isPlayerXMoveNow;
        counter++;
    }

    private boolean isDraw() {
        return winningsCombinationClosed(0, 1, 2) &&
                winningsCombinationClosed(3, 4, 5) &&
                winningsCombinationClosed(6, 7, 8) &&
                winningsCombinationClosed(0, 3, 6) &&
                winningsCombinationClosed(1, 4, 7) &&
                winningsCombinationClosed(2, 5, 8) &&
                winningsCombinationClosed(0, 4, 8) &&
                winningsCombinationClosed(2, 4, 6);
    }

    private boolean winningsCombinationClosed(int i, int j, int k) {
        return (!buttons.get(i).getText().equals("") || !buttons.get(j).getText().equals("") || !buttons.get(k).getText().equals("")) &&
                (buttons.get(i).getText().equals(PLAYER_X) || buttons.get(j).getText().equals(PLAYER_X) || buttons.get(k).getText().equals(PLAYER_X)) &&
                (buttons.get(i).getText().equals(PLAYER_O) || buttons.get(j).getText().equals(PLAYER_O) || buttons.get(k).getText().equals(PLAYER_O));
    }

    private boolean isWinner() {
        return winningsCombinationAchieve(0, 1, 2) ||
                winningsCombinationAchieve(3, 4, 5) ||
                winningsCombinationAchieve(6, 7, 8) ||
                winningsCombinationAchieve(0, 3, 6) ||
                winningsCombinationAchieve(1, 4, 7) ||
                winningsCombinationAchieve(2, 5, 8) ||
                winningsCombinationAchieve(0, 4, 8) ||
                winningsCombinationAchieve(2, 4, 6);
    }

    private boolean winningsCombinationAchieve(int i, int j, int k) {
        if (buttons.get(i).getText().equals(buttons.get(j).getText()) && buttons.get(i).getText().equals(buttons.get(k).getText()) && !buttons.get(i).getText().equals("")) {
            showWinnersButtons(i, j, k);
            return true;
        }
        return false;
    }

    private void showWinnersButtons(int i, int j, int k) {
        buttons.get(i).setBackground(Color.green);
        buttons.get(j).setBackground(Color.green);
        buttons.get(k).setBackground(Color.green);
    }

    private void endsGame(boolean isWinner) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        if (isWinner) {
            String winner;
            if (counter % 2 == 0) winner = PLAYER_X; // pętla wyświetlająca naprzemiennie X i O
            else winner = PLAYER_O;
            JOptionPane.showMessageDialog(null, "Game over! The winner is " + winner);
        } else {
            JOptionPane.showMessageDialog(null, "Game tied!");
        }
        proposeNewGame();
    }

    private void proposeNewGame() {
        int decision = JOptionPane.showConfirmDialog(null,
                "Play again?", "GAME ENDS", JOptionPane.YES_NO_OPTION);
        if (decision == 0) {
            for (JButton button : buttons) {
                button.setEnabled(true);
                button.setText("");
                button.setBackground(null);
            }
            isPlayerXMoveNow = randomChooseFirstPlayer();
            System.out.println(isPlayerXMoveNow); //TODO delete this after tests
            for (int i = 0; i < winningButtonCombination.length; i++) {
                winningButtonCombination[i] = 0;
            }
            for (int i = 0; i < buttonsOwner.length; i++) {
                buttonsOwner[i] = 0;
            }
            if (!isPlayerXMoveNow) moveAI(PLAYER_O, PLAYER_X);
        } else {
            System.exit(0);
        }
    }

    //TODO AI

    private void moveAI(String playerAI, String opponent) {
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        JButton button = buttons.get(algorithmAI(playerAI, opponent));
        button.setText(playerAI);
        afterMove(buttons.indexOf(button));
    }

    private int algorithmAI(String player, String opponent) { //TODO refactoring 03 optimisation
        //check crucial moves
        for (int i = 0; i < 9; i++) {
            if (buttonsOwner[i] == 0) {
                JButton button = buttons.get(i);
                button.setText(player);
                if (isWinner()) return i;
                button.setText(opponent);
                if (isWinner()) return i;
                button.setText("");
            }
        }
        //check actual best moves
        for (int i = 0; i < 9; i++) {
            if (buttonsOwner[i] == 0) {
                if (canWinAfterThisMove(i, isPlayerXMoveNow)) return i;
            }
        }
        //check initial and draws moves
        if (buttonsOwner[4] == 0) return 4;
        int rand = 4;
        if (buttonsOwner[0] != 0 || buttonsOwner[2] != 0 || buttonsOwner[6] != 0 || buttonsOwner[8] != 0) {
            while (rand == 4 && buttonsOwner[rand] != 0) {
                rand = ((int) (Math.random() * 5)) * 2; //random choose buttons 0, 2, 6 or 8
            }
            return rand;
        }
        while (rand == 4 && buttonsOwner[rand] != 0) {
            rand = (int) (Math.random() * 9); //random choose buttons 0, 2, 6 or 8
        }
        return rand;
    }

    private void refreashWinningPossibility(int newButton, boolean playerXtakenIt) {
        int value = playerXtakenIt ? 1 : -1;
        winningButtonCombination[newButton / 3] += value;
        winningButtonCombination[newButton % 3 + 3] += value;
        if (newButton % 4 == 0) winningButtonCombination[6] += value;
        if (newButton == 2 || newButton == 4 || newButton == 6) winningButtonCombination[7] += value;
    }

    private boolean canWinAfterThisMove(int buttonNumber, boolean forPlayerX) {
        int optionsWeight = winningButtonCombination[buttonNumber / 3] + winningButtonCombination[buttonNumber % 3 + 3];
        if (buttonNumber % 4 == 0) optionsWeight += winningButtonCombination[6];
        if (buttonNumber == 2 || buttonNumber == 4 || buttonNumber == 6)
            optionsWeight += winningButtonCombination[7];
        if (forPlayerX) return optionsWeight > 0;
        else return optionsWeight < 0;
    }
}

//TODO problems: -new games problems: player is O (should be X); AI moves 2 times; -AI checking crucial change button color at green (only winning situation should do that)