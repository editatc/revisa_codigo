/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.iberotec.objetos;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author JERSON
 */
public abstract class Sprite {
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int ancho;
    protected int altura;
    protected ImageIcon foto;
    protected Rectangle contorno;

    public Sprite(int x, int y, int vx, int vy, int ancho, int altura, ImageIcon foto){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ancho = ancho;
        this.altura = altura;
        this.foto = foto;
        this.contorno = new Rectangle();
    }
    
    public boolean huboImpacto(Sprite otroObjeto){
        return this.contorno.intersects(otroObjeto.contorno);
    }
    
    public abstract void mover(int anchoLienzo, int alturaLienzo);
    
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the vx
     */
    public int getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(int vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public int getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(int vy) {
        this.vy = vy;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the foto
     */
    public ImageIcon getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
    
    
}
