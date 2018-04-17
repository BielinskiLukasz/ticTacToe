package com.bielinski.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private Controller controller;

    View(Controller controller) {
        this.controller = controller;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int size = 500;
        setSize(size, size);
        setVisible(true);

        for (int i = 0; i < 9; i++) {
            JButton jButton = new JButton("");
            jButton.addActionListener(this);
            jButton.setFont(new Font("Arial", Font.PLAIN, this.getSize().height / 6)); // set font size
            add(jButton);
        }

        setLayout(new GridLayout(3, 3));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
