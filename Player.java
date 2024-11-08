package newgame;

import javax.swing.ImageIcon;

public abstract class Player {

    // Fields for Player attributes
    private int hp;
    private int maxHP;
    private double defense;
    private double strength;
    private double agility;
    public int x;
    public int y;
    public int count;
    public int facingDirection;
    public ImageIcon[] im = new ImageIcon[7];
    public ImageIcon[] imAtk = new ImageIcon[6];
    public ImageIcon[] hitAnimationFrames = new ImageIcon[1];  // Hit animation frames
    public boolean isAttacking = false;
    
    private boolean isHit = false;
    private int hitAnimationDuration = 30; // Duration for the hit animation
    public int hitCounter = 0; // Counter for hit animation frames

    // Method to take damage and reduce HP
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0; // Prevent negative HP
        }
                isHit = true; // Start hit animation
        hitCounter = 0; // Reset hit animation counter
    }
    
   

    // Method to heal the player
    public void heal(int healingAmount) {
        this.hp += healingAmount;
        if (this.hp > this.maxHP) {
            this.hp = this.maxHP; // Ensure HP doesn't exceed max
        }
    }

    public boolean getisHit() {
        return isHit;
    }
    



    // Method to initiate attack
    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            count = 3;  // Example of setting attack animation frame
        }
    }

    // Method to stop attacking
    public void stopAttack() {
        isAttacking = false;
    }

    // Constructor for Player with default facing direction and max HP
    public Player() {
        this.facingDirection = 1;
        this.hp = this.maxHP; // Initialize hp to max HP
    }

    // Constructor for Player with specific x, y positions and max HP
    public Player(int x, int y) {
        this.facingDirection = 1;
        this.x = x;
        this.y = y;
        this.maxHP = 100; // Set a default max HP
        this.hp = this.maxHP; // Initialize hp to max HP
    }

    public void updatePlayerAnimation() {
        if (isHit) {
            // Display hit animation frame
            if (hitCounter < hitAnimationFrames.length) {
                count = hitCounter; // Set current frame for hit animation
            }
            hitCounter++;
            
            if (hitCounter >= hitAnimationDuration) {
                isHit = false;  // Reset hit state after animation
                count = 0; // Reset to normal animation
            }
        } else if (isAttacking) {
            count++; // Increment count for attack animation
            if (count >= imAtk.length) {
                isAttacking = false;  // Reset attack flag when done
                count = 0;  // Reset count
            }
        } else {
            count++; // Increment count for normal animation
            if (count >= im.length) {
                count = 0;  // Loop back to first frame
            }
        }
    }

    // Getters and Setters for the player's attributes
    public double getAgility() {
        return agility;
    }
        public void setHit(boolean hit) {
        isHit = hit;
        hitCounter = 0; // Reset counter to start the animation
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public int getImageHeight() {
        if (im[0] != null) {  // Ensure the image is not null
            return im[0].getIconHeight(); // Return the image height
        } else {
            return 0;  // Return 0 if the image is not loaded yet
        }
    }

    public int getHP() {
        return hp;  // Returns the current HP value
    }

    public int getMaxHP() {
        return maxHP;  // Returns the maximum HP
    }

    public void setHP(int hp) {
        this.hp = hp;  // Sets the HP
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
    
        public void reduceHP(int amount) {
        this.hp = Math.max(0, hp - amount);  // Ensure HP doesn't go below 0
    }

    public void restoreHP(int amount) {
        this.hp = Math.min(maxHP, hp + amount);  // Ensure HP doesn't exceed maxHP
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    
    
}
