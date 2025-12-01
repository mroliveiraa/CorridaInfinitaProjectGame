
package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle; // Importei Rectangle para garantir que não haja erros

public class Player {
    
   //atributos    
    protected int x, y; //posição
    protected int velocidadeY;//velocidade do pulo
    protected boolean estaPulando;//Estado
    protected final int distanciaChao = 400;//altura do solo após pular

            
    
    //construtor
    public Player (int startX, int startY){
    this.x  = startX;
    this.y = startY;
    this.velocidadeY = 0;  //velocidade do pulo
    this.estaPulando = false;
     
    }
    
    
    //inicia na posição
    public void Update() {
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
        }
                
            public java.awt.Rectangle getBounds() {
    return new java.awt.Rectangle(x, y, 50, 50);
}

    
            public void Pulo (){
                if (!estaPulando){
                    estaPulando = true;
                    velocidadeY = -23; 
                   }   
                }   
                public void imprimirPersonagem (Graphics g){ 
          g.setColor (Color.BLUE);
          g.fillRect(x, y, 50, 50);
          
        
    
    
    
    

}
}
