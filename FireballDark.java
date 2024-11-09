package newgame;

import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FireballDark extends JPanel implements Runnable {

    public ImageIcon[] imfire = new ImageIcon[9]; // Array to hold the images for animation
    public int x, y; // Position of the fireball
    public int count = 0; // Counter for animation frames
    public int direction; // Direction: 1 for right, -1 for left
    public boolean isActive = true; // Fireball's active state
    public int velocity = 5; // Speed of the fireball
    private Thread fireballThread;

    // Constructor to initialize the fireball with its position and direction
    public FireballDark(int x, int y, int direction) {
        for (int i = 0; i < imfire.length; i++) {
            // Load fireball images from the resources folder
            imfire[i] = new ImageIcon(this.getClass().getResource("d" + (i + 1) + ".png"));
        }
        this.x = x+60;
        this.y = y+30;
        this.direction = direction; // Set direction based on player's facing direction

        startThread(); // Start the fireball's movement thread immediately
    }

    // Start the thread for the fireball animation and movement
    private void startThread() {
        fireballThread = new Thread(this);
        fireballThread.start();
    }

    // Method to move the fireball based on direction and update animation frame
    public void move() {
        if (isActive) {
            this.x += velocity * direction; // Move the fireball in the specified direction
            count = (count + 1) % imfire.length; // Cycle through animation frames
        }
    }

    // Main thread logic
public void run() {
    try {
        while (isActive) {
            move(); // Move the fireball based on direction
            checkOutOfBounds(); // Check if the fireball is out of bounds

            Thread.sleep(10); // Control speed (adjust if needed)
        }
    } catch (InterruptedException e) {
        // Thread was interrupted, exit gracefully
        Thread.currentThread().interrupt(); // Reset the interrupted status
    }
}

    // Method to check if fireball is out of bounds
    public void checkOutOfBounds() {
        if (this.x < 0 || this.x > 1000) { // Assuming screen width is 1000
            deactivate();
        }
    }

    // Deactivate the fireball
    public void deactivate() {
        this.isActive = false;  // Mark as inactive
        if (fireballThread != null && fireballThread.isAlive()) {
            fireballThread.interrupt();  // Stop the thread
        }
    }
    
    

    // Get the current animation frame based on the count
    public Image getImage() {
        return imfire[count].getImage(); // Use count to get the current animation frame
    }

    // Optional: Method to check if fireball is active
    public boolean isActive() {
        return isActive;
    }

    // Get the bounds for collision detection
    public Rectangle2D getBound() {
        return new Rectangle2D.Double(x, y, 25, 25); // Size of the fireball
    }
}
