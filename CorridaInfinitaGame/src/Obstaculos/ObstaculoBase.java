package Obstaculos;

import java.awt.*;

public abstract class ObstaculoBase {
    protected int x, y, largura, altura;
    protected Color cor;

    public ObstaculoBase(int x, int y, int largura, int altura, Color cor) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
    }

    public void mover(int velocidade) {
        x -= velocidade;
    }

    public void desenhar(Graphics g) {
        g.setColor(cor);
        g.fillRect(x, y, largura, altura);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean estaVisivel() {
        return x + largura > 0;
    }
}
