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

        jPanel.setLayout(new GridLayout(3, 3));

        showStartingPlayerMessage(controller.model.currentPlayer);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setEnabled(false);
        Player actualPlayer = controller.move(buttons.indexOf(button));
        button.setText(actualPlayer == Player.X ? "X" : "O");
        controller.afterMoveAction();
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
            buttons.get(indexOfButton).setForeground(Color.green);
        }
    }

    void proposeNewGame() {
        int decision = JOptionPane.showConfirmDialog(null,
                "Play again?", "GAME ENDS", JOptionPane.YES_NO_OPTION);
        if (decision == 0) {
            for (JButton button : buttons) {
                button.setEnabled(true);
                button.setText("");
                button.setForeground(UIManager.getColor("Button.foreground"));
            }
            controller.restartGame();
        } else {
            System.exit(0);
        }
    }

    private void showStartingPlayerMessage(Player currentPlayer) {
        if (currentPlayer == Player.X) {
            JOptionPane.showMessageDialog(null, "Player X start!");
        } else {
            JOptionPane.showMessageDialog(null, "Player O start!");
        }
    }

    void viewAIMove(int fieldNumber) {
        JButton button = buttons.get(fieldNumber);
        button.setEnabled(false);
        button.setText("O");
    }
}
