package Personagem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

    private int x, y;
    private int velocidadeY;
    private boolean estaPulando;
    private final int distanciaChao = 400;

    // NOVO: Vida e invulnerabilidade
    private int vida = 100;
    private boolean invulneravel = false;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.velocidadeY = 0;
        this.estaPulando = false;
    }

    public void Update() {
        if (estaPulando) {
            velocidadeY += 1; // gravidade
            y += velocidadeY;

            if (y >= distanciaChao) {
                y = distanciaChao;
                estaPulando = false;
                velocidadeY = 0;
            }
        }
    }

    public void Pulo() {
        if (!estaPulando) {
            estaPulando = true;
            velocidadeY = -20;
        }
    }

    public void imprimirPersonagem(Graphics g) {
        if (invulneravel) {
            g.setColor(Color.CYAN); // piscando quando invulnerável
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, 50, 50);
    }

    // ---------------------------
    // NOVOS MÉTODOS PARA OBSTÁCULOS
    // ---------------------------

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }

    public void reduzirVida(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println("Vida do player: " + vida);
    }

    public void setInvulneravel(boolean inv) {
        invulneravel = inv;
    }

    public boolean isInvulneravel() {
        return invulneravel;
    }

    public int getVida() {
        return vida;
    }
    
}
