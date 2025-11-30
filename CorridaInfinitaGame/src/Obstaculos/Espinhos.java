package Obstaculos;

import java.awt.*;
import javax.swing.*;

public class Espinhos {
    private int x, y, largura, altura;
    private Image imagem;

    //construtor que cria o espinho com posição, tamanho e imagem
    public Espinhos(int x, int y, int largura, int altura, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;

        //carrega a imagem do espinho com o tamanho certo
        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        this.imagem = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }

    
    //movimento da espinho seguindo a velocidade do cenário (esquerda)
    public void mover(int velocidade) {
        x -= velocidade;
    }
    
    //desenha o espinho na tela
    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, null);
    }
    
    //desenha o espinho na tela
    public int getX() { return x; }
    public int getY() { return y; }           
    public int getLargura() { return largura; }
    public int getAltura() { return altura; } 
}
