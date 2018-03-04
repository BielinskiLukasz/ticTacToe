package com.bielinski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeFrame extends JFrame implements ActionListener {

    private int counter;
    private List<JButton> buttons = new ArrayList<>(); // tworzy listę buttonów
    private boolean[] availableButtons = new boolean[9];
    private String[] board = new String[9];
    private final String PLAYER_X_BUTTON = "X";
    private final String PLAYER_O_BUTTON = "O";

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
        chooseGameMode();
        counter = randomChooseFirstPlayer();
    }

    private void chooseGameMode() {
        Object[] possibleValues = {"PvP", "PvAI", "AIvAI"};
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Game Mode", JOptionPane.INFORMATION_MESSAGE,
                null, possibleValues, possibleValues[0]);
        System.out.println(selectedValue);
    }

    private int randomChooseFirstPlayer() {
        return ((Math.random() < 0.5) ? 0 : 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource(); // pokazuje, który obiekt jest wciśnięty
        if (counter % 2 == 0) button.setText(PLAYER_X_BUTTON); // pętla wyświetlająca naprzemiennie X i O
        else button.setText(PLAYER_O_BUTTON);
        button.setEnabled(false); // blokuje przycisk wybrany przez gracza bądź AI
        if (counter > 4) {
            if (isDraw()) endsGame(false);
            if (isWinner()) endsGame(true);
        }
        counter++;
    }

    private boolean isDraw() {
        return isDraw(0, 1, 2) &&
                isDraw(3, 4, 5) &&
                isDraw(6, 7, 8) &&
                isDraw(0, 3, 6) &&
                isDraw(1, 4, 7) &&
                isDraw(2, 5, 8) &&
                isDraw(0, 4, 8) &&
                isDraw(2, 4, 6);
    }

    private boolean isDraw(int i, int j, int k) {
        return (!buttons.get(i).getText().equals("") || !buttons.get(j).getText().equals("") || !buttons.get(k).getText().equals("")) &&
                (buttons.get(i).getText().equals(PLAYER_X_BUTTON) || buttons.get(j).getText().equals(PLAYER_X_BUTTON) || buttons.get(k).getText().equals(PLAYER_X_BUTTON)) &&
                (buttons.get(i).getText().equals(PLAYER_O_BUTTON) || buttons.get(j).getText().equals(PLAYER_O_BUTTON) || buttons.get(k).getText().equals(PLAYER_O_BUTTON));
    }

    private boolean isWinner() {
        return isWinner(0, 1, 2) ||
                isWinner(3, 4, 5) ||
                isWinner(6, 7, 8) ||
                isWinner(0, 3, 6) ||
                isWinner(1, 4, 7) ||
                isWinner(2, 5, 8) ||
                isWinner(0, 4, 8) ||
                isWinner(2, 4, 6);
    }

    private boolean isWinner(int i, int j, int k) {
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
            if (counter % 2 == 0) winner = PLAYER_X_BUTTON; // pętla wyświetlająca naprzemiennie X i O
            else winner = PLAYER_O_BUTTON;
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
        } else {
            setVisible(false); //can't see JFrame
            dispose(); //destroy obcject
        }
    }
}
