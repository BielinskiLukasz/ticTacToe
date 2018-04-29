package com.bielinski;

import com.bielinski.ticTacToe.Controller;

import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Controller());
    }
}
