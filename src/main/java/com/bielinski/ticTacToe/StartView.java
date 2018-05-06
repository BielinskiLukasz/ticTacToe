package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;

class StartView extends JFrame {

    StartView() {
        setTitle("Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); //to organisation axis of added elements

        JLabel jLabel = new JLabel("Hello in Tic Tac Toe application");
        JButton jButtonOpen = new JButton("Start new game");
        JButton jButtonClose = new JButton("Close application");
        gridBagConstraints.insets = new Insets(10, 5, 10, 5); //set up insets between elements
        add(jLabel, gridBagConstraints);
        gridBagConstraints.gridy = 1; //change y axis for next added element
        add(jButtonOpen, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(jButtonClose, gridBagConstraints);

        jButtonOpen.addActionListener(
                e -> chooseGameModeMessage());
        jButtonClose.addActionListener(e -> System.exit(0));

        setVisible(true);
        getContentPane().setPreferredSize(new Dimension(200, 200));
        pack();
        setMinimumSize(new Dimension(180, 110));
    }

    private void chooseGameModeMessage() {
        final String MODE_PvP = "PvP";
        final String MODE_PvAI = "PvAI";
        Object[] possibleValues = {MODE_PvP, MODE_PvAI};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, possibleValues, possibleValues[1]);
        if (selectedValue != null && selectedValue.equals(MODE_PvP)) {
            setVisible(false);
            startNewGame(false);
        } else if (selectedValue != null && selectedValue.equals(MODE_PvAI)) {
            setVisible(false);
            startNewGame(true);
        } else System.exit(0);
    }

    private void startNewGame(boolean againstAI) {
        new BoardController(againstAI);
    }
}