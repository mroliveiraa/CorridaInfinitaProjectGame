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

        //carrega a imagem da lava no tamanho certo
        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        this.imagem = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }
    
    //movimento da lava seguindo a velocidade do cenário
    public void mover(int velocidade) {
        x -= velocidade;
    }
    
    //desenha a lava na tela
    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, null);
    }

    //getters usados para colisão e posição da lava
    public int getX() { return x; }
    public int getY() { return y; }           
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }  
}
