package Obstaculos;

import java.awt.*;
import javax.swing.ImageIcon;


public class EspinhoMapa2 {

    private static Image imagemCache; // imagem carregada 1 vez (economia de memória)

    private int x, y;
    private int largura, altura;

    public EspinhoMapa2(int x, int y, int largura, int altura, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;

        carregarImagemSoUmaVez(caminhoImagem); // adan - carrega a imagem só na primeira vez
    }

    // evita carregar a mesma imagem toda hora
    private void carregarImagemSoUmaVez(String caminhoImagem) {
        if (imagemCache != null) return; //se já carregou, não carrega de novo


        ImageIcon ic = new ImageIcon(getClass().getResource(caminhoImagem));
        imagemCache = ic.getImage().getScaledInstance(largura, altura, Image.SCALE_FAST);
    }
    
    //movimenta o espinho para a esquerda conforme a velocidade do cenário
    public void mover(int velocidade) {
        x -= velocidade;
    }
    
    //desenha o espinho na tela
    public void desenhar(Graphics g) {
        g.drawImage(imagemCache, x, y, null);
    }
        
    // getters simples (usados para colisão e posição)
    public int getX() { return x; }
    public int getY() { return y; }
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }
}
