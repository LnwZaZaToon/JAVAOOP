/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Playstate extends JPanel implements ActionListener {

    private ImageIcon field = new ImageIcon(this.getClass().getResource("Bg.png"));

    private Player player1;
    private Player player2;

    // Key state variables
    private boolean keyA = false; // For player1 left
    private boolean keyD = false; // For player1 right
    private boolean keyLeft = false; // For player2 left
    private boolean keyRight = false; // For player2 right

    private boolean isJumping1 = false, isJumping2 = false;
    private int yVelocity1 = 0, yVelocity2 = 0;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15; // Adjust for jump height

    public Playstate() {
        this.setFocusable(true);
        setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_A) {
                    keyA = true;
                    player1.facingDirection = -1;
                } else if (a == KeyEvent.VK_D) {
                    keyD = true;
                    player1.facingDirection = 1;
                }
                if (a == KeyEvent.VK_LEFT) {
                    keyLeft = true;
                    player2.facingDirection = -1;
                } else if (a == KeyEvent.VK_RIGHT) {
                    keyRight = true;
                    player2.facingDirection = 1;
                }

                // Handle jumping keys
                if (a == KeyEvent.VK_W && !isJumping1) { // Player 1 jump with W
                    isJumping1 = true;
                    yVelocity1 = JUMP_STRENGTH;
                }
                if (a == KeyEvent.VK_UP && !isJumping2) { // Player 2 jump with UP arrow
                    isJumping2 = true;
                    yVelocity2 = JUMP_STRENGTH;
                }
                if (a == KeyEvent.VK_SPACE) { // Player 1 attack
                    if (player1 instanceof Mage) {
                        Mage mage1 = (Mage) player1;  // Correct cast
                        mage1.castFireball();  // Call the Mage's fireball casting method
                        mage1.attack();
                    } else {
                        player1.attack();
                    }
                }
                if (a == KeyEvent.VK_ENTER) { // Player 2 attack
                    player2.attack();
                }
            }

            public void keyReleased(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_A) {
                    keyA = false;
                } else if (a == KeyEvent.VK_D) {
                    keyD = false;
                }
                if (a == KeyEvent.VK_LEFT) {
                    keyLeft = false;
                } else if (a == KeyEvent.VK_RIGHT) {
                    keyRight = false;
                }
                if (a == KeyEvent.VK_SPACE) {
                    player1.stopAttack();
                }
                if (a == KeyEvent.VK_ENTER) {
                    player2.stopAttack();
                }

                player1.count = 0;
                player2.count = 0;
            }
        });

        time.start();
        actor1.start();
        actor2.start();
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        player1.x = 100;
        player1.y = 500;

        player2.facingDirection = -1;
        player2.x = 800;
        player2.y = 500;
        if (player1 instanceof Demon) {
            player1.y = 460;
        }
        if (player2 instanceof Demon) {
            player1.y = 460;
        }

        repaint();
    }

    Thread time = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                repaint();
            }
        }
    });

    Thread actor1 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }

                if (keyA && player1.x > 0) {
                    player1.x -= 10;
                    player1.count = (player1.count + 1) % 6;
                }
                if (keyD && player1.x < getWidth() - player1.im[player1.count].getIconWidth()) {
                    player1.x += 10;
                    player1.count = (player1.count + 1) % 6;
                }

                if (isJumping1) {
                    player1.y += yVelocity1;
                    yVelocity1 += GRAVITY;

                    if (player1.y >= 500) {
                        player1.y = 500;
                        isJumping1 = false;
                        yVelocity1 = 0;
                    }
                }

                repaint();
            }
        }
    });

    Thread actor2 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }

                if (keyLeft && player2.x > 0) {
                    player2.x -= 10;
                    player2.count = (player2.count + 1) % 6;
                }
                if (keyRight && player2.x < getWidth() - player2.im[player2.count].getIconWidth()) {
                    player2.x += 10;
                    player2.count = (player2.count + 1) % 6;
                }

                if (isJumping2) {
                    player2.y += yVelocity2;
                    yVelocity2 += GRAVITY;

                    if (player2.y >= 500) {
                        player2.y = 500;
                        isJumping2 = false;
                        yVelocity2 = 0;
                    }
                }

                repaint();
            }
        }
    });

 private void drawPlayer(Graphics g, Player player) {
    ImageIcon[] imToDraw = player.isAttacking ? player.imAtk : player.im;
    if (player.facingDirection == -1) {
        g.drawImage(imToDraw[player.count].getImage(), player.x + imToDraw[player.count].getIconWidth(), player.y, -imToDraw[player.count].getIconWidth(), imToDraw[player.count].getIconHeight(), this);
    } else {
        g.drawImage(imToDraw[player.count].getImage(), player.x, player.y, this);
    }
}

