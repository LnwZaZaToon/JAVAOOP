/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOver extends JPanel {
    public GameOver(String result) {
        setLayout(new BorderLayout());
        JLabel message = new JLabel(result, SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 30));
        message.setForeground(Color.RED);
        add(message, BorderLayout.CENTER);

        JButton retryButton = new JButton("Play Again");
        retryButton.addActionListener(e -> {
            // Add action to restart the game
        });
        add(retryButton, BorderLayout.SOUTH);
    }
}