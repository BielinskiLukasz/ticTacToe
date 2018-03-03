package com.bielinski;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeFrame("Kółko i Krzyżyk", 500));
    }
}
