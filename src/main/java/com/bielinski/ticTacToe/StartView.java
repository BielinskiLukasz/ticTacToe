package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;

class StartView extends JFrame {

    StartView() {
        setTitle("Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); //to organisation axis of added elements

        createLabelOnTheLayout(gridBagConstraints);
        createStartButtonOnTheLayout(gridBagConstraints);
        createExitButtonOnTheLabel(gridBagConstraints);

        setVisible(true);
        getContentPane().setPreferredSize(new Dimension(200, 200));
        pack();
        setMinimumSize(new Dimension(180, 110));
    }

    private void createLabelOnTheLayout(GridBagConstraints gridBagConstraints) {
        JLabel jLabel = new JLabel("Hello in Tic Tac Toe application");
        add(jLabel, gridBagConstraints);
    }

    private void createStartButtonOnTheLayout(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridy = 1; //change y axis for next added element
        JButton jButtonOpen = new JButton("Start new game");
        add(jButtonOpen, gridBagConstraints);
        jButtonOpen.addActionListener(
                e -> chooseGameModeMessage());
    }

    private void createExitButtonOnTheLabel(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridy = 2;
        JButton jButtonClose = new JButton("Close application");
        add(jButtonClose, gridBagConstraints);
        gridBagConstraints.insets = new Insets(10, 5, 10, 5); //set up insets between elements
        jButtonClose.addActionListener(
                e -> System.exit(0));
    }

    private void chooseGameModeMessage() {
        Object[] availableGameModes = {GameMode.PvP, GameMode.PvAI};

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, availableGameModes, availableGameModes[0]);
        hideGameModeMessage();
        performSelectedAction(selectedValue);
    }

    private void hideGameModeMessage() {
        setVisible(false);
    }

    private void performSelectedAction(Object selectedValue) {
        if (isNoModeSelected(selectedValue)) {
            System.exit(0);
        } else if (isPvPModeSelected(selectedValue)) {
            startNewGame(false);
        } else if (isPvAIModeSelected(selectedValue)) {
            startNewGame(true);
        }
    }

    private boolean isNoModeSelected(Object selectedValue) {
        return selectedValue == null;
    }

    private boolean isPvPModeSelected(Object selectedValue) {
        return selectedValue.equals(GameMode.PvP);
    }

    private boolean isPvAIModeSelected(Object selectedValue) {
        return selectedValue.equals(GameMode.PvAI);
    }

    private void startNewGame(boolean againstAI) {
        new BoardController(againstAI);
    }
}