public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(field.getImage(), 0, 0, 1000, 800, this);

    drawHPBar(g, player1, true);
    drawHPBar(g, player2, false);

    // Draw player1
    if (player1 instanceof Mage mage1) {
        mage1.updateFireballs();  // Update fireballs
        Iterator<Fireball> fireballIterator = mage1.fireballs.iterator();  // Use an iterator to safely remove fireballs
        while (fireballIterator.hasNext()) {
            Fireball fireball = fireballIterator.next();
            fireball.update();  // Move the fireballs
            g.drawImage(fireball.imfire[fireball.count % fireball.imfire.length].getImage(), fireball.x, fireball.y, this);

            // Check for collision with Player 2
            Rectangle fireballBounds = new Rectangle(fireball.x, fireball.y, fireball.imfire[fireball.count % fireball.imfire.length].getIconWidth(), fireball.imfire[fireball.count % fireball.imfire.length].getIconHeight());
            Rectangle player2Bounds = new Rectangle(player2.x, player2.y, player2.im[player2.count].getIconWidth(), player2.im[player2.count].getIconHeight());

            if (fireballBounds.intersects(player2Bounds)) {
            player2.takeDamage(50);  // Player 2 takes damage
            fireball.deactivate();  // Deactivate fireball after hit
            fireballIterator.remove();  // Remove fireball from the list
            }
        }
    }
    drawPlayer(g, player1); // Handle rendering for player1

    // Draw player2
    if (player2 instanceof Mage mage2) {
        mage2.updateFireballs();
        for (Fireball fireball : mage2.fireballs) {
            g.drawImage(fireball.imfire[fireball.count % fireball.imfire.length].getImage(), fireball.x, fireball.y, this);
        }
    }
    drawPlayer(g, player2); // Handle rendering for player2

    // Collision and damage logic
    Rectangle player1Bounds = new Rectangle(player1.x, player1.y, player1.im[player1.count].getIconWidth(), player1.im[player1.count].getIconHeight());
    Rectangle player2Bounds = new Rectangle(player2.x, player2.y, player2.im[player2.count].getIconWidth(), player2.im[player2.count].getIconHeight());

    if (player1.isAttacking && player1Bounds.intersects(player2Bounds)) {
        player2.takeDamage(1);
    }

    if (player2.isAttacking && player2Bounds.intersects(player1Bounds)) {
        player1.takeDamage(1);
    }
}


    private void drawHPBar(Graphics g, Player player, boolean isPlayer1) {
        // Define the width and height of the HP bar
        int barWidth = 250; // Width of the HP bar
        int barHeight = 10; // Height of the HP bar

        // Calculate the width of the HP bar based on the player's current HP
        int hpBarWidth = (int) ((double) player.getHP() / player.getMaxHP() * barWidth);

        // Position for top-left corner (Player 1)
        int barXLeft = 10;  // Small offset from the left edge
        int barY = 10;  // Small offset from the top edge

        // Position for top-right corner (Player 2)
        int barXRight = 1000 - barWidth - 30; // Assuming screen width is 500 (adjust as needed)

        // Draw the HP bar based on which player it is
        if (isPlayer1) {
            // Draw the border for Player 1's HP bar (top-left)
            g.setColor(Color.BLACK);  // Border color
            g.drawRect(barXLeft, barY, barWidth, barHeight); // Draw border around the whole bar

            // Fill the background of the HP bar (gray)
            g.setColor(Color.GRAY);
            g.fillRect(barXLeft + 1, barY + 1, barWidth - 2, barHeight - 2); // Leave space for the border

            // Fill the foreground of the HP bar (green) within the border
            g.setColor(Color.RED);
            g.fillRect(barXLeft + 1, barY + 1, Math.min(hpBarWidth, barWidth - 2), barHeight - 2); // Ensure the green bar doesn't overflow
        } else {
            // Draw the border for Player 2's HP bar (top-right)
            g.setColor(Color.BLACK);  // Border color
            g.drawRect(barXRight, barY, barWidth, barHeight); // Draw border around the whole bar

            // Fill the background of the HP bar (gray)
            g.setColor(Color.GRAY);
            g.fillRect(barXRight + 1, barY + 1, barWidth - 2, barHeight - 2); // Leave space for the border

            // Fill the foreground of the HP bar (green) within the border
            g.setColor(Color.RED);
            g.fillRect(barXRight + 1, barY + 1, Math.min(hpBarWidth, barWidth - 2), barHeight - 2); // Ensure the green bar doesn't overflow
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle any action events here if needed
    }
}
