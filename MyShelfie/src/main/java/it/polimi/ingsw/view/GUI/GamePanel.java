package it.polimi.ingsw.view.GUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private ImageIcon MyShelfieIcon = new ImageIcon("./resources/sfondo.jpg");
    private JLabel icon;
    public GamePanel(){
        setSize(500,500);
        icon = new JLabel(MyShelfieIcon);
        add(icon);
    }



}
