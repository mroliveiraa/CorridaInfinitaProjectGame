
package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle; // Importei Rectangle para garantir que não haja erros
import java.net.URL;

public class Player {
    
   //atributos    
    protected int x, y; //posição
    protected int velocidadeY;//velocidade do pulo
    protected boolean estaPulando;//Estado
    protected final int distanciaChao = 400;//altura do solo após pular

     //Animação
    private Image[] frames; //Vetores dos sprites
    private int frameAtual = 0; // Frame exibido
    private int contadorFrames = 0; // comtrola vel da troca de frames
   
    

//construtor
    public Player (int startX, int startY, String skinSelecionada){
    this.x  = startX;
    this.y = startY;
    this.velocidadeY = 0;  //velocidade do pulo
    this.estaPulando = false;
     
    carregarSprintes(skinSelecionada);
    }
    
    //Carregar os frames
  private void carregarSprintes(String skinSelecionada) {
    frames = new Image[3];

    for (int i = 0; i < 3; i++) {
        String caminho =  skinSelecionada + "/FrameMov" + (i + 1) + ".png";
        URL url = getClass().getResource(caminho);

        if (url == null) {
            System.err.println("Erro: imagem não encontrada → " + caminho);
            frames[i] = null;
        } else {
            frames[i] = new ImageIcon(url).getImage();
        }
    }
}


    
    //Lógica da movimentação
    public void Update() {
       
        //pulo
        if (estaPulando) {
            velocidadeY += 1.3; //gravidade do pulo
            y += velocidadeY;
            
            //faz que o personagem caia no chão e "para de pular"
            if (y >= distanciaChao){
                y = distanciaChao;
                estaPulando = false;
                velocidadeY = 0;
                
                
                } 
            }
        
    
        //Animação
        animar();
    }
    
    //Automatização da troca de frame 
    private void animar() {
        contadorFrames++;
        
        if (contadorFrames >= 10) {
            frameAtual++;
            if (frameAtual >= frames.length) {
                frameAtual = 0;//Volta ao primeiro'LOOP'
            }
            contadorFrames = 0;
        }
    }
    
    public java.awt.Rectangle getBounds() {
      return new java.awt.Rectangle(x, y, 50, 50);
}

    
            public void Pulo (){
                if (!estaPulando){
                    estaPulando = true;
                    velocidadeY = -30; 
                   }   
                }   
            
            //desenha o sprite atual
                public void imprimirPersonagem (Graphics g){ 
          g.drawImage(frames[frameAtual], x, y, 70, 70, null);
       }
    }

