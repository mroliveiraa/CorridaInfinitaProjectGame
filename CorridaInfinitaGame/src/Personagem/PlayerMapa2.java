package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerMapa2 {

    // posição do player
    private int x, y;

    // física do pulo
    private int velocidadeY;  // <- igual do MAPA 1
    private boolean estaPulando;

    // tamanho do player
    private final int largura = 50;
    private final int altura = 50;

    // chão fixo igual ao MAPA 1
    private final int distanciaChao = 560; //garante que o chão seja fixo

    public PlayerMapa2(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        this.velocidadeY = 0;
        this.estaPulando = false;
    }

    // Update igual ao MAPA 1
    public void Update() {

        if (estaPulando) {

            velocidadeY += 1.5; // gravidade igual MAPA 1
            y += velocidadeY;

            // impedir cair abaixo do chão
            if (y >= distanciaChao) {
                y = distanciaChao;
                estaPulando = false;
                velocidadeY = 0;
            }
        }
    }

    // pulo igual MAPA 1
    public void Pulo() {
        if (!estaPulando) {
            estaPulando = true;
            velocidadeY = -25; // força igual do MAPA 1
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void imprimirPersonagem(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, largura, altura);
    }

    // getters
    public int getX() { return x; }
    public int getY() { return y; }
}
