package it.polimi.ingsw.view.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.*;

public class InterfaceGUI extends JFrame {
    private JPanel menuPanel;
    private JLabel Title;
    private JTextField textName;
    private JButton createGameButton;
    private JButton joinGameButton;


public InterfaceGUI() {

    createGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(createGameButton, textName.getText() + " creates a new game!" );
        }
    });
    joinGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(createGameButton, textName.getText() + " wants to join a game!" );
        }
    });

}

    public static void main(String[] args){
        InterfaceGUI window = new InterfaceGUI();
        window.setContentPane(window.menuPanel);
        window.setTitle("My Shelfie");
        window.setSize(500,500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
