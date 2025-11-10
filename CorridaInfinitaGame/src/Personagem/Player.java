
package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Player {
    
   //atributos    
    private int x,y; //posição
    private int velocidadeY; //velocidade do pulo
    private boolean estaPulando; //Estado
    private final int distanciaChao = 400; //altura do solo após pular
    
    
            
    
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
            velocidadeY += 1; //gravidade do pulo
            y += velocidadeY;
            
            //faz que o personagem caia no chão e "para de pular"
            if (y >= distanciaChao){
                y = distanciaChao;
                estaPulando = false;
                velocidadeY = 0;
                
                
            } 
            }
        }
            public void Pulo (){
                if (!estaPulando){
                    estaPulando = true;
                    velocidadeY = -20; //velocidade do pulo negativa para acrescentar no pulo
                   }   
                }
                public void imprimirPersonagem (Graphics g){ 
          g.setColor (Color.BLUE);
          g.fillRect(x, y, 50, 50);
          
        
    
    
    
    

}
}