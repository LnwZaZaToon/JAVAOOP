
package newgame;


public abstract class Player {
    private double HP;
    private double defense;
    private double strength;
    private double agility;
    public int x;
    public int y;
    public int count;
    public int facingDirection;
 
    public Player() {
        this.facingDirection = 1;
    }
    public Player(int x, int y) {
        this.facingDirection = 1;
        this.x = x;
        this.y = y;
    }

    public double getAgility() {
        return agility;
    }

    public double getDefense() {
        return defense;
    }

    public double getHP() {
        return HP;
    }

    public double getStrength() {
        return strength;
    }

    public void setAgility(double agility) {
        this.agility = agility;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
    
    
}
