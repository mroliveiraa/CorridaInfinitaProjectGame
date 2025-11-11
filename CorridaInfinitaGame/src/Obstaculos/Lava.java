package Obstaculos;

import java.awt.*;
import javax.swing.*;

public class Lava {
    private int x, y, largura, altura;
    private Image imagem;

    public Lava(int x, int y, int largura, int altura, String caminhoImagem) {
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

    // Métodos necessários para aparecer só depois que os inimigos passam
    public int getX() { return x; }
    public int getLargura() { return largura; }
}
