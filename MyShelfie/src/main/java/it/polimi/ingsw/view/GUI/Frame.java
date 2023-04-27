package it.polimi.ingsw.view.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private GamePanel gamePanel;

    public Frame() {
        super("My Shelfie");
        gamePanel = new GamePanel();
        setLayout(new BorderLayout());
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
