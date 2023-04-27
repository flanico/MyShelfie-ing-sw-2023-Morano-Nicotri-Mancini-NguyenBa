/*package it.polimi.ingsw.view.GUI;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI interface class
 * @author Stefano Morano
 */
/*
public class InterfaceGUI extends JFrame {
    private JPanel mainPanel;
    private GamePanel gamePanel;
    private JTable Board;
    private JTextField nameField;
    private JButton createGameButton;
    private JButton joinGameButton;
    private JTable table1;
    private JPanel menuPanel;
    private JTextField textName;
    private JButton backButton;
    private JTextArea nameArea;
    private JLabel MyShelfie;
    private String name;
    public InterfaceGUI() {
    super("My Shelfie");
    gamePanel = new GamePanel();
    setLayout(new BorderLayout());
    setSize(500,500);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gamePanel, BorderLayout.NORTH);
    createGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            name = nameField.getText();
            nameArea.append(name + " is joining the game");
           getContentPane().remove(menuPanel);
           getContentPane().add(gamePanel);
           validate();
        }
    });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().remove(gamePanel);
                getContentPane().add(menuPanel);
                validate();
            }
        });

    setVisible(true);

    }


}
*/