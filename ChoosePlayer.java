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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChoosePlayer extends JPanel {

    private ImageIcon feild = new ImageIcon(this.getClass().getResource("Bg.png"));
    Player player1;
    Player player2;
    String class1;
    String class2;
    JButton Heros1 = new JButton("Heros");
    JButton Demons1 = new JButton("Demons");
    JButton Heros2 = new JButton("Heros");
    JButton Demons2 = new JButton("Demons");
    
    JButton Bnext = new JButton("Next");
    JButton Bback = new JButton("Back");
    JButton Breset = new JButton("Reset");

    // knight
    JButton KnightClass1 = new JButton("Knight");
    JButton KnightClass2 = new JButton("Knight");
    //demon
    JButton DemonsClass1 = new JButton("Demons");
    JButton DemonsClass2 = new JButton("Demons");
    // mage
    JButton MageClass1 = new JButton("Mage");
    JButton MageClass2 = new JButton("Mage");

    JLabel p1Status = new JLabel("");
    JLabel p2Status = new JLabel("");

    public ChoosePlayer() {
        setLayout(null);
        setLayout(null);
        Heros1.setBounds(10, 200, 170, 90);
        add(Heros1);
        Demons1.setBounds(200, 200, 170, 90);
        add(Demons1);
        Heros2.setBounds(610, 200, 170, 90);
        add(Heros2);
        Demons2.setBounds(800, 200, 170, 90);
        add(Demons2);
        p1Status.setBounds(170, 100, 170, 90);
        add(p1Status);
        p2Status.setBounds(780, 100, 170, 90);
        add(p2Status);

        KnightClass1.setBounds(10, 350, 170, 90);
        KnightClass2.setBounds(610, 350, 170, 90);

        MageClass1.setBounds(200, 350, 170, 90);
        MageClass2.setBounds(800, 350, 170, 90);

        DemonsClass1.setBounds(10, 350, 170, 90);
        DemonsClass2.setBounds(610, 350, 170, 90);

        DemonsClass1.setVisible(false);
        KnightClass1.setVisible(false);
        MageClass1.setVisible(false);
        KnightClass2.setVisible(false);
        MageClass2.setVisible(false);
        DemonsClass2.setVisible(false);
        
        Bback.setBounds(250, 600, 170, 90);
        Breset.setBounds(440, 600, 170, 90);
        Bnext.setBounds(630, 600, 170, 90);
        add(Bback);
        add(Breset);
        add(Bnext);

        add(KnightClass1);
        add(KnightClass2);
        add(MageClass1);
        add(MageClass2);
        add(DemonsClass1);
        add(DemonsClass2);

    }

    public void CheckClassPlayer1() {
        if (class1.equals("Heros")) {
            DemonsClass1.setVisible(false);
            KnightClass1.setVisible(true);
            MageClass1.setVisible(true);
        } else if (class1.equals("Demons")) {
            DemonsClass1.setVisible(true);
            KnightClass1.setVisible(false);
            MageClass1.setVisible(false);
        }
    }

    public void CheckClassPlayer2() {
        if (class2.equals("Heros")) { 
            DemonsClass2.setVisible(false);
            KnightClass2.setVisible(true);
            MageClass2.setVisible(true);
        } else if (class2.equals("Demons")) {
            DemonsClass2.setVisible(true);
            KnightClass2.setVisible(false);
            MageClass2.setVisible(false);
        }
    }
    public void CreateClassPlayer1(Player object){
           player1 = object;
    }
    public void CreateClassPlayer2(Player object){
           player2 = object;
    }
    public boolean CheckPlayerNULL(){
        return player1 != null && player2 != null;
    }
    
    public void Reset() {
        class1 = null; // Reset class1
        class2 = null; // Reset class2
        player1 = null; // Reset player1
        player2 = null; // Reset player2
        
        // Clear status labels
        p1Status.setText("");
        p2Status.setText("");
        
        // Hide all class selection buttons
        DemonsClass1.setVisible(false);
        KnightClass1.setVisible(false);
        MageClass1.setVisible(false);
        DemonsClass2.setVisible(false);
        KnightClass2.setVisible(false);
        MageClass2.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(feild.getImage(), 0, 0, 1000, 800, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("2005_iannnnnTKO", Font.CENTER_BASELINE, 50));
        g.drawString("Player1", 100, 100);
        g.drawString("Player2 ", 700, 100);
    }

}