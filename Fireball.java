package newgame;

import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fireball extends JPanel {

    public ImageIcon[] imfire = new ImageIcon[10]; // Array to hold the images for animation
    public int x, y; // Position of the fireball
    public int count = 0; // Counter for animation frames
    public int direction; // Direction: 1 for right, -1 for left
    public boolean isActive = true; // Fireball's active state
    public int velocity = 5; // Speed of the fireball

    // Constructor to initialize the fireball with its position and direction
    public Fireball(int x, int y, int direction) {
        for (int i = 0; i < imfire.length; i++) {
            // Load fireball images from the resources folder
            imfire[i] = new ImageIcon(this.getClass().getResource("b" + (i + 1) + ".png"));
        }
        this.x = x;
        this.y = y;
        this.direction = direction; // Set direction based on player's facing direction
    }

    // Method to move the fireball based on direction
    public void move() {
        if (isActive) {
            this.x += velocity * direction; // Move the fireball in the specified direction
            count++; // Update animation frame (if you want to animate)
            if (count >= imfire.length) {
                count = 0; // Reset frame count if needed
            }
        }
    }

    // Method to get the boundary of the fireball for collision detection
    public Rectangle2D getBound() {
        return new Rectangle2D.Double(x, y, 25, 25); // Size of the fireball (adjust as needed)
    }

    // Method to update the fireball's state, like moving and deactivating when it goes off-screen
    public void update() {
        if (isActive) {
            move(); // Move the fireball
            // Check if the fireball goes off-screen (assuming a screen width of 1000)
            if (this.x < 0 || this.x > 1000) {
                deactivate(); // Deactivate fireball if out of screen bounds
            }
        }
    }

    // Deactivate the fireball (e.g., remove from game objects or set it to inactive)
   public void deactivate() {
        this.isActive = false; // Mark the fireball as inactive
    }

    // Optional: Getter for the current animation frame to be drawn (can be used in the paintComponent)
    public ImageIcon getCurrentFrame() {
        return imfire[count]; // Return the current animation frame based on 'count'
    }

    // Optional: Method to check if fireball is active (for cleanup or collision purposes)
    public boolean isActive() {
        return isActive;
    }
}
