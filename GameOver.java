/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver extends JPanel {

    public JButton BStartover = new JButton("Restart");
    public JButton BExitover = new JButton("GameOver");

    public GameOver() {
        this.setLayout(null);
        BExitover.setBounds(500, 650, 150, 90);
        add(BExitover);
        add(BStartover);
        BStartover.setBounds(750, 650, 150, 90);
        add(BStartover);
    }
    	public void paintComponent(Graphics g){
		  super.paintComponent(g);

		  
		  
	      
	  }

}
