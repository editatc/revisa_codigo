/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.iberotec.ejecutable;

import javax.swing.JFrame;
import pe.edu.iberotec.gui.PanelJuego;

/**
 *
 * @author JERSON
 */
public class Juego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame v = new JFrame();
        v.setSize(PanelJuego.ANCHO_JUEGO, PanelJuego.ALTURA_JUEGO);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setTitle("Mi Juego");
        
        v.add(new PanelJuego());
        v.setVisible(true);
    }
    
}
