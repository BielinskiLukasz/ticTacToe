package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View extends JFrame implements ActionListener {

    private Controller controller;

    private List<JButton> buttons;
    private Player actualPlayer;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        actualPlayer = controller.move(buttons.indexOf(button));
        button.setText(actualPlayer == Player.PLAYER_X ? "X" : "O");
        controller.afterMove();
        button.setEnabled(false);


//        if (playerOIsAI && !isPlayerXMoveNow && !gameEnds) moveAI(PLAYER_O, PLAYER_X);
//        if (gameEnds) {
//            gameEnds = false;
//            proposeNewGame();
//        }
    }

    void showWinner(boolean isPlayerX) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        String winner;
        if (isPlayerX) winner = "X";
        else winner = "O";
        JOptionPane.showMessageDialog(null, "Game over! The winner is " + winner);
    }
}
