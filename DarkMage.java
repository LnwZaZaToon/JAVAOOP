/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class DarkMage extends Player {
    public List<FireballDark> fireballs = new ArrayList<>();
    private boolean Mageattack = false;

    public DarkMage() {
        this.setATK(40);
        this.setHP(700);
        this.setMaxHP(700);
        // Initialize mage images
        for (int i = 0; i < im.length; i++) {
            im[i] = new ImageIcon(this.getClass().getResource("DarkMagerun" + (i + 1) + ".png"));
        }
        for (int i = 0; i < imAtk.length; i++) {
            imAtk[i] = new ImageIcon(this.getClass().getResource("DarkMagicatk.png"));
        }
        
                for (int i = 0; i < 1; i++) {
            hitAnimationFrames[i] = new ImageIcon(this.getClass().getResource("DarkMagehit" + (i + 1) + ".png"));
        }
    }

    // Method to cast a fireball
    public void castFireball() {
        FireballDark fireball = new FireballDark(this.x, this.y, this.facingDirection);  // Pass the direction here
        fireballs.add(fireball);
    }
    
    public void MageAttack(){
        if (!Mageattack) {
           Mageattack = true;
            count = 3;  // Example of setting attack animation frame
        }
    }
    
    public void StopMageAttack(){
           Mageattack = false;
    
    }
    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            count = 3;  
        }
    }
    
    public boolean getMageattack(){
        return Mageattack;
    }

    // Method to update all fireballs (e.g., move them upward)
public void updateFireballs() {
    fireballs.removeIf(fireball -> !fireball.isActive()); // Remove inactive fireballs
}

}
