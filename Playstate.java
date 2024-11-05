/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.Graphics;
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
    

    public Playstate() {
        this.setFocusable(true);
        setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_A) {
                    keyA = true; // Set keyA to true
                    player1.facingDirection = -1; // Face left
                } else if (a == KeyEvent.VK_D) {
                    keyD = true; // Set keyD to true
                    player1.facingDirection = 1; // Face right
                }
                if (a == KeyEvent.VK_LEFT) {
                    keyLeft = true; // Set keyLeft to true
                    player2.facingDirection = -1; // Face left
                } else if (a == KeyEvent.VK_RIGHT) {
                    keyRight = true; // Set keyRight to true
                    player2.facingDirection = 1; // Face right
                }
            }

            public void keyReleased(KeyEvent e) {
                int a = e.getKeyCode();
                if (a == KeyEvent.VK_A) {
                    keyA = false; // Reset keyA
                } else if (a == KeyEvent.VK_D) {
                    keyD = false; // Reset keyD
                }
                if (a == KeyEvent.VK_LEFT) {
                    keyLeft = false; // Reset keyLeft
                } else if (a == KeyEvent.VK_RIGHT) {
                    keyRight = false; // Reset keyRight
                }

                // Set animation to initial frame (im[0]) when key is released
                player1.count = 0; // Reset player1 animation frame
                player2.count = 0; // Reset player2 animation frame
            }
        });

        time.start();
        actor.start();
    }

public void setPlayers(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;

    player1.x=100;
    player1.y = 500;
    
    player2.facingDirection = -1;
    player2.x = 800;
    player2.y = 500;
    

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

    Thread actor = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                }

                // Move player1 based on key states
                if (keyA) {
                    player1.x -= 10;
                    player1.count++;
                    if (player1.count >= 7) {
                        player1.count = 1;
                    }
                } 
                if (keyD) {
                    player1.x += 10;
                    player1.count++;
                    if (player1.count >= 7) {
                        player1.count = 1;
                    }
                }

                // Move player2 based on key states
                if (keyLeft) {
                    player2.x -= 10;
                    player2.count++;
                    if (player2.count >= 7) {
                        player2.count = 1;
                    }
                } 
                if (keyRight) {
                 
                    player2.x += 10;
                    player2.count++;
                    if (player2.count >= 7) {
                        player2.count = 1;
                    }
                }

                repaint(); // Repaint the panel
            }
        }
    });

public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(field.getImage(), 0, 0, 1000, 800, this);
    
    
    
    
    

    // Draw player1
    if (player1 instanceof Knight knight1) {
        if (player1.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(knight1.im[player1.count].getImage(), player1.x + knight1.im[player1.count].getIconWidth(), player1.y, -knight1.im[player1.count].getIconWidth(), knight1.im[player1.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(knight1.im[player1.count].getImage(), player1.x, player1.y, this);
        }
    } else if (player1 instanceof Mage mage1) {
        if (player1.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(mage1.im[player1.count].getImage(), player1.x + mage1.im[player1.count].getIconWidth(), player1.y, -mage1.im[player1.count].getIconWidth(), mage1.im[player1.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(mage1.im[player1.count].getImage(), player1.x, player1.y, this);
        }
    } else if (player1 instanceof Demon demon1) {
        if (player1.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(demon1.im[player1.count].getImage(), player1.x + demon1.im[player1.count].getIconWidth(), player1.y, -demon1.im[player1.count].getIconWidth(), demon1.im[player1.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(demon1.im[player1.count].getImage(), player1.x, player1.y, this);
        }
    }

    // Draw player2
    if (player2 instanceof Knight knight2) {
        if (player2.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(knight2.im[player2.count].getImage(), player2.x + knight2.im[player2.count].getIconWidth(), player2.y, -knight2.im[player2.count].getIconWidth(), knight2.im[player2.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(knight2.im[player2.count].getImage(), player2.x, player2.y, this);
        }
    } else if (player2 instanceof Mage mage2) {
        if (player2.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(mage2.im[player2.count].getImage(), player2.x + mage2.im[player2.count].getIconWidth(), player2.y, -mage2.im[player2.count].getIconWidth(), mage2.im[player2.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(mage2.im[player2.count].getImage(), player2.x, player2.y, this);
        }
    } else if (player2 instanceof Demon demon2) {
        if (player2.facingDirection == -1) {
            // Flip the image for left-facing
            g.drawImage(demon2.im[player2.count].getImage(), player2.x + demon2.im[player2.count].getIconWidth(), player2.y, -demon2.im[player2.count].getIconWidth(), demon2.im[player2.count].getIconHeight(), this);
        } else {
            // Normal right-facing
            g.drawImage(demon2.im[player2.count].getImage(), player2.x, player2.y, this);
        }
    }
}


    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle any action events here if needed
    }
}
