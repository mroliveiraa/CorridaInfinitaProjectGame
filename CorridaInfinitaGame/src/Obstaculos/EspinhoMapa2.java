package Obstaculos;

import java.awt.*;
import javax.swing.ImageIcon;

// EspinhoMapa2 agora herda os atributos e métodos de Espinhos
public class EspinhoMapa2 extends Espinhos {

    private static Image imagemCache; // carrega só 1x

    public EspinhoMapa2(int x, int y, int largura, int altura, String caminhoImagem) {
        super(x, y, largura, altura, caminhoImagem); // usa o construtor do pai
        carregarImagemSoUmaVez(caminhoImagem);
    }

    private void carregarImagemSoUmaVez(String caminhoImagem) {
        if (imagemCache != null) return;

        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        imagemCache = ic.getImage().getScaledInstance(getLargura(), getAltura(), Image.SCALE_FAST);
    }

    @Override
    public void desenhar(Graphics g) {
        g.drawImage(imagemCache, getX(), getY(), null);
    }
}
