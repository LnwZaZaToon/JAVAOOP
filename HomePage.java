/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JPanel {

    private ImageIcon feild = new ImageIcon(this.getClass().getResource("Bg.png"));
    public JButton BStart = new JButton("Start");
    public JButton BExit = new JButton("Exit");

    public HomePage() {
        setLayout(null);
        BStart.setBounds(300, 300, 170,90);
        BExit.setBounds(500, 300, 170,90);
        add(BStart);
        add(BExit);
       
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(feild.getImage(),0,0,1000,800,this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("2005_iannnnnTKO", Font.CENTER_BASELINE, 90));
        g.drawString("Heroes of Empires", 90, 200);
    }

}
