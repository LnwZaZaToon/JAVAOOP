/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Mage extends Player {
    public List<Fireball> fireballs = new ArrayList<>();

    public Mage() {
        this.setHP(200);
        this.setMaxHP(200);
        // Initialize mage images
        for (int i = 0; i < im.length; i++) {
            im[i] = new ImageIcon(this.getClass().getResource("wizard" + (i + 1) + ".png"));
        }
        for (int i = 0; i < imAtk.length; i++) {
            imAtk[i] = new ImageIcon(this.getClass().getResource("Wizardattack" + (i + 1) + ".png"));
        }
        
                for (int i = 0; i < 1; i++) {
            hitAnimationFrames[i] = new ImageIcon(this.getClass().getResource("Magehit" + (i + 1) + ".png"));
        }
    }

    // Method to cast a fireball
    public void castFireball() {
        Fireball fireball = new Fireball(this.x, this.y, this.facingDirection);  // Pass the direction here
        fireballs.add(fireball);
    }

    // Method to update all fireballs (e.g., move them upward)
public void updateFireballs() {
    fireballs.removeIf(fireball -> !fireball.isActive()); // Remove inactive fireballs
}

}
