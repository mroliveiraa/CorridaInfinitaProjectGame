package corridainfinitagame;
//@author Arthur Machado

import java.awt.Image;

 
public class Inimigo {
    
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private int velocidade;
    private boolean isVisivel;
    
    private static final int LARGURA = 938;
    private static int VELOCIDADE = 2;
    
    public Inimigo(int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
        
        
    }
    
    
    
}
