package com.bielinski;

import com.bielinski.ticTacToe.StartController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        upgradeView();
        SwingUtilities.invokeLater(StartController::new);
    }

    private static void upgradeView() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
