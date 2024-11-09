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
public class Knight extends Player {



    public Knight() {
        this.setATK(20);
        this.setHP(1000);
        this.setMaxHP(1000);
        for (int i = 0; i < im.length; i++) {
            im[i] = new ImageIcon(this.getClass().getResource("Knight" + (i + 1) + ".png"));
        }
        for (int i = 0; i < imAtk.length; i++) {
            imAtk[i] = new ImageIcon(this.getClass().getResource("Knightattack" + (i + 1) + ".png"));
        }
        for (int i = 0; i < 1; i++) {
            hitAnimationFrames[i] = new ImageIcon(this.getClass().getResource("Knighthit" + (i + 1) + ".png"));
        }

    }
  
    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            count = 3;  
        }
    }
}
