// @Adan Martin
// Classe responsável pelos elementos na tela

package Mecanicas;

import Personagem.PlayerMapa2; //player otimizado para o MAPA 2
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import corridainfinitagame.InimigoMapa2; //inimigo otimizado MAPA 2
import Obstaculos.EspinhoMapa2;         //espinho otimizado MAPA 2
import java.util.ArrayList;

public class PainelJogoMapa2 extends JPanel implements ActionListener, KeyListener {

    private PlayerMapa2 player; //Player exclusivo do MAPA 2
    private Timer timer;

    // listas de inimigos e espinhos
    private ArrayList<InimigoMapa2> inimigos;
    private ArrayList<EspinhoMapa2> espinhos;

    // --- VARIÁVEIS DE SCORE E DIFICULDADE ---
    private int score = 0;
    private final int VELOCIDADE_BASE = 50;
    private final int FATOR_DIFICULDADE = 100;

    // --- VARIÁVEIS DE DISTÂNCIA DINÂMICA ---
    private final int INTERVALO_SPAWN_BASE = 150;
    private final int INTERVALO_SPAWN_MINIMO = 40;
    private final int FATOR_REDUCAO_DISTANCIA = 20;

    private int velocidadeCenario = 20;

    private ImageIcon fundoMapa2;

    // altura real da rua do mapa 2
    private final int CHAO_REAL_Y = 600;

    private int contadorSpawnInimigo = 0;
    private int intervaloSpawn = INTERVALO_SPAWN_BASE;

    private int contadorSpawnEspinho = 0;
    private int intervaloEspinhos = 200;

    private boolean deveReiniciar = false;

    public PainelJogoMapa2() {

        setPreferredSize(new Dimension(1200, 676)); //tamanho real do cenário
        setDoubleBuffered(true); //ajuda no FPS
        setFocusable(true);
        addKeyListener(this);

        // cria jogador alinhado ao chão da rua
        player = new PlayerMapa2(100, CHAO_REAL_Y - 50);

        // timer mais leve (40 FPS)
        timer = new Timer(25, this);
        timer.start();

        // carrega fundo
        fundoMapa2 = new ImageIcon("src/res/fundo.png");

        // cria lista de inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new InimigoMapa2(1200, CHAO_REAL_Y - 50, velocidadeCenario));

        // cria lista de espinhos
        espinhos = new ArrayList<>();
        espinhos.add(new EspinhoMapa2(3000, CHAO_REAL_Y - 40, 70, 70, "/res/espinho.png"));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fundo fixo cobrindo 100% da tela
        g.drawImage(fundoMapa2.getImage(), 0, 0, 1200, 676, this);

        // desenhar jogador
        player.imprimirPersonagem(g);

        // desenhar inimigos
        for (InimigoMapa2 i : inimigos) {
            if (i.isVisivel()) {
                g.drawImage(i.getImagem(), i.getX(), i.getY(), null);
            }
        }

        // desenhar espinhos
        for (EspinhoMapa2 e : espinhos) {
            e.desenhar(g);
        }

        // desenhar score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + (score / 10), 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        score++;
        velocidadeCenario = calcularVelocidade();
        intervaloSpawn = calcularIntervaloSpawn();

        // atualiza player
        player.Update();

        // mover inimigos
        for (InimigoMapa2 i : inimigos) {
            i.moverComCenario(velocidadeCenario);
        }

        // remover inimigos fora da tela
        inimigos.removeIf(i -> i.getX() < -200);

        // mover espinhos
        for (EspinhoMapa2 esp : espinhos) {
            esp.mover(velocidadeCenario);
        }

        // remover espinhos fora da tela
        espinhos.removeIf(sp -> sp.getX() + sp.getLargura() < 0);

        //SPAWN AUTOMÁTICO DE INIMIGOS (MAPA 2)
        contadorSpawnInimigo++;
        if (contadorSpawnInimigo >= intervaloSpawn) {
            contadorSpawnInimigo = 0;

            inimigos.add(new InimigoMapa2(
                    getWidth() + (int)(Math.random() * 300),
                    CHAO_REAL_Y - 50,
                    velocidadeCenario
            ));
        }

        //SPAWN AUTOMÁTICO DE ESPINHOS (MAPA 2)
        contadorSpawnEspinho++;
        if (contadorSpawnEspinho >= intervaloEspinhos) {
            contadorSpawnEspinho = 0;

            espinhos.add(new EspinhoMapa2(
                    getWidth() + (int)(Math.random() * 400),
                    CHAO_REAL_Y - 40,
                    70, 70,
                    "/res/espinho.png"
            ));
        }

        // checar colisões
        verificarColisoes();

        repaint();

        if (deveReiniciar) {
            reiniciarJogo();
            deveReiniciar = false;
        }
    }

    private void verificarColisoes() {

        // colisão com inimigos
        for (InimigoMapa2 i : inimigos) {
            if (i.isVisivel() && player.getBounds().intersects(i.getBounds())) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu! 💀");
                deveReiniciar = true;
            }
        }

        // colisão com espinhos
        for (EspinhoMapa2 e : espinhos) {
            Rectangle r = new Rectangle(e.getX(), e.getY(), e.getLargura(), 40);
            if (player.getBounds().intersects(r)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "💀 Você perdeu! 💀");
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
        return Math.min(novaVelocidade, 20); // limite
    }

    private int calcularIntervaloSpawn() {
        int reducaoAtual = score / FATOR_REDUCAO_DISTANCIA;
        int novoIntervalo = INTERVALO_SPAWN_BASE - reducaoAtual;
        return Math.max(INTERVALO_SPAWN_MINIMO, novoIntervalo);
    }

    public void reiniciarJogo() {

        score = 0;
        velocidadeCenario = VELOCIDADE_BASE;
        intervaloSpawn = INTERVALO_SPAWN_BASE;

        // recriar player
        player = new PlayerMapa2(100, CHAO_REAL_Y - 50);

        inimigos.clear();
        inimigos.add(new InimigoMapa2(1200, CHAO_REAL_Y - 50, velocidadeCenario));

        espinhos.clear();
        espinhos.add(new EspinhoMapa2(3000, CHAO_REAL_Y - 40, 70, 70, "/res/espinho.png"));

        timer.start();
    }
}
