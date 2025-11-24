package Obstaculos;

import java.awt.*;
import javax.swing.ImageIcon;


public class EspinhoMapa2 {

    private static Image imagemCache; // imagem carregada 1 vez

    private int x, y;
    private int largura, altura;

    public EspinhoMapa2(int x, int y, int largura, int altura, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;

        carregarImagemSoUmaVez(caminhoImagem);
    }

    // Carrega apenas uma vez
    private void carregarImagemSoUmaVez(String caminhoImagem) {
        if (imagemCache != null) return;

        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        imagemCache = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_FAST);
    }

    public void mover(int velocidade) {
        x -= velocidade;
    }

    public void desenhar(Graphics g) {
        g.drawImage(imagemCache, x, y, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }
}
