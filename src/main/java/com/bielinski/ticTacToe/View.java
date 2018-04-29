package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class View extends JFrame implements ActionListener {

    private final Controller controller;

    private final List<JButton> buttons;
    private boolean againstAI;

    View(Controller controller) {
        this.controller = controller;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int size = 500;
        setSize(size, size);
        setVisible(true);

        buttons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            JButton jButton = new JButton("");
            jButton.addActionListener(this);
            jButton.setFont(new Font("Arial", Font.PLAIN, this.getSize().height / 6)); // set font size
            add(jButton);
            buttons.add(jButton);
        }

        setLayout(new GridLayout(3, 3));

        chooseGameModeMessage();
    }

    private void chooseGameModeMessage() {
        final String MODE_PvP = "PvP";
        final String MODE_PvAI = "PvAI";
        Object[] possibleValues = {MODE_PvP, MODE_PvAI};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, possibleValues, possibleValues[1]);
        if (selectedValue == null) {
            System.exit(0);
        } else if (selectedValue.equals(MODE_PvP)) {
            showStartingPlayer(controller.model.isPlayerX());
        } else if (selectedValue.equals(MODE_PvAI)) {
            againstAI = true;
            showStartingPlayer(controller.model.isPlayerX());
        } else System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (againstAI && controller.model.getMoveCounter() == 0 && !controller.model.isPlayerX())
            controller.startGameWithAI();
        else if (againstAI && controller.model.getMoveCounter() == 0 && controller.model.isPlayerX()) {
            controller.startGameWithAI();
            buttonAction(button);
        } else if ((controller.model.getMoveCounter() == 0) && againstAI && controller.model.isPlayerX() || !againstAI || controller.model.getMoveCounter() > 0) {
            buttonAction(button);
        }
    }

    private void buttonAction(JButton button) {
        button.setEnabled(false);
        Player actualPlayer = controller.move(buttons.indexOf(button));
        button.setText(actualPlayer == Player.PLAYER_X ? "X" : "O");
        controller.afterMove();
    }

    void showWinner(boolean isPlayerX) {
        disableAllButtons();
        String winner;
        if (isPlayerX) winner = "X";
        else winner = "O";
        JOptionPane.showMessageDialog(null, "Game over! The winner is " + winner);
    }

    private void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    void showDraw() {
        disableAllButtons();
        JOptionPane.showMessageDialog(null, "Game tied!");
    }

    void showWinningCombination(int[] winningButtons) {
        for (int indexOfButton : winningButtons) {
            buttons.get(indexOfButton).setBackground(Color.green);
        }
    }

    void proposeNewGame() {
        int decision = JOptionPane.showConfirmDialog(null,
                "Play again?", "GAME ENDS", JOptionPane.YES_NO_OPTION);
        if (decision == 0) {
            for (JButton button : buttons) {
                button.setEnabled(true);
                button.setText("");
                button.setBackground(null);
            }
            controller.restartGame();
        } else {
            System.exit(0);
        }
    }

    private void showStartingPlayer(boolean isPlayerXMoveNow) {
        if (isPlayerXMoveNow) JOptionPane.showMessageDialog(null, "Player X start!");
        else JOptionPane.showMessageDialog(null, "Player O start!");
    }

    void viewAIMove(int fieldNumber) {
        JButton button = buttons.get(fieldNumber);
        button.setEnabled(false);
        button.setText("O");
    }
}
