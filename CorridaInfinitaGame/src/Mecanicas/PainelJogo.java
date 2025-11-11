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
    private ArrayList<ObstaculoBase> obstaculos;
    private int velocidadeCenario = 5;

    private int backgroundX = 0;
    private ImageIcon gifFundo;

    public PainelJogo() {
        setFocusable(true);
        addKeyListener(this);

        // Player
        player = new Player(100, 400);

        // Timer 60 FPS
        timer = new Timer(16, this);
        timer.start();

        // Fundo
        gifFundo = new ImageIcon("src/res/BackgroundPixelado.gif");

        // Inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(600, 400, velocidadeCenario));
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
        inimigos.add(new Inimigo(2400, 400, velocidadeCenario));

        // Obstáculos
        obstaculos = new ArrayList<>();
        obstaculos.add(new Lava(800, 450));
        obstaculos.add(new Espinho(1200, 450));
        obstaculos.add(new Alcapao(1600, 450));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fundo
        g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);

        // Chão
        int alturaChao = 450;
        int alturaRestante = getHeight() - alturaChao;
        g.setColor(Color.BLACK);
        g.fillRect(backgroundX, alturaChao, getWidth(), alturaRestante);
        g.fillRect(backgroundX + getWidth(), alturaChao, getWidth(), alturaRestante);

        // Player
        player.imprimirPersonagem(g);

        // Inimigos
        for (Inimigo inimigo : inimigos) {
            if (inimigo.isVisivel()) {
                g.drawImage(inimigo.getImagem(), inimigo.getX(), inimigo.getY(), null);
            }
        }

        // Obstáculos
        for (ObstaculoBase o : obstaculos) {
            g.setColor(o.getCor());
            g.fillRect(o.getX(), o.getY(), o.getLargura(), o.getAltura());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza player
        player.Update();

        // Move cenário
        backgroundX -= velocidadeCenario;
        if (backgroundX <= -getWidth()) backgroundX = 0;

        // Move inimigos
        for (Inimigo inimigo : inimigos) {
            inimigo.moverComCenario(velocidadeCenario);
        }

        // Move obstáculos
        for (ObstaculoBase o : obstaculos) {
            o.setX(o.getX() - velocidadeCenario);
        }

        // Checa colisão com obstáculos
        for (ObstaculoBase o : obstaculos) {
            if (player.getBounds().intersects(o.getBounds()) && !player.isInvulneravel()) {
                player.reduzirVida(20);
                player.setInvulneravel(true);
                new Timer(2000, ev -> player.setInvulneravel(false)).start();
            }
        }

        // Aqui você pode adicionar aumento de velocidade progressivo
        // ex: velocidadeCenario += 1 a cada X segundos ou distância

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            player.Pulo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
