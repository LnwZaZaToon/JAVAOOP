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
public class Demon extends Player {

    

    public Demon() {
        this.setAgility(150);
        this.setHP(500);
        this.setDefense(150);
        this.setStrength(20);
        for (int i =0; i <  im.length; i++) {
            im[i] = new ImageIcon(this.getClass().getResource("demon"+(i+1) + ".png"));
        }
    }

}
