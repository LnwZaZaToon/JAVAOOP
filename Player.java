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
    public boolean isAttacking = false;

    // Method to take damage and reduce HP
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0; // Prevent negative HP
        }
    }

    // Method to heal the player
    public void heal(int healingAmount) {
        this.hp += healingAmount;
        if (this.hp > this.maxHP) {
            this.hp = this.maxHP; // Ensure HP doesn't exceed max
        }
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
        this.maxHP = 100; // Set a default max HP
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

    // Getters and Setters for the player's attributes

    public double getAgility() {
        return agility;
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
}
