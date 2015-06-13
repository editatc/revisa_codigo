/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.iberotec.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pe.edu.iberotec.objetos.Misil;
import pe.edu.iberotec.objetos.Nave;

/**
 *
 * @author JERSON
 */
public class PanelJuego extends JPanel implements KeyListener, Runnable {

    public static final int ANCHO_JUEGO = 1000;
    public static final int ALTURA_JUEGO = 600;

    private Nave nave;
    private Nave enemigo;
    private Image imagenJuego;
    private Graphics pincelImagenJuego;
    private ImageIcon fondo;
    private Thread animador;
    private ArrayList<Misil> listaMisiles = new ArrayList<Misil>();
    private ArrayList<Misil> listaMisilesEnemigo = new ArrayList<Misil>();
    private int vidasNave=5;
    private int vidasEnemigo=5;
    
    public PanelJuego() {
        //1: Preparar el Panel
        setSize(ANCHO_JUEGO, ALTURA_JUEGO);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();

        //2: Crear los Sprites
        ImageIcon foto = new ImageIcon(getClass().getResource("/imagenes/nave.jpg")
        );
        nave =new Nave(700, 300, 0, 0, foto.getIconWidth(),
                foto.getIconHeight(), foto);
        

        foto = new ImageIcon(
                getClass().getResource("/imagenes/naves.jpg")
        );
        enemigo = new Nave(0, 300, 0, 0, foto.getIconWidth(),
                foto.getIconHeight(), foto);

        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")
        );

        addKeyListener(this);

    }

   
    public void addNotify() {
        super.addNotify();
        iniciarJuego();
    }

    public void iniciarJuego() {
        if (animador == null) {
            animador = new Thread(this);
        }
        //Ejecuta el método run
        animador.start();
    }

    private void actualizarPosiciones() {
        nave.mover(getWidth(), getHeight());
        enemigo.mover(getWidth(), getHeight());
        for (Misil m : listaMisiles) {
            m.mover(getWidth(), getHeight());
            if (m.huboImpacto(enemigo)) {
                if(!m.isEliminado()){
                    vidasEnemigo--;
                }
                m.setEliminado(true);
            }
        }
        
        for (Misil m : listaMisilesEnemigo) {
            m.mover(getWidth(), getHeight());
            if (m.huboImpacto(nave)) {
                if(!m.isEliminado()){
                    vidasNave--;
                }
                m.setEliminado(true);
                
            }
        }
        
    }

    private boolean terminoJuego(){
        boolean termino = false;
        if(vidasNave<=3){
            JOptionPane.showMessageDialog(this, "Ganó Jerson.");
            termino=true;
        } 
        
        if(vidasEnemigo<=3){
            JOptionPane.showMessageDialog(this, "Ganó Marinita.");
            termino=true;
        }
        return termino;
    }
    
    private void dibujarObjetos() {
        if (imagenJuego == null) {
            imagenJuego = createImage(ANCHO_JUEGO, ALTURA_JUEGO);
            pincelImagenJuego = imagenJuego.getGraphics();
        }

        pincelImagenJuego.drawImage(fondo.getImage(),0,0,null);

        pincelImagenJuego.drawImage(
                nave.getFoto().getImage(),
                nave.getX(),
                nave.getY(),
                nave.getAncho(),
                nave.getAltura(),
                null
        );

        pincelImagenJuego.drawImage(
                enemigo.getFoto().getImage(),
                enemigo.getX(),
                enemigo.getY(),
                enemigo.getAncho(),
                enemigo.getAltura(),
                null
        );

        for (Misil m : listaMisiles) {
            if (!m.isEliminado()) {
                pincelImagenJuego.drawImage(
                        m.getFoto().getImage(),
                        m.getX(),
                        m.getY(),
                        null
                );
            }
        }

        for (Misil m : listaMisilesEnemigo) {
            if (!m.isEliminado()) {
                pincelImagenJuego.drawImage(
                        m.getFoto().getImage(),
                        m.getX(),
                        m.getY(),
                        null
                );
            }
        }
    }

    private void dibujarImagenJuego() {
        Graphics pincelPanel = getGraphics();
        pincelPanel.drawImage(imagenJuego,0,0,ANCHO_JUEGO,ALTURA_JUEGO,null);
    }

    private void emitirSonido(String sonido){
        AudioInputStream stream = null;
        try {
            //1: Sonido a reproducir
            stream = AudioSystem.getAudioInputStream(getClass().getResource(sonido)
            );
            //2: Formato del sonido
            AudioFormat formato = stream.getFormat();
            
            DataLine.Info datos = new DataLine.Info(Clip.class , formato);
            
            Clip reproductor = (Clip)AudioSystem.getLine(datos);
            
            reproductor.open(stream);
            reproductor.start();
            stream.close();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while (!terminoJuego()) {
            //1: actualizar posiciones o estados
            actualizarPosiciones();

            //2: dibujar objetos en imagen general del juego
            dibujarObjetos();

            //3: dibujar la imagen general en el panel
            dibujarImagenJuego();

            //4: pausa
            try {
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Enemigo
        if (e.getKeyCode() == KeyEvent.VK_S) {
            enemigo.setVy(5);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            enemigo.setVy(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            enemigo.setVy(0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            //Disparar
            ImageIcon fotoMisil = new ImageIcon(
                    getClass().getResource("/imagenes/bala.jpg"));

            Misil m = new Misil(
                    enemigo.getX() + enemigo.getAncho() /2 -fotoMisil.getIconWidth() / 2,
                    enemigo.getY() + enemigo.getAltura()/2,
                    0,
                    5,
                    fotoMisil.getIconWidth(),
                    fotoMisil.getIconHeight(),
                    fotoMisil
            );

            listaMisilesEnemigo.add(m);
            
            emitirSonido("/sonidos/explosion.wav");
        }

        
        //Nave
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            nave.setVy(5);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            nave.setVy(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            nave.setVy(0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Disparar
            ImageIcon fotoMisil = new ImageIcon(
                    getClass().getResource("/imagenes/balas.jpg"));

            Misil m = new Misil(
                    nave.getX() + nave.getAncho() / 2 - fotoMisil.getIconWidth() / 2,
                    nave.getY() + fotoMisil.getIconHeight()/2,
                    0,
                    -5,
                    fotoMisil.getIconWidth(),
                    fotoMisil.getIconHeight(),
                    fotoMisil
            );

            listaMisiles.add(m);
            
            emitirSonido("/sonidos/explosion.wav");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
