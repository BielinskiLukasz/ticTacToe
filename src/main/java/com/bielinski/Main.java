package com.bielinski;

import com.bielinski.ticTacToe.StartController;

import javax.swing.*;

class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } //with that i can't see winners combination

        SwingUtilities.invokeLater(StartController::new);
    }
}
