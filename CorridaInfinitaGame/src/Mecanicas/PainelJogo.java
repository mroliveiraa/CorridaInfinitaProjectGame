//@Mateus Ribeiro
//classe responsável pelos elementos na tela
package Mecanicas;

import Personagem.Player;
import Obstaculos.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import corridainfinitagame.Inimigo;
import java.util.ArrayList;

public class PainelJogo extends JPanel implements ActionListener, KeyListener {

    private Player player;
    private Timer timer;
    private ArrayList<Inimigo> inimigos;
    private int velocidadeCenario = 5;

    private int backgroundX = 0;
    private ImageIcon gifFundo;

    // Lava e Espinhos
    private Lava lava;
    private Espinhos espinho;

    public PainelJogo() {
        setFocusable(true); //aciona eventos de teclado
        addKeyListener(this);

        player = new Player(100, 400); //posição do personagem
        timer = new Timer(16, this); //60 fps
        timer.start();

        gifFundo = new ImageIcon("src/res/BackgroundPixelado.gif");

        //inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(600, 400, velocidadeCenario)); //posiçao de spawn dos inimigos
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
        inimigos.add(new Inimigo(2400, 400, velocidadeCenario));

        // Lava e Espinhos para aparecer próximos do player
        int alturaChao = 450; // altura do chão
        int posPlayer = 100; // posição x do player
        int distanciaAntesDoPlayer = 1000; // distância antes do player

        lava = new Lava(posPlayer + distanciaAntesDoPlayer, alturaChao - 20, 40, 20, "/res/lava.png");
        espinho = new Espinhos(posPlayer + distanciaAntesDoPlayer + 800, alturaChao - 50, 100, 50, "/res/espinho.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);

        int alturaChao = 450;
        int alturaRestante = getHeight() - alturaChao;
        g.setColor(Color.BLACK); // chão
        g.fillRect(backgroundX, alturaChao, getWidth(), alturaRestante);
        g.fillRect(backgroundX + getWidth(), alturaChao, getWidth(), alturaRestante);

        //cor do chão

        player.imprimirPersonagem(g); //adiciona personagem na tela

        for (Inimigo inimigo : inimigos) { //adiciona inimigos
            if (inimigo.isVisivel()) {
                g.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), null);
            }
        }

        //desenhar Lava e Espinhos
        lava.desenhar(g);
        espinho.desenhar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.Update();

        //background se movendo
        backgroundX -= velocidadeCenario;
        if (backgroundX <= -getWidth()) {
            backgroundX = 0;
        }

        for (Inimigo inimigo : inimigos) {
            inimigo.moverComCenario(velocidadeCenario);
        }

        //mover Lava e Espinhos com o cenário
        lava.mover(velocidadeCenario);
        espinho.mover(velocidadeCenario);

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { //Pula com espaço e seta para cima
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            player.Pulo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //desenha gif animado de fundo
    }
}
