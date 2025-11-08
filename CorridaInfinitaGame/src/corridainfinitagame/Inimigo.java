package corridainfinitagame;
// @author Arthur Machado

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Inimigo {

    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean visivel = true;
    private int velocidade;

    public Inimigo(int x, int y, int velocidadeCenario) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidadeCenario;
        this.visivel = true;
        carregarImagemAleatoria();
    }

    private void carregarImagemAleatoria() {
        String[] imagens = {
            "res/worm.png",
            "res/virus.png",
            "res/virus2.png",
            "res/CavaloTroia.png"
        };

        Random random = new Random();
        String caminhoEscolhido = imagens[random.nextInt(imagens.length)];

        ImageIcon referencia = new ImageIcon(caminhoEscolhido);
        imagem = referencia.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        this.altura = 80;
        this.largura = 80;
    }

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
