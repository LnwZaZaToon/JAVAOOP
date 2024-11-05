/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import javax.swing.ImageIcon;

/**
 *
 * @author Toon
 */
public class Mage extends Player {

    public ImageIcon[] im = new ImageIcon[7];

    public Mage() {
        this.setAgility(100);
        this.setHP(50);
        this.setDefense(100);
        this.setStrength(200);
        for (int i = 0; i < im.length; i++) {
            im[i] = new ImageIcon(this.getClass().getResource("wizard" + (i + 1) + ".png"));
        }
    }

}
