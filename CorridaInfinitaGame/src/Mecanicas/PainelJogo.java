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
    
    
    
    // --- VARIÁVEIS DE SCORE E DIFICULDADE (ADICIONADAS) ---
    private int score = 0; // O placar atual do jogo
    private final int VELOCIDADE_BASE = 50; // A velocidade mínima e inicial do jogo
    private final int FATOR_DIFICULDADE = 100; // O score a cada qual a velocidade aumenta 1
    // -----------------------------------------------------

    // --- VARIÁVEIS DE DISTÂNCIA DINÂMICA (NOVO) ---
    private final int INTERVALO_SPAWN_BASE = 150; // Distância inicial (em frames)
    private final int INTERVALO_SPAWN_MINIMO = 40; // Distância mínima (para não ficar impossível)
    private final int FATOR_REDUCAO_DISTANCIA = 20; // O score a cada qual o intervalo diminui em 1 frame
    // ----------------------------------------------

    private int velocidadeCenario = 20;

    private int backgroundX = 0;
    private ImageIcon gifFundo;
    private int contadorSpawnInimigo = 0;
    private int intervaloSpawn = INTERVALO_SPAWN_BASE; // Usa a nova constante como inicial


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
        espinhos.add(new Espinhos(3000, 430, 40, 40, "/res/espinho.png"));
        

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
        
        // --- EXIBIR O SCORE NA TELA ---
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Arial", Font.BOLD, 30)); 
        g.drawString("Score: " + (score / 10), 50, 50); 
        // ------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // --- APLICAÇÃO DO SCORE E DIFICULDADE ---
        score++; // Aumenta o score a cada frame.
        velocidadeCenario = calcularVelocidade(); // Atualiza a velocidade do jogo.
        intervaloSpawn = calcularIntervaloSpawn(); // ATUALIZA O INTERVALO COM BASE NO SCORE
        // -----------------------------------------------------

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
        // contador para spawn de novos inimigos
        contadorSpawnInimigo++;
        if (contadorSpawnInimigo >= intervaloSpawn) {
        contadorSpawnInimigo = 0;
        int posX = getWidth() + (int)(Math.random() * 300); // aparece fora da tela
        inimigos.add(new Inimigo(posX, 400, velocidadeCenario));
        }
        inimigos.removeIf(i -> i.getX() + i.getImagem().getWidth(null) < 0);//remove inimigos da tela
        
if (deveReiniciar){
    reiniciarJogo();
    deveReiniciar = false;
}


    }
private boolean deveReiniciar = false; //corrigir o erro de reinicialização do loop
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
    
    // --- MÉTODO DE CÁLCULO DE VELOCIDADE (JÁ EXISTENTE) ---
    private int calcularVelocidade() {
        int fatorAumento = score / FATOR_DIFICULDADE;
        int novaVelocidade = VELOCIDADE_BASE + fatorAumento;
        
        // Limita a velocidade máxima
        if (novaVelocidade > 20) {
            return 20; 
        }

        return novaVelocidade;
    }
    // -----------------------------------------------------

    // --- MÉTODO DE CÁLCULO DE INTERVALO (NOVO) ---
    /**
     * Calcula o intervalo de spawn de inimigos, diminuindo-o conforme o score.
     * @return O novo intervalo em frames.
     */
    private int calcularIntervaloSpawn() {
        // Calcula o quanto o intervalo deve ser reduzido (ex: 1 frame a menos a cada 20 pontos)
        int reducaoAtual = score / FATOR_REDUCAO_DISTANCIA;
        
        // Novo intervalo = Base - Redução
        int novoIntervalo = INTERVALO_SPAWN_BASE - reducaoAtual;

        // Garante que o intervalo nunca seja menor que o mínimo (40 frames)
        return Math.max(INTERVALO_SPAWN_MINIMO, novoIntervalo);
    }
    // ----------------------------------------------
    
    //autor @Mateus Ribeiro
    public void reiniciarJogo (){
        // --- RESETAR O SCORE E VELOCIDADE ---
        score = 0;
        velocidadeCenario = VELOCIDADE_BASE;
        intervaloSpawn = INTERVALO_SPAWN_BASE; // RESET DO INTERVALO (NOVO)
        // --------------------------------------------------
        
        //Reinicia o player
        player = new Player (100,400);
        //Reinicia o cenario
        backgroundX = 0;
        
        //Limpa e recria os inimigos
    inimigos.clear();
    inimigos.add(new Inimigo(1200, 400, velocidadeCenario));
    

    // Limpa e recria os espinhos
    espinhos.clear();
    espinhos.add(new Espinhos(6000, 430, 40, 40, "/res/espinho.png"));
    

    // Limpa e recria as lavas
    lavas.clear();
    lavas.add(new Lava(4500, 445, 70, 30, "/res/lava.png"));
    

        
     //Reinicia o timer
      timer.start (); 
        }
}


