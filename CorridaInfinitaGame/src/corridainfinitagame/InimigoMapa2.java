package corridainfinitagame;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


public class InimigoMapa2 {

    private static Image[] imagensCache; // imagens já carregadas

    private Image imagem;
    private int x, y;
    private int largura = 50;
    private int altura = 50;
    private boolean visivel = true;
    private int velocidade;

    public InimigoMapa2(int x, int y, int velocidadeCenario) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidadeCenario;
        this.visivel = true;

        carregarImagensSoUmaVez();

        // escolhe imagem leve da memória
        Random rand = new Random();
        imagem = imagensCache[rand.nextInt(imagensCache.length)];
    }

    private static void carregarImagensSoUmaVez() {
        if (imagensCache != null) return; 

        String[] arquivos = {
            "src/res/worm.png",
            "src/res/virus.png",
            "src/res/virus2.png",
            "src/res/CavaloTroia.png"
        };

        imagensCache = new Image[arquivos.length];

        for (int i = 0; i < arquivos.length; i++) {
            ImageIcon ref = new ImageIcon(arquivos[i]);
            imagensCache[i] = ref.getImage().getScaledInstance(80, 80, Image.SCALE_FAST);
        }
    }

    public void moverComCenario(int velocidadeCenario) {
        x -= velocidadeCenario;
        if (x + largura < 0) visivel = false;
    }

    public java.awt.Rectangle getBounds() {
        return new java.awt.Rectangle(x, y, largura, altura);
    }

    public Image getImagem() { return imagem; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isVisivel() { return visivel; }
}
