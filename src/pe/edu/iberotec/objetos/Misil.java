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
public class Misil extends Sprite {
    private boolean eliminado;
  
    public Misil(int x, int y, int vx, int vy, int ancho, int altura, ImageIcon foto) {
        super(x, y, vx, vy, ancho, altura, foto);
    }

    @Override
    public void mover(int anchoLienzo, int alturaLienzo) {
        if(y+vy+altura<0 || y+vy>anchoLienzo){
            eliminado = true;
        }
        x = x + vy;
        contorno.setBounds(x, y, ancho, altura);
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
}
