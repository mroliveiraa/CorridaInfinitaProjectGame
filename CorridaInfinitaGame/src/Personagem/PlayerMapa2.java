package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerMapa2 {

    // posição do player
    private int x, y;

    // física do pulo
    private double velocidadeY;
    private boolean estaPulando;

    // tamanho do personagem
    private final int largura = 50;
    private final int altura = 50;

    // chão real do MAPA 2 
    private int chaoRealY;

    public PlayerMapa2(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        this.velocidadeY = 0;
        this.estaPulando = false;

        // define o chão real com base no valor 
        this.chaoRealY = startY;
    }

    // Atualiza pulo e gravidade
    public void Update() {

        if (estaPulando) {

            // gravidade
            velocidadeY += 1.5;
            y += velocidadeY;

            // impede o player de atravessar o topo (teto bem alto)
            if (y < 20) {
                y = 20;
                velocidadeY = 0;
            }

            // impede atravessar o chão
            if (y >= chaoRealY) {
                y = chaoRealY;
                velocidadeY = 0;
                estaPulando = false;
            }
        }
    }

    // comando de pulo
    public void Pulo() {
        if (!estaPulando) {
            estaPulando = true;
            velocidadeY = -25; // força do pulo
        }
    }

    // colisão
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    // desenha player
    public void imprimirPersonagem(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, largura, altura);
    }

    // getters
    public int getX() { return x; }
    public int getY() { return y; }
}
