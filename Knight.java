/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

import javax.swing.ImageIcon;

/**
 *
 * @author Toon
 */
public class Knight  extends Player{
    public ImageIcon[] im = new ImageIcon[7];
    
    public Knight(int x , int y){
        super(x,y);
    }
    
    
    public Knight() {
        this.setAgility(20);
        this.setHP(200);
        this.setDefense(200);
        this.setStrength(50);
        for(int i=0;i<im.length;i++){
            im[i] = new ImageIcon(this.getClass().getResource("Knight"+(i+1)+".png"));        
        }
        
    }
}

