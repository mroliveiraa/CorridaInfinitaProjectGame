package corridainfinitagame;
// @author Arthur Machado
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

    /*Classe que representa um inimigo no jogo de corrida infinita.
      Cada inimigo movimenta junto com o cenário (efeito de rolagem).*/

public class Inimigo {
    
    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean visivel = true;
    protected int velocidade;

   
    public Inimigo(int x, int y, int velocidadeCenario) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidadeCenario;
        this.visivel = true;
       carregarImagemAleatoria();
    }

    private void carregarImagemAleatoria() {
        String[] imagens = {
            "src/res/worm.png",
            "src/res/virus.png",
            "src/res/virus2.png",
            "src/res/CavaloTroia.png"
        };
     
        

       Random random = new Random();
       String caminhoEscolhido = imagens[random.nextInt(imagens.length)];
        
        // Carrega a imagem(80x80) para manter proporção
       ImageIcon referencia = new ImageIcon(caminhoEscolhido);
        imagem = referencia.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        this.altura = 50;
        this.largura = 50;
    }
    //método para detectar colisões com o jogador
    public java.awt.Rectangle getBounds() {
    return new java.awt.Rectangle(x, y, largura, altura);
}
    //Move o inimigo conforme o cenário (efeito de rolagem)
    public void moverComCenario(int velocidadeCenario) {
        x -= velocidadeCenario;

        if (x + largura < 0) {
            visivel = false; 
        }
    }

    public Image getImagem() { return imagem; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isVisivel() { return visivel; }
}
