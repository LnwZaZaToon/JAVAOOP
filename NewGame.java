/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package newgame;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;

public class NewGame extends JFrame implements ActionListener {

    HomePage home = new HomePage();
    ChoosePlayer chooseplayer = new ChoosePlayer();
    Playstate playstate = new Playstate();

    public NewGame() throws HeadlessException {

        this.add(home);
        home.BStart.addActionListener(this);
        home.BExit.addActionListener(this);
        chooseplayer.Heros1.addActionListener(this);
        chooseplayer.Demons1.addActionListener(this);
        chooseplayer.Heros2.addActionListener(this);
        chooseplayer.Demons2.addActionListener(this);

        //choose class
        chooseplayer.KnightClass1.addActionListener(this);
        chooseplayer.KnightClass2.addActionListener(this);

        chooseplayer.DemonsClass1.addActionListener(this);
        chooseplayer.DemonsClass2.addActionListener(this);

        chooseplayer.MageClass1.addActionListener(this);
        chooseplayer.MageClass2.addActionListener(this);

        chooseplayer.Bnext.addActionListener(this);
        chooseplayer.Bback.addActionListener(this);
        chooseplayer.Breset.addActionListener(this);

    }

    public static void main(String[] args) {
        NewGame f = new NewGame();
        f.setSize(1000, 800);
        f.setTitle("Heroes of Empire");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home.BStart) {
            this.setLocationRelativeTo(null);
            this.remove(home);
            this.add(chooseplayer);
            chooseplayer.requestFocusInWindow();

        } else if (e.getSource() == home.BExit) {
            System.exit(0);
        } else if (e.getSource() == chooseplayer.Heros1) {
            chooseplayer.p1Status.setText("Player 1 chooses heroes");
            chooseplayer.class1 = "Heros";
            chooseplayer.CheckClassPlayer1();
        } else if (e.getSource() == chooseplayer.Demons1) {
            chooseplayer.p1Status.setText("Player 1 chooses Demons");
            chooseplayer.class1 = "Demons";
            chooseplayer.CheckClassPlayer1();
        } else if (e.getSource() == chooseplayer.Heros2) {
            chooseplayer.p2Status.setText("Player 2 chooses heroes");
            chooseplayer.class2 = "Heros";
            chooseplayer.CheckClassPlayer2();
        } else if (e.getSource() == chooseplayer.Demons2) {
            chooseplayer.p2Status.setText("Player 2 chooses Demons");
            chooseplayer.class2 = "Demons";
            chooseplayer.CheckClassPlayer2();
        } //
        else if (e.getSource() == chooseplayer.DemonsClass1) {
            chooseplayer.CreateClassPlayer1(new Demon());
        } else if (e.getSource() == chooseplayer.DemonsClass2) {
            chooseplayer.CreateClassPlayer2(new Demon());

        } else if (e.getSource() == chooseplayer.KnightClass1) {
            chooseplayer.CreateClassPlayer1(new Knight());
        } else if (e.getSource() == chooseplayer.KnightClass2) {
            chooseplayer.CreateClassPlayer2(new Knight());

        } else if (e.getSource() == chooseplayer.MageClass1) {
            chooseplayer.CreateClassPlayer1(new Mage());
        } else if (e.getSource() == chooseplayer.MageClass2) {
            chooseplayer.CreateClassPlayer2(new Mage());
        } else if (e.getSource() == chooseplayer.Bback) {
            chooseplayer.Reset();
            this.setLocationRelativeTo(null);
            this.remove(chooseplayer);
            this.add(home);
            home.requestFocusInWindow();

        } else if (e.getSource() == chooseplayer.Breset) {
            chooseplayer.Reset();

        } else if (e.getSource() == chooseplayer.Bnext) {
            if (chooseplayer.CheckPlayerNULL()) {
                 playstate.setPlayers(chooseplayer.player1, chooseplayer.player2);
                this.setLocationRelativeTo(null);
                this.remove(chooseplayer);
                this.add(playstate);
                playstate.requestFocusInWindow();

            }
            else{
                showMessageDialog(null, "you have to select player 1 and 2 first");
            }
        }
        this.validate();
        this.repaint();
    }

}