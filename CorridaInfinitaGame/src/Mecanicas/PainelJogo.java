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

    private ArrayList<Espinhos> espinhos; // <- lista de espinhos
    private ArrayList<Lava> lavas;
    
    // --- VARIÁVEIS DE SCORE E DIFICULDADE (ADICIONADAS) ---
    private int score = 0; 
    private final int VELOCIDADE_BASE = 50;
    private final int FATOR_DIFICULDADE = 100;
    // -----------------------------------------------------

    // --- VARIÁVEIS DE DISTÂNCIA DINÂMICA (NOVO) ---
    private final int INTERVALO_SPAWN_BASE = 150;
    private final int INTERVALO_SPAWN_MINIMO = 40;
    private final int FATOR_REDUCAO_DISTANCIA = 20;
    // ----------------------------------------------

    private int velocidadeCenario = 20;

    private int backgroundX = 0;
    private ImageIcon gifFundo;
    private int contadorSpawnInimigo = 0;
    private int intervaloSpawn = INTERVALO_SPAWN_BASE;

    //CONTADOR ESPINHOS
    private int contadorSpawnEspinho = 0; 
    private int intervaloEspinhos = 200; 

    private boolean deveReiniciar = false;

    public PainelJogo() {
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 400);

        timer = new Timer(16, this);
        
        timer.start();

        gifFundo = new ImageIcon("src/res/BackgroundPixelado.gif");

        // inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
        
        // espinhos
        espinhos = new ArrayList<>();
        espinhos.add(new Espinhos(3000, 400, 70, 70, "/res/espinho.png"));
        
        // lava
        lavas = new ArrayList<>();
        lavas.add(new Lava(7800, 445, 70, 30, "/res/lava.png"));
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
        
        // score
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString("Score: " + (score / 10), 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // dificuldade
        score++;
        velocidadeCenario = calcularVelocidade();
        intervaloSpawn = calcularIntervaloSpawn();

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

        // criar novos inimigos
        contadorSpawnInimigo++;
        if (contadorSpawnInimigo >= intervaloSpawn) {
            contadorSpawnInimigo = 0;
            int posX = getWidth() + (int)(Math.random() * 300);
            inimigos.add(new Inimigo(posX, 400, velocidadeCenario));
        }

        //CRIAR NOVOS ESPINHOS
        contadorSpawnEspinho++;
        if (contadorSpawnEspinho >= intervaloEspinhos) {
            contadorSpawnEspinho = 0;
            int posX = getWidth() + (int)(Math.random() * 400);
            espinhos.add(new Espinhos(posX, 400, 70, 70, "/res/espinho.png"));
        }

        //remove espinhos que saíram da tela
        espinhos.removeIf(sp -> sp.getX() + sp.getLargura() < 0);

        verificarColisoes();
        repaint();

        if (deveReiniciar){
            reiniciarJogo();
            deveReiniciar = false;
        }
    }

    private void verificarColisoes() {

        // colisão com inimigos
        for (Inimigo i : inimigos) {
            if (i.isVisivel() && player.getBounds().intersects(i.getBounds())) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu!💀");
                deveReiniciar = true;
            }
        }

        // colisão com espinhos
        for (Espinhos e : espinhos) {
            Rectangle r = new Rectangle(e.getX(), 430, e.getLargura(), 40);
            if (player.getBounds().intersects(r)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu!💀");
                deveReiniciar = true;
            }
        }

        // colisão com lava
        for (Lava l : lavas) {
            Rectangle r = new Rectangle(l.getX(), 445, l.getLargura(), 30);
            if (player.getBounds().intersects(r)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "🔥 Você caiu na lava! 🔥");
                deveReiniciar = true;
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            player.Pulo();
        }
    }

    private int calcularVelocidade() {
        int fatorAumento = score / FATOR_DIFICULDADE;
        int novaVelocidade = VELOCIDADE_BASE + fatorAumento;
        if (novaVelocidade > 20) {
            return 20; 
        }
        return novaVelocidade;
    }

    private int calcularIntervaloSpawn() {
        int reducaoAtual = score / FATOR_REDUCAO_DISTANCIA;
        int novoIntervalo = INTERVALO_SPAWN_BASE - reducaoAtual;
        return Math.max(INTERVALO_SPAWN_MINIMO, novoIntervalo);
    }
    
    //autor @Mateus Ribeiro
    public void reiniciarJogo (){
        score = 0;
        velocidadeCenario = VELOCIDADE_BASE;
        intervaloSpawn = INTERVALO_SPAWN_BASE;

        player = new Player (100,400);
        backgroundX = 0;
        
        inimigos.clear();
        inimigos.add(new Inimigo(1200, 400, velocidadeCenario));

        espinhos.clear(); 
        espinhos.add(new Espinhos(3000, 400, 70, 70, "/res/espinho.png"));

        lavas.clear();
        lavas.add(new Lava(4500, 445, 70, 30, "/res/lava.png"));

        timer.start (); 
    }

    // método para parar o jogo quando fechar o MAPA1
    public void pararJogo() {
        if (timer != null) {
            timer.stop();  //impede MAPA1 de rodar por trás
        }
    }
}
