//@Mateus Ribeiro
//classe responsável pelos elementos na tela
package Mecanicas;

import Personagem.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import corridainfinitagame.Inimigo;
import Obstaculos.Espinhos;
import Obstaculos.Lava;
import java.util.ArrayList;

public class PainelJogo extends JPanel implements ActionListener, KeyListener {

    private Player player;
    private Timer timer;
    private ArrayList<Inimigo> inimigos;

    private ArrayList<Espinhos> espinhos;
    private ArrayList<Lava> lavas;

    private int velocidadeCenario = 5;

    private int backgroundX = 0;
    private ImageIcon gifFundo;

    public PainelJogo() {
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 400);

        timer = new Timer(16, this);
        timer.start();

        gifFundo = new ImageIcon("src/res/BackgroundPixelado.gif");

        // inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(600, 400, velocidadeCenario));
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
        inimigos.add(new Inimigo(2400, 400, velocidadeCenario));

        // espinhos
        espinhos = new ArrayList<>();
        espinhos.add(new Espinhos(900, 430, 40, 40, "/res/espinho.png"));
        espinhos.add(new Espinhos(1800, 430, 40, 40, "/res/espinho.png"));

        // lava
        lavas = new ArrayList<>();
        lavas.add(new Lava(1500, 445, 70, 30, "/res/lava.png"));
        lavas.add(new Lava(2600, 445, 70, 30, "/res/lava.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fundo
        g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);

        int alturaChao = 450;
        int alturaRestante = getHeight() - alturaChao;

        // chão
        g.setColor(Color.BLACK);
        g.fillRect(backgroundX, alturaChao, getWidth(), alturaRestante);
        g.fillRect(backgroundX + getWidth(), alturaChao, getWidth(), alturaRestante);

        // personagem
        player.imprimirPersonagem(g);

        // inimigos
        for (Inimigo i : inimigos) {
            if (i.isVisivel()) {
                g.drawImage(i.getImagem(), i.getX(), i.getY(), null);
            }
        }

        // espinhos
        for (Espinhos e : espinhos) {
            e.desenhar(g);
        }

        // lava
        for (Lava l : lavas) {
            l.desenhar(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // atualizar player
        player.Update();

        // mover cenário
        backgroundX -= velocidadeCenario;
        if (backgroundX <= -getWidth()) {
            backgroundX = 0;
        }

        // mover inimigos
        for (Inimigo i : inimigos) {
            i.moverComCenario(velocidadeCenario);
        }

        // mover espinhos
        for (Espinhos esp : espinhos) {
            esp.mover(velocidadeCenario);
        }

        // mover lava
        for (Lava lv : lavas) {
            lv.mover(velocidadeCenario);
        }

        verificarColisoes();

        repaint();
    }

    private void verificarColisoes() {

        // colisão com inimigos
        for (Inimigo i : inimigos) {
            if (i.isVisivel() && player.getBounds().intersects(i.getBounds())) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu! (inimigo) 💀");
                System.exit(0);
            }
        }

        // colisão com espinhos
        for (Espinhos e : espinhos) {
            Rectangle r = new Rectangle(e.getX(), 430, e.getLargura(), 40);
            if (player.getBounds().intersects(r)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu! (espinho) 💀");
                System.exit(0);
            }
        }

        // colisão com lava
        for (Lava l : lavas) {
            Rectangle r = new Rectangle(l.getX(), 445, l.getLargura(), 30);
            if (player.getBounds().intersects(r)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "🔥 Você caiu na lava! 🔥");
                System.exit(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            player.Pulo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
