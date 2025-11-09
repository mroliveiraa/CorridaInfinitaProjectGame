//@Mateus Ribeiro
//classe responsável pelo personagem na tela
package Mecanicas;

import Personagem.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PainelJogo extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private Timer timer;
    
    public PainelJogo (){
        setFocusable (true); //aciona eventos de teclado
        addKeyListener (this);
        player = new Player (100,400); //posição do personagem
        timer = new Timer(16,this); //60 fps
        timer.start();
        
    }
protected void paintComponent (Graphics g){
    super.paintComponent(g);
    player.imprimirPersonagem(g);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        player.Update();
        repaint ();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { //Pula com espaço e seta para cima
        if (e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
            player.Pulo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    
}
