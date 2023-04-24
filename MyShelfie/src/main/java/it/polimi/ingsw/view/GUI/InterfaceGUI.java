package it.polimi.ingsw.view.GUI;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI interface class
 * @author Stefano Morano
 */
public class InterfaceGUI extends JFrame {
    private JPanel mainPanel;
    private JTable Board;
    private JTextField nameField;
    private JButton createGameButton;
    private JButton joinGameButton;
    private JTable table1;
    private JPanel menuPanel;
    private JTextField textName;
    private JPanel gamePanel;
    private JButton backButton;
    private JTextArea nameArea;
    private JLabel MyShelfie;
    private String name;
    public InterfaceGUI() {
    ImageIcon backgroundImage = new ImageIcon(getClass().getResource("sfondo.jpg"));
    ImageIcon logo = new ImageIcon(getClass().getResource("./Publisher Material/Title 2000x618px.png"));
    setTitle("My Shelfie");
    MyShelfie = new JLabel(logo);
    //menuPanel.add(MyShelfie);
    //JLabel background = new JLabel(new ImageIcon(image));
    setContentPane(mainPanel);
    setSize(500,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Graphics g= mainPanel.getGraphics();
    //g.drawImage(,0,0,null);
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

    public static void main(String[] args){
         new InterfaceGUI();
    }
}
