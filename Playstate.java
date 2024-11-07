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
                    player1.attack();
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(field.getImage(), 0, 0, 1000, 800, this);

        Rectangle player1Bounds = new Rectangle(player1.x, player1.y, player1.im[player1.count].getIconWidth(), player1.im[player1.count].getIconHeight());
        Rectangle player2Bounds = new Rectangle(player2.x, player2.y, player2.im[player2.count].getIconWidth(), player2.im[player2.count].getIconHeight());
        // Draw player1
        if (player1 instanceof Knight knight1) {
            ImageIcon[] imToDraw = player1.isAttacking ? knight1.imAtk : knight1.im;
            if (player1.facingDirection == -1) {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x + imToDraw[player1.count].getIconWidth(), player1.y, -imToDraw[player1.count].getIconWidth(), imToDraw[player1.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x, player1.y, this);
            }
        } else if (player1 instanceof Mage mage1) {
            ImageIcon[] imToDraw = player1.isAttacking ? mage1.imAtk : mage1.im;
            if (player1.facingDirection == -1) {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x + imToDraw[player1.count].getIconWidth(), player1.y, -imToDraw[player1.count].getIconWidth(), imToDraw[player1.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x, player1.y, this);
            }
        } else if (player1 instanceof Demon demon1) {
            ImageIcon[] imToDraw = player1.isAttacking ? demon1.imAtk : demon1.im;
            if (player1.facingDirection == -1) {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x + imToDraw[player1.count].getIconWidth(), player1.y, -imToDraw[player1.count].getIconWidth(), imToDraw[player1.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player1.count].getImage(), player1.x, player1.y, this);
            }
        }
        drawHPBar(g, player1,true);

        // Draw player2 (attack animation if attacking)
        if (player2 instanceof Knight knight2) {
            ImageIcon[] imToDraw = player2.isAttacking ? knight2.imAtk : knight2.im;
            if (player2.facingDirection == -1) {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x + imToDraw[player2.count].getIconWidth(), player2.y, -imToDraw[player2.count].getIconWidth(), imToDraw[player2.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x, player2.y, this);
            }
        } else if (player2 instanceof Mage mage2) {
            ImageIcon[] imToDraw = player2.isAttacking ? mage2.imAtk : mage2.im;
            if (player2.facingDirection == -1) {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x + imToDraw[player2.count].getIconWidth(), player2.y, -imToDraw[player2.count].getIconWidth(), imToDraw[player2.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x, player2.y, this);
            }
        } else if (player2 instanceof Demon demon2) {
            ImageIcon[] imToDraw = player2.isAttacking ? demon2.imAtk : demon2.im;
            if (player2.facingDirection == -1) {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x + imToDraw[player2.count].getIconWidth(), player2.y, -imToDraw[player2.count].getIconWidth(), imToDraw[player2.count].getIconHeight(), this);
            } else {
                g.drawImage(imToDraw[player2.count].getImage(), player2.x, player2.y, this);
            }
            if (player1.isAttacking && player1.x + player1.im[player1.count].getIconWidth() > player2.x && player1.x < player2.x + player2.im[player2.count].getIconWidth()) {
        
            }
        }
          drawHPBar(g, player2,false);
        if (player1.isAttacking && player1Bounds.intersects(player2Bounds)) {
            player2.takeDamage(1); // Apply 10 damage to player 2
        }

        if (player2.isAttacking && player2Bounds.intersects(player1Bounds)) {
            player1.takeDamage(1); // Apply 10 damage to player 1
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
