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

        //carrega a imagem do espinho com o tamanho certo
        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        this.imagem = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    }

    public Espinhos(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // adan - movimento para a esquerda conforme a velocidade do cenário
    public void mover(int velocidade) {
        x -= velocidade;
    }
    
    // adan - desenha o espinho na tela
    public void desenhar(Graphics g) {
        g.drawImage(imagem, x, y, null);
    }
    
    // adan - desenha o espinho na tela
    public int getX() { return x; }
    public int getY() { return y; }           
    public int getLargura() { return largura; }
    public int getAltura() { return altura; } 
}
