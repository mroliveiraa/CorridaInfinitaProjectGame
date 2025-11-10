//@Mateus Ribeiro
//classe responsável pelos elementos na tela
package Mecanicas;

import Personagem.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import corridainfinitagame.Inimigo;
import java.net.URL;
import java.util.ArrayList;


public class PainelJogo extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private Timer timer;
    private ArrayList <Inimigo> inimigos;
    private int velocidadeCenario = 5;
    
    private int backgroundX = 0;
    private ImageIcon gifFundo;
   
    
    
    public PainelJogo (){
        setFocusable (true); //aciona eventos de teclado
        addKeyListener (this);
        player = new Player (100,400); //posição do personagem
        timer = new Timer(16,this); //60 fps
        timer.start();
        gifFundo = new ImageIcon ("src/res/BackgroundPixelado.gif");
        
        //inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(600, 400, velocidadeCenario));//posiçao de spawn dos inimigos
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
        inimigos.add(new Inimigo(2400, 400, velocidadeCenario));

               
    }
@Override
protected void paintComponent (Graphics g){
    super.paintComponent(g);
    g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);
    int alturaChao = 450;
    int alturaRestante = getHeight()- alturaChao;
    g.setColor(Color.BLACK); // chão
       g.fillRect(backgroundX, alturaChao, getWidth(), alturaRestante);
       g.fillRect(backgroundX + getWidth(), alturaChao, getWidth(), alturaRestante);
       
  
   //cor do chão
        

        
        player.imprimirPersonagem(g);//adicione personagem na tela
    
        for (Inimigo inimigo : inimigos) {//adiciona inimigos
        if (inimigo.isVisivel()) {
        g.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), null);
       
    
    }

}
}
    @Override
    public void actionPerformed(ActionEvent e) {
        player.Update();
           //background se movendo
       backgroundX -= velocidadeCenario;
        if (backgroundX <= -getWidth()){
            backgroundX = 0;
            }
        for(Inimigo inimigo : inimigos){
        inimigo.moverComCenario(velocidadeCenario);
        
     
        }
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
       //desenha gif animado de fundo
     
    }
    
    
}



