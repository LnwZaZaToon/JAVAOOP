/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Playstate extends JPanel implements ActionListener {

    public ImageIcon field;
    public ImageIcon field2;
    public ImageIcon field3;
    private ImageIcon fieldDirt = new ImageIcon(this.getClass().getResource("dirt2.png"));
    private ImageIcon fieldHeart = new ImageIcon(this.getClass().getResource("heart2.png"));

    private ImageIcon fieldrestart = new ImageIcon(this.getClass().getResource("restartbtn.jpg"));
    private ImageIcon fieldexit = new ImageIcon(this.getClass().getResource("exitbtn.jpg"));

    JButton restartbtn = new JButton(fieldrestart);
    JButton exitbtn = new JButton(fieldexit);

    private static final int MATCH_DURATION = 30000; // Duration in milliseconds (1 minute)
    private int timeRemaining = MATCH_DURATION;
    private Thread timerThread; // Removed the duplicate declaration here
    private Thread gameThread;
    public boolean matchOver = false;

    private Player player1;
    private Player player2;
    private Playstate p1;

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

        restartbtn.setBounds(300, 400, 170, 90);
        add(restartbtn);
        exitbtn.setBounds(500, 400, 170, 90);
        add(exitbtn);
        restartbtn.setVisible(false);
        exitbtn.setVisible(false);

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
                        mage1.MageAttack();
                    } else if (player1 instanceof DarkMage) {
                        DarkMage mage1 = (DarkMage) player1;  // Correct cast
                        mage1.castFireball();  // Call the Mage's fireball casting method
                        mage1.MageAttack();
                    } else {
                        player1.attack();
                    }
                }
                if (a == KeyEvent.VK_ENTER) { // Player 2 attack
                    if (player2 instanceof Mage) {
                        Mage mage2 = (Mage) player2;  // Correct cast
                        mage2.castFireball();  // Call the Mage's fireball casting method
                        mage2.MageAttack();
                    } else if (player2 instanceof DarkMage) {
                        DarkMage mage1 = (DarkMage) player2;  // Correct cast
                        mage1.castFireball();  // Call the Mage's fireball casting method
                        mage1.MageAttack();
                    } else {
                        player2.attack();
                    }
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
                    if (player1 instanceof Mage mage1) {
                        mage1.StopMageAttack();
                    }
                    if (player1 instanceof DarkMage mage1) {
                        mage1.StopMageAttack();
                    }
                }
                if (a == KeyEvent.VK_ENTER) {
                    player2.stopAttack();
                    if (player2 instanceof Mage mage2) {
                        mage2.StopMageAttack();
                    }
                    if (player2 instanceof DarkMage mage2) {
                        mage2.StopMageAttack();
                    }
                }

                player1.count = 0;
                player2.count = 0;
            }
        });

        startTimerThread();
        actor1.start();
        actor2.start();
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        // Set initial x positions
        player1.x = 100;
        player2.facingDirection = -1;
        player2.x = 800;

        // Define the baseline Y position (the y-value for the 'same line')
        int lineY = 500;

        // Get the heights of the players' images (assuming you have methods to retrieve them)
        int player1Height = player1.getImageHeight(); // This should return the height of the image
        int player2Height = player2.getImageHeight(); // Same for player2

        // Adjust y positions based on their individual image heights
        player1.y = lineY - player1Height; // Align player1's bottom edge with the line
        player2.y = lineY - player2Height; // Align player2's bottom edge with the line

        // Handle Demon character with consistent height adjustments
        if (player1 instanceof Demon) {
            // Adjusting Demon for correct alignment
            player1.y = lineY - player1Height; // Remove additional tweak to ensure same line
        }

        if (player2 instanceof Demon) {
            // Adjusting Demon for correct alignment
            player2.y = lineY - player2Height; // Remove additional tweak to ensure same line
        }

        // Debug print statements to check the y-values
        System.out.println("Player 1 Y Position: " + player1.y);
        System.out.println("Player 2 Y Position: " + player2.y);

        repaint(); // Ensure the visual is updated
    }

    private void startTimerThread() {

        timerThread = new Thread(() -> {
            while (timeRemaining > 0 && !matchOver) {
                try {
                    Thread.sleep(1000);  // Wait for 1 second
                    timeRemaining -= 1000;  // Decrement the time counter
                    repaint();  // Update display
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            matchOver = true;  // Set match over when time is up

            repaint();
        });
        timerThread.start();  // Start the timer thread
    }

    Thread actor1 = new Thread(new Runnable() {
        public void run() {

            while (!matchOver) {
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

                    if (player1.y >= 414 && player1 instanceof Knight) {
                        player1.y = 414;
                        isJumping1 = false;
                        yVelocity1 = 0;
                    } else if (player1.y >= 372 && player1 instanceof Mage) {
                        player1.y = 372;
                        isJumping1 = false;
                        yVelocity1 = 0;
                    } else if (player1.y >= 372 && player1 instanceof Demon) {
                        player1.y = 372;
                        isJumping1 = false;
                        yVelocity1 = 0;
                    } else if (player1.y >= 372 && player1 instanceof DarkMage) {
                        player1.y = 372;
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
            while (!matchOver) {
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

                    if (player2 instanceof Knight && player2.y >= 414) {
                        player2.y = 414;  // Set to the landing position for Knight
                        isJumping2 = false;  // Stop jumping
                        yVelocity2 = 0;  // Reset velocity
                    } else if (player2 instanceof Mage && player2.y >= 372) {
                        player2.y = 372;  // Set to the landing position for Mage
                        isJumping2 = false;  // Stop jumping
                        yVelocity2 = 0;  // Reset velocity
                    } else if (player2 instanceof Demon && player2.y >= 372) {
                        player2.y = 372;  // Set to the landing position for Demon
                        isJumping2 = false;  // Stop jumping
                        yVelocity2 = 0;  // Reset velocity
                    } else if (player2 instanceof DarkMage && player2.y >= 372) {
                        player2.y = 372;  // Set to the landing position for Demon
                        isJumping2 = false;  // Stop jumping
                        yVelocity2 = 0;  // Reset velocity
                    }
                }

                repaint();
            }
        }
    });

    private void drawPlayer(Graphics g, Player player) {
        ImageIcon[] imToDraw;

        // Determine which animation to draw based on player's state
        if (player.getisHit()) {
            imToDraw = player.hitAnimationFrames;
            player.hitCounter++; // Increment hit frame counter

            // Reset `isHit` after displaying hit frame
            if (player.hitCounter >= player.hitAnimationFrames.length) {
                player.setHit(false); // Stop hit animation
                player.hitCounter = 0; // Reset counter
            }

        } else if (player.isAttacking) {
            imToDraw = player.imAtk;
        } else if (player instanceof Mage mage1 && mage1.getMageattack()) {
            imToDraw = player.imAtk;
        }else if (player instanceof DarkMage mage1 && mage1.getMageattack()) {
            imToDraw = player.imAtk;
        } else {
            imToDraw = player.im;
        }

        // Draw the image based on facing direction
        int imgWidth = imToDraw[Math.min(player.count, imToDraw.length - 1)].getIconWidth();
        int imgHeight = imToDraw[Math.min(player.count, imToDraw.length - 1)].getIconHeight();

        if (player.facingDirection == -1) {
            g.drawImage(imToDraw[Math.min(player.count, imToDraw.length - 1)].getImage(), player.x + imgWidth, player.y, -imgWidth, imgHeight, this);
        } else {
            g.drawImage(imToDraw[Math.min(player.count, imToDraw.length - 1)].getImage(), player.x, player.y, this);
        }
    }

    private void drawGameOverPanel(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));  // Black with transparency
        g.fillRect(0, 0, getWidth(), getHeight());
        String result = determineWinner();
        // Draw Game Over text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("" + " " + result, getWidth() / 2 - 190, getHeight() / 2 - 50);

        restartbtn.setVisible(true);
        exitbtn.setVisible(true);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        //       g.drawString("Press R to Restart", getWidth() / 2 - 120, getHeight() / 2 + 20);
        //      g.drawString("Press Q to Quit", getWidth() / 2 - 100, getHeight() / 2 + 70);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (field != null) {
            g.drawImage(field.getImage(), 0, 0, 1000, 800, this);
        } else if (field2 != null) {
            g.drawImage(field2.getImage(), 0, 0, 1000, 800, this);
        } else if (field3 != null) {
            g.drawImage(field3.getImage(), 0, 0, 1000, 800, this);
        }

        g.drawImage(fieldDirt.getImage(), 0, getHeight() - 262, 1000, 900, this);

        int seconds = timeRemaining / 1000;
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.drawString("Time Left: " + seconds + "s", getWidth() / 2 - 40, 20);

        // Check if match is over and display result
        if (player1.getHP() <= 0 || player2.getHP() <= 0) {
            matchOver = true;
            drawGameOverPanel(g);

        }

        if (matchOver) {
            drawGameOverPanel(g);
        }

        drawHPBar(g, player1, true);  // For player 1 (on left)
        drawHPBar(g, player2, false); // For player 2 (on right)

        // Draw player1
        if (player1 instanceof Mage mage1) {
            mage1.updateFireballs(); // Update fireballs list
            for (Fireball fireball : mage1.fireballs) {
                if (fireball.isActive()) {  // Draw only active fireballs
                    g.drawImage(fireball.getImage(), fireball.x, fireball.y, this);
                    // Check collision with player2
                    Rectangle player2Bounds = new Rectangle(
                            player2.x,
                            player2.y,
                            player2.im[Math.min(player2.count, player2.im.length - 1)].getIconWidth(),
                            player2.im[Math.min(player2.count, player2.im.length - 1)].getIconHeight()
                    );

                    if (fireball.getBound().intersects(player2Bounds)) {
                        player2.takeDamage(player1.getATK());  // Reduce player2 HP
                        fireball.deactivate();  // Deactivate fireball on collision
                        player2.setHit(true);    // Trigger hit animation
                        player2.hitCounter = 0;  // Reset hit counter for a fresh animation cycle
                    }
                }
            }
        }
        if (player1 instanceof DarkMage mage1) {
            mage1.updateFireballs(); // Update fireballs list
            for (FireballDark fireball : mage1.fireballs) {
                if (fireball.isActive()) {  // Draw only active fireballs
                    g.drawImage(fireball.getImage(), fireball.x, fireball.y, this);
                    // Check collision with player2
                    Rectangle player2Bounds = new Rectangle(
                            player2.x,
                            player2.y,
                            player2.im[Math.min(player2.count, player2.im.length - 1)].getIconWidth(),
                            player2.im[Math.min(player2.count, player2.im.length - 1)].getIconHeight()
                    );

                    if (fireball.getBound().intersects(player2Bounds)) {
                        player2.takeDamage(player1.getATK());  // Reduce player2 HP
                        fireball.deactivate();  // Deactivate fireball on collision
                        player2.setHit(true);    // Trigger hit animation
                        player2.hitCounter = 0;  // Reset hit counter for a fresh animation cycle
                    }
                }
            }
        }

        drawPlayer(g, player1);

        if (player2 instanceof Mage mage2) {
            mage2.updateFireballs(); // Update fireballs list
            for (Fireball fireball : mage2.fireballs) {
                if (fireball.isActive()) {  // Draw only active fireballs
                    g.drawImage(fireball.getImage(), fireball.x, fireball.y, this);

                    // Check collision with player1
                    Rectangle player1Bounds = new Rectangle(
                            player1.x,
                            player1.y,
                            player1.im[Math.min(player1.count, player1.im.length - 1)].getIconWidth(),
                            player1.im[Math.min(player1.count, player1.im.length - 1)].getIconHeight()
                    );

                    if (fireball.getBound().intersects(player1Bounds)) {
                        player1.takeDamage(player2.getATK());  // Reduce player1 HP
                        fireball.deactivate();   // Deactivate fireball on collision
                        player1.setHit(true);    // Trigger hit animation
                        player1.hitCounter = 0;  // Reset hit counter for a fresh animation cycle
                    }
                }
            }
        }
        if (player2 instanceof DarkMage mage2) {
            mage2.updateFireballs(); // Update fireballs list
            for (FireballDark fireball : mage2.fireballs) {
                if (fireball.isActive()) {  // Draw only active fireballs
                    g.drawImage(fireball.getImage(), fireball.x, fireball.y, this);

                    // Check collision with player1
                    Rectangle player1Bounds = new Rectangle(
                            player1.x,
                            player1.y,
                            player1.im[Math.min(player1.count, player1.im.length - 1)].getIconWidth(),
                            player1.im[Math.min(player1.count, player1.im.length - 1)].getIconHeight()
                    );

                    if (fireball.getBound().intersects(player1Bounds)) {
                        player1.takeDamage(player2.getATK());  // Reduce player1 HP
                        fireball.deactivate();   // Deactivate fireball on collision
                        player1.setHit(true);    // Trigger hit animation
                        player1.hitCounter = 0;  // Reset hit counter for a fresh animation cycle
                    }
                }
            }
        }

        drawPlayer(g, player2);

        // Collision and damage logic
        Rectangle player1Bounds = new Rectangle(player1.x, player1.y, player1.im[player1.count].getIconWidth(), player1.im[player1.count].getIconHeight());
        Rectangle player2Bounds = new Rectangle(player2.x, player2.y, player2.im[player2.count].getIconWidth(), player2.im[player2.count].getIconHeight());

        if (player1.isAttacking && player1Bounds.intersects(player2Bounds)) {
            player2.takeDamage(player1.getATK());
        }

        if (player2.isAttacking && player2Bounds.intersects(player1Bounds)) {
            player1.takeDamage(player2.getATK());
        }
    }

    private String determineWinner() {
        if (player1.getHP() > player2.getHP()) {
            return "Player 1 Wins!";
        } else if (player2.getHP() > player1.getHP()) {
            return "Player 2 Wins!";
        } else {
            return "It's a Tie!";
        }
    }

    private void drawHPBar(Graphics g, Player player, boolean isPlayer1) {
        int hpWidth = 200; // Fixed width of the HP bar
        int hpHeight = 20; // Fixed height of the HP bar
        int hpCurrentWidth = (int) ((double) player.getHP() / player.getMaxHP() * hpWidth); // The filled portion of the bar

        // Positioning of the HP bar
        int xPos = isPlayer1 ? 50 : getWidth() - 250; // Position for Player 1 and Player 2
        int yPos = 20; // Vertical positioning

        // Draw the background of the HP bar (gray color)
        g.setColor(Color.GRAY);
        g.fillRect(xPos, yPos, hpWidth, hpHeight);

        // Draw the current HP on the bar (red color)
        g.setColor(Color.RED);
        g.fillRect(xPos, yPos, hpCurrentWidth, hpHeight);

        // Optionally, draw the current HP as text on the bar
        g.setColor(Color.WHITE);
        g.drawString(player.getHP() + " / " + player.getMaxHP(), xPos + hpWidth / 2 - 20, yPos + 15);

        if (isPlayer1) {
            g.drawImage(fieldHeart.getImage(), 15, 5, 50, 50, this); // Left side for Player 1
        } else {
            g.drawImage(fieldHeart.getImage(), 700, 5, 50, 50, this); // Right side for Player 2
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle any action events here if needed
    }
}
