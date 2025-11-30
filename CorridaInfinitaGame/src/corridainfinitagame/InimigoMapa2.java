package corridainfinitagame;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

// InimigoMapa2 agora herda de Inimigo
public class InimigoMapa2 extends Inimigo {

    private static Image[] imagensCache; // imagens carregadas uma vez só

    public InimigoMapa2(int x, int y, int velocidadeCenario) {
        super(x, y, velocidadeCenario); // chama construtor da classe-mãe

        carregarImagensSoUmaVez();

        // escolhe uma imagem aleatória para o inimigo
        Random rand = new Random();
        this.imagem = imagensCache[rand.nextInt(imagensCache.length)];

        // garante tamanho
        this.largura = 50;
        this.altura = 50;
    }

    private static void carregarImagensSoUmaVez() {
        if (imagensCache != null) return; // já carregado? então só usa

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
}
