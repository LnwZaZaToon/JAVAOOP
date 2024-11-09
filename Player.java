package newgame;

import javax.swing.ImageIcon;

public abstract class Player {

    // Fields for Player attributes
    private int hp;
    private int maxHP;
    private int ATK;
    public int x;
    public int y;
    public int count;
    public int facingDirection;
    public ImageIcon[] im = new ImageIcon[7];
    public ImageIcon[] imAtk = new ImageIcon[6];
    public ImageIcon[] hitAnimationFrames = new ImageIcon[1];  
    public boolean isAttacking = false;
    private boolean isHit = false;

    public int hitCounter = 0; 

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0; 
        }
                isHit = true; 
        hitCounter = 0; 
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }
    
   



    public boolean getisHit() {
        return isHit;
    }
    
    
    public abstract  void attack();


  
 /*   public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            count = 3;  
        }
    }
*/

 
    public void stopAttack() {
        isAttacking = false;
    }

   
    public Player() {
        this.facingDirection = 1;
        this.hp = this.maxHP; 
    }

   
    public Player(int x, int y) {
        this.facingDirection = 1;
        this.x = x;
        this.y = y;
        this.maxHP = 100; 
        this.hp = this.maxHP; 
    }




        public void setHit(boolean hit) {
        isHit = hit;
        hitCounter = 0; // Reset counter to start the animation
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
