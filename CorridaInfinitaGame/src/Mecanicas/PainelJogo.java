//@Mateus Ribeiro
//classe responsável pelos elementos na tela
package Mecanicas;

import MapasFases.TelaSelecaoFases;
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
    private final int VELOCIDADE_BASE = 15;
    private final int FATOR_DIFICULDADE = 100;
    // -----------------------------------------------------

    // --- VARIÁVEIS DE DISTÂNCIA DINÂMICA (NOVO) ---
    private final int INTERVALO_SPAWN_BASE = 150;
    private final int INTERVALO_SPAWN_MINIMO = 40;
    private final int FATOR_REDUCAO_DISTANCIA = 20;
    // ----------------------------------------------

    private int velocidadeCenario = 5;

    private int backgroundX = 0;
    private ImageIcon gifFundo;
    private int contadorSpawnInimigo = 0;
    private int intervaloSpawn = INTERVALO_SPAWN_BASE;

    //contador espinhos
    private int contadorSpawnEspinho = 0; 
    private int intervaloEspinhos = 450; 

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
        espinhos.add(new Espinhos(3000, 397, 70, 55, "/res/espinho.png"));
        
        // lava
        lavas = new ArrayList<>();
        lavas.add(new Lava(7800, 480, 120, 235, "/res/lava.png"));
    }
       //@Mateus Ribeiro 
    public void voltarMenu() {
    pararJogo(); // garante que o timer não continue rodando
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.dispose(); // fecha o jogo
    new TelaSelecaoFases(); // reabre o menu
}
    
    //@Mateus Ribeiro
    private void pausarJogo() {
    timer.stop(); // pausa o jogo

    JDialog pauseDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Pause", true);
    pauseDialog.setSize(300, 200);
    pauseDialog.setLayout(new GridLayout(3, 1));
    pauseDialog.setLocationRelativeTo(this);

    JButton btnContinuar = new JButton("Continuar");
    JButton btnMenu = new JButton("Voltar ao Menu");
    JButton btnSair = new JButton("Sair do Jogo");

    btnContinuar.addActionListener(e -> {
        timer.start(); // retoma o jogo
        pauseDialog.dispose();
    });

    btnMenu.addActionListener(e -> {
        pauseDialog.dispose();
        voltarMenu(); // método que troca para TelaSelecaoFases
    });

    btnSair.addActionListener(e -> System.exit(0));

    pauseDialog.add(new JLabel("Jogo Pausado", SwingConstants.CENTER));
    pauseDialog.add(btnContinuar);
    pauseDialog.add(btnMenu);
    pauseDialog.add(btnSair);

    pauseDialog.setVisible(true);
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fundo
        g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);

        int alturaChao = 450;
        int alturaRestante = getHeight() - alturaChao;

        // chão COM BURACO para a lava 
        Lava lavaAtual = lavas.get(0); // pega a lava atual
        int lavaX = lavaAtual.getX();
        int lavaLargura = lavaAtual.getLargura();

        //chão antes da lava
        g.setColor(Color.BLACK);
        g.fillRect(0, alturaChao, lavaX, alturaRestante);

        //chão depois da lava
        int inicioDepoisLava = lavaX + lavaLargura;
        g.fillRect(inicioDepoisLava, alturaChao, getWidth() - inicioDepoisLava, alturaRestante);

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
            e.desenhar(g); // desenha o espinho na tela
        }

        // lava
        for (Lava l : lavas) {
            l.desenhar(g); //desenha a lava dentro do buraco
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

        // spawn inimigos (equilibrado, evita nascer grudado)
    contadorSpawnInimigo++;
    if (contadorSpawnInimigo >= intervaloSpawn) {
        contadorSpawnInimigo = 0;

        // distância mínima aumentada
        int posX = getWidth() + 400 + (int)(Math.random() * 300);

        // impede inimigo de nascer colado em espinho
        if (!espinhos.isEmpty()) {
            Espinhos ultimo = espinhos.get(espinhos.size() - 1);
            if (posX - ultimo.getX() < 400) {
                posX = ultimo.getX() + 400;
            }
        }
        inimigos.add(new Inimigo(posX, 400, velocidadeCenario));
    }


    //Criar espinhos automaticamente, evitando que eles nasçam grudados
    contadorSpawnEspinho++;
    if (contadorSpawnEspinho >= intervaloEspinhos) {
        contadorSpawnEspinho = 0;

        // espinho nasce mais longe
        int posX = getWidth() + 500 + (int)(Math.random() * 400);

        // impede espinho colado em inimigo
        if (!inimigos.isEmpty()) {
            Inimigo ultimo = inimigos.get(inimigos.size() - 1);
            if (posX - ultimo.getX() < 350) {
                posX = ultimo.getX() + 350;
            }
        }

        // impede espinho colado em espinho
        if (!espinhos.isEmpty()) {
            Espinhos ultimoEsp = espinhos.get(espinhos.size() - 1);
            if (posX - ultimoEsp.getX() < 350) {
                posX = ultimoEsp.getX() + 350;
            }
        }
        // cria espinho com posição e imagem
        espinhos.add(new Espinhos(posX, 397, 70, 55, "/res/espinho.png"));
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
private void mostrarMenuOpcoes(String titulo, String mensagem, Runnable acaoContinuar, Runnable acaoMenu) {
    JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), titulo, true);
    dialog.setSize(400, 250);
    dialog.setLocationRelativeTo(this);
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // impede fechar no X

    // Painel com layout absoluto
    JPanel painel = new JPanel() {
        ImageIcon fundo = new ImageIcon("src/res/pauseBackground.png");
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };
    painel.setLayout(null); // desativa layout automático
    dialog.setContentPane(painel);

    Font fonte = new Font("Press Start 2P", Font.PLAIN, 14);

    // Mensagem centralizada no topo
    JLabel lbl = new JLabel(mensagem, SwingConstants.CENTER);
    lbl.setFont(fonte);
    lbl.setForeground(Color.WHITE);
    lbl.setBounds(50, 20, 300, 40); // x, y, largura, altura
    painel.add(lbl);

    // Botão Continuar
    JButton btnContinuar = new JButton("▶ Continuar");
    btnContinuar.setFont(fonte);
    btnContinuar.setBounds(100, 80, 220, 40);
    btnContinuar.addActionListener(e -> {
        dialog.dispose();
        acaoContinuar.run();
    });
    painel.add(btnContinuar);

    // Botão Voltar ao Menu
    JButton btnMenu = new JButton("🏠 Voltar ao menu");
    btnMenu.setFont(fonte);
    btnMenu.setBounds(100, 140, 220, 40);
    btnMenu.addActionListener(e -> {
        dialog.dispose();
        acaoMenu.run();
    });
    painel.add(btnMenu);

    dialog.setVisible(true);
}
   private void verificarColisoes() {

    // colisão com inimigos
    for (Inimigo i : inimigos) {
        if (i.isVisivel() && player.getBounds().intersects(i.getBounds())) {
            mostrarMenuOpcoes(
                "Derrota",
                "💀 Você perdeu!",
                this::reiniciarJogo,   // ação continuar
                this::voltarMenu       // ação voltar ao menu
            );
            return; // sai do método para não abrir múltiplos menus
        }
    }

    // colisão com espinhos
    for (Espinhos e : espinhos) {
        Rectangle r = new Rectangle(e.getX(), 430, e.getLargura(), 40);
        if (player.getBounds().intersects(r)) {
            mostrarMenuOpcoes(
                "Derrota",
                "💀 Você perdeu!",
                this::reiniciarJogo,
                this::voltarMenu
            );
            return;
        }
    }

    // colisão com lava
    for (Lava l : lavas) {
        Rectangle r = new Rectangle(l.getX(), 445, l.getLargura(), 30);
        if (player.getBounds().intersects(r)) {
            mostrarMenuOpcoes(
                "Derrota",
                "🔥 Você caiu na lava! 🔥",
                this::reiniciarJogo,
                this::voltarMenu
            );
            return;
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
              if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        pausarJogo();
       
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
        espinhos.add(new Espinhos(3000, 397, 70, 55, "/res/espinho.png"));

        lavas.clear();
        lavas.add(new Lava(7800, 480, 120, 235, "/res/lava.png")); 

        timer.start (); 
    }

    // método para parar o jogo quando fechar o MAPA1
    public void pararJogo() {
        if (timer != null) {
            timer.stop();  //impede MAPA1 de rodar por trás
        }
    }
}
