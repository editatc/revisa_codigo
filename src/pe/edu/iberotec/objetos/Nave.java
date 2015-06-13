/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.iberotec.objetos;

import javax.swing.ImageIcon;

/**
 *
 * @author JERSON
 */
public class Nave extends Sprite {

    public Nave(int x, int y, int vx, int vy, int ancho, int altura, ImageIcon foto) {
        super(x, y, vx, vy, ancho, altura, foto);
    }

    
    
    @Override
    public void mover(int anchoLienzo, int alturaLienzo) {
        if(x+vx<0 || x+vx+ancho>anchoLienzo){
            vx = 0;
        }
      //  x = x +vx;
        y = y +vy;
        contorno.setBounds(x, y, ancho, altura);
    }
    
}
