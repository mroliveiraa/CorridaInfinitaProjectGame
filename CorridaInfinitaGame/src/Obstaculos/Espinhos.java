package Obstaculos;

import java.awt.*;
import javax.swing.*;

public class Espinhos {
    private int x, y, largura, altura;
    private Image imagem;

    public Espinhos(int x, int y, int largura, int altura, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;

        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        this.imagem = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }

    public void mover(int velocidade) {
        x -= velocidade;
    }

    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }            // <-- ADICIONADO
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }  // <-- ADICIONADO
}
