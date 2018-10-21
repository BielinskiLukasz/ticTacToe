package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class BoardView extends JFrame implements ActionListener {

    private final BoardController controller;
    private final List<JButton> buttons;


    BoardView(BoardController controller) {
        JPanel jPanel = new JPanel();
        this.controller = controller;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(jPanel);
        int size = 500;
        jPanel.setPreferredSize(new Dimension(size, size));
        setVisible(true);
        jPanel.setBackground(Color.black);
        pack();

        buttons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            JButton jButton = new JButton("");
            jButton.addActionListener(this);
            jButton.setFont(new Font("Arial", Font.PLAIN, this.getSize().height / 6));
            jPanel.add(jButton);
            buttons.add(jButton);
        }

        jPanel.setLayout(new GridLayout(Model.BOARD_WIDTH, Model.BOARD_WIDTH));

        showStartingPlayerMessage(controller.model.currentPlayer);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setEnabled(false);
        controller.move(buttons.indexOf(button));
        button.setText(controller.getCurrentPlayer().name());
        controller.afterMoveAction();
    }

    void showWinner(Player currentPlayer) {
        disableAllButtons();
        String winner = currentPlayer.name();
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
            buttons.get(indexOfButton).setForeground(Color.green);
        }
    }

    void proposeNewGame() {
        int decision = JOptionPane.showConfirmDialog(null,
                "Play again?", "GAME ENDS", JOptionPane.YES_NO_OPTION);
        if (decision == 0) {
            resetAllButtons();
            controller.restartGame();
        } else {
            System.exit(0);
        }
    }

    private void resetAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
            button.setText("");
            resetWinningCombinationColors(button);
        }
    }

    private void resetWinningCombinationColors(JButton button) {
        button.setForeground(UIManager.getColor("Button.foreground"));
    }

    private void showStartingPlayerMessage(Player currentPlayer) {
        JOptionPane.showMessageDialog(null, "Player " + currentPlayer.name() + " start!");
    }

    void viewAIMove(int fieldNumber) {
        JButton button = buttons.get(fieldNumber);
        button.setEnabled(false);
        button.setText(Player.O.name());
    }
}
