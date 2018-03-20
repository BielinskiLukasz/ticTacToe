package com.bielinski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeFrame extends JFrame implements ActionListener {

    private int counter = 0;
    private List<JButton> buttons = new ArrayList<>(); // button list initiation
    private boolean[] availableButtons = new boolean[9];
    private int[] buttonsOwner = new int[9];
    private int[] winningButtonCombination = {0, 0, 0, 0, 0, 0, 0, 0}; //for AI
    private final String PLAYER_X = "X";
    private final String PLAYER_O = "O";
    private boolean isPlayerXMoveNow;
    private boolean playerOIsAI = false;
    private boolean gameEnds = false;

    TicTacToeFrame(String title, int width) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, width); // window size set
        setVisible(true); // window visible set
        for (int i = 0; i < 9; i++) {
            JButton jButton = new JButton("");
            jButton.addActionListener(this); // "click" listener
            jButton.setFont(new Font("Arial", Font.PLAIN, this.getSize().height / 6)); // set font size
            add(jButton); // add new button
            buttons.add(jButton); // add button to button list
            availableButtons[i] = true;
        }
        setLayout(new GridLayout(3, 3)); // set buttons layout (the upper row from left to right first appears, followed by the lower rows)
        isPlayerXMoveNow = randomChooseFirstPlayer();
        chooseGameMode();
    }

    private void chooseGameMode() {
        final String MODE_PvP = "PvP";
        final String MODE_PvAI = "PvAI";
        final String MODE_AIvAI = "AIvAI";
        Object[] possibleValues = {MODE_PvP, MODE_PvAI, MODE_AIvAI};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, possibleValues, possibleValues[1]);
        if (selectedValue == null) {
            System.exit(0);
        } else if (selectedValue.equals(MODE_PvAI)) {
            playerOIsAI = true;
            if (!isPlayerXMoveNow) moveAI(PLAYER_O, PLAYER_X);
        } else if (selectedValue.equals(MODE_AIvAI)) {
            playerOIsAI = true;
            for (int i = 0; i < 9; i++) {
                if (isPlayerXMoveNow) moveAI(PLAYER_X, PLAYER_O);
                else moveAI(PLAYER_O, PLAYER_X);
            }
        } else {
            if (isPlayerXMoveNow) JOptionPane.showMessageDialog(null, "Player X start!");
            else JOptionPane.showMessageDialog(null, "Player O start!");
        }
    }

    private boolean randomChooseFirstPlayer() {
        return (Math.random() < 0.5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource(); // show with button was clicked
        if (isPlayerXMoveNow) {
            button.setText(PLAYER_X); // change players condition
            button.setForeground(Color.BLUE);
        } else {
            button.setText(PLAYER_O);
            UIManager.getDefaults().put("Button.disabledText", Color.RED);
        }
        afterMove(buttons.indexOf(button));
        if (playerOIsAI && !isPlayerXMoveNow && !gameEnds) moveAI(PLAYER_O, PLAYER_X);
        if (gameEnds) {
            gameEnds = false;
            proposeNewGame();
        }
    }

    private void afterMove(int buttonNumber) {
        counter++;
        buttonsOwner[buttonNumber] = isPlayerXMoveNow ? 1 : -1;
        buttons.get(buttonNumber).setEnabled(false); // enable button chooses by player or AI
        if (counter > 4) {
            if (isDraw()) {
                gameEnds = true;
                endsGame(false);
            }
            if (isWinner(false)) {
                gameEnds = true;
                endsGame(true);
            }
        }
        refreashWinningPossibility(buttonNumber, isPlayerXMoveNow);
        isPlayerXMoveNow = !isPlayerXMoveNow;
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

    private boolean isWinner(boolean itsForAI) {
        return winningsCombinationAchieve(0, 1, 2, itsForAI) ||
                winningsCombinationAchieve(3, 4, 5, itsForAI) ||
                winningsCombinationAchieve(6, 7, 8, itsForAI) ||
                winningsCombinationAchieve(0, 3, 6, itsForAI) ||
                winningsCombinationAchieve(1, 4, 7, itsForAI) ||
                winningsCombinationAchieve(2, 5, 8, itsForAI) ||
                winningsCombinationAchieve(0, 4, 8, itsForAI) ||
                winningsCombinationAchieve(2, 4, 6, itsForAI);
    }

    private boolean winningsCombinationAchieve(int i, int j, int k, boolean itsForAI) {
        if (buttons.get(i).getText().equals(buttons.get(j).getText()) && buttons.get(i).getText().equals(buttons.get(k).getText()) && !buttons.get(i).getText().equals("")) {
            if (!itsForAI) {
                showWinnersButtons(i, j, k);
            }
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
            if (isPlayerXMoveNow) winner = PLAYER_X;
            else winner = PLAYER_O;
            JOptionPane.showMessageDialog(null, "Game over! The winner is " + winner);
        } else {
            JOptionPane.showMessageDialog(null, "Game tied!");
        }
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
            if (!playerOIsAI) {
                if (isPlayerXMoveNow) JOptionPane.showMessageDialog(null, "Player X start!");
                else JOptionPane.showMessageDialog(null, "Player O start!");
            }
            for (int i = 0; i < winningButtonCombination.length; i++) {
                winningButtonCombination[i] = 0;
            }
            for (int i = 0; i < buttonsOwner.length; i++) {
                buttonsOwner[i] = 0;
            }
            counter = 0;
            if (!isPlayerXMoveNow && playerOIsAI) moveAI(PLAYER_O, PLAYER_X);
        } else {
            System.exit(0);
        }
    }

    private void moveAI(String playerAI, String opponent) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JButton button = buttons.get(algorithmAI(playerAI, opponent));
        button.setText(playerAI);
        afterMove(buttons.indexOf(button));
    }

    private int algorithmAI(String player, String opponent) {
        //check initial if AI starts game
        if (buttonsOwner[4] == 0) return 4;
        //check crucial moves
        if (counter > 2) {
            for (int i = 0; i < 9; i++) {
                if (buttonsOwner[i] == 0)
                    if (checkCrucialSituationForAI(player, i)) return i;
            }
            for (int i = 0; i < 9; i++) {
                if (buttonsOwner[i] == 0)
                    if (checkCrucialSituationForAI(opponent, i)) return i;
            }
        }
        //check actual best moves
        if ((buttonsOwner[0] == buttonsOwner[8] && buttons.get(0).getText().equals(opponent)) || buttonsOwner[2] == buttonsOwner[6] && buttons.get(2).getText().equals(opponent)) {
            return buttonRandomised(2,1);
        }
        for (int i = 0; i < 9; i++) {
            if (buttonsOwner[i] == 0) {
                if (canWinAfterThisMove(i, isPlayerXMoveNow)) return i;
            }
        }
        //check initial and draws moves
        if (buttonsOwner[0] == 0 || buttonsOwner[2] == 0 || buttonsOwner[6] == 0 || buttonsOwner[8] == 0) {
            return buttonRandomised(2,0);
        }
        return buttonRandomised(1,0);
    }

    private int buttonRandomised(int move, int start) {
        int rand;
        List<Integer> buttonsAvailableIndex = new ArrayList<>();
        for (int i = start; i < buttonsOwner.length; i += move) {
            if (buttonsOwner[i] == 0) buttonsAvailableIndex.add(i);
        }
        rand = (int) (Math.random() * buttonsAvailableIndex.size()); //random choose buttons
        rand = buttonsAvailableIndex.get(rand);
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
        int betterButtons = forPlayerX ? 1 : -1;
        int optionsWeight = winningButtonCombination[buttonNumber / 3] + winningButtonCombination[buttonNumber % 3 + 3];
        if (buttonNumber % 4 == 0) optionsWeight += winningButtonCombination[6] + betterButtons;
        if (buttonNumber == 2 || buttonNumber == 4 || buttonNumber == 6)
            optionsWeight += winningButtonCombination[7] + betterButtons;
        if (counter < 5) {
            if (forPlayerX) return optionsWeight > 0;
            else return optionsWeight < 0;
        } else {
            if (forPlayerX) return optionsWeight >= 0;
            else return optionsWeight <= 0;
        }
    }

    private boolean checkCrucialSituationForAI(String checkingPlayer, int i) {
        JButton button = buttons.get(i);
        button.setText(checkingPlayer);
        boolean checking = isWinner(true);
        buttons.get(i).setText("");
        return checking;
    }
}

