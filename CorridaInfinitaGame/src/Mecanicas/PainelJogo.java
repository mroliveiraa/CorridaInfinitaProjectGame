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

    //caminho padrão do mapa 1
    private String caminhoFundo = "src/res/BackgroundMapa1.png";

    //trocar fundo no mapa 2
    public void setFundo(String caminho) {
        this.caminhoFundo = caminho;
        this.gifFundo = new ImageIcon(caminho);
    }

    private Player player;
    private Timer timer;
    private ArrayList<Inimigo> inimigos;

    private ArrayList<Espinhos> espinhos; // <- lista de espinhos
    private ArrayList<Lava> lavas;
    
    // --- VARIÁVEIS DE SCORE E DIFICULDADE (ADICIONADAS) ---
    private int score = 0; 
    private final int VELOCIDADE_BASE = 5;
    private final int FATOR_DIFICULDADE = 100;
    // -----------------------------------------------------

    // --- VARIÁVEIS DE DISTÂNCIA DINÂMICA (NOVO) ---
    private final int INTERVALO_SPAWN_BASE = 150;
    private final int INTERVALO_SPAWN_MINIMO = 40;
    private final int FATOR_REDUCAO_DISTANCIA = 20;
    // ----------------------------------------------

    private int velocidadeCenario = 3;

    private int backgroundX = 0;

    //agora esse fundo é definido pelo caminhoFundo
    private ImageIcon gifFundo;

    private int contadorSpawnInimigo = 0;
    private int intervaloSpawn = INTERVALO_SPAWN_BASE;

    //contador espinhos
    private int contadorSpawnEspinho = 0; 
    private int intervaloEspinhos = 450; 
    
    
    
    final int DISTANCIA_MINIMA_INIMIGO = 800; // mínimo entre inimigos
final int DISTANCIA_MINIMA_ESPINHOS = 700; // mínimo entre espinhos
final int DISTANCIA_MINIMA_ENTRE_OBSTACULOS = 600;



    private boolean deveReiniciar = false;

    public PainelJogo() {

        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 400);

        timer = new Timer(16, this);
        timer.start();

        //agora o fundo usa o caminho configurado (mapa1 ou mapa2)
        gifFundo = new ImageIcon(caminhoFundo);

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
        pararJogo(); 
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose(); 
        new TelaSelecaoFases(); 
    }

    //@Yago
    private void SalvarPontuacao() {
        try{
            BancoDeDados.UsuarioDto lastSave;

            BancoDeDados.UsuarioDao uDao = new BancoDeDados.UsuarioDao();

            lastSave = BancoDeDados.PersistenciaDeDados.Read();
            
            if (lastSave.getPontuacao() >= score) return;
            
            lastSave.setPontuacao(score);

            BancoDeDados.PersistenciaDeDados.Create(lastSave);
            uDao.atualizarUsuario(lastSave);
        }
        catch (Exception e){
        
        }
    }

    //@Mateus Ribeiro
    private void pausarJogo() {
        timer.stop();

        JDialog pauseDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Pause", true);
        pauseDialog.setSize(300, 200);
        pauseDialog.setLayout(new GridLayout(3, 1));
        pauseDialog.setLocationRelativeTo(this);
//Botoes do menu
        JButton btnContinuar = new JButton("Continuar");
        JButton btnMenu = new JButton("Voltar ao Menu");
        JButton btnSair = new JButton("Sair do Jogo");
//Ação do botão de continue
        btnContinuar.addActionListener(e -> {
            timer.start();
            pauseDialog.dispose();
        });
//Ação do bbotão de pause
        btnMenu.addActionListener(e -> {
            pauseDialog.dispose();
            voltarMenu();
        });

        btnSair.addActionListener(e -> System.exit(0));

        pauseDialog.add(new JLabel("Jogo Pausado", SwingConstants.CENTER));
        pauseDialog.add(btnContinuar);
        pauseDialog.add(btnMenu);
        pauseDialog.add(btnSair);

        pauseDialog.setVisible(true);
    }

    // Métodos de acesso controlado
    public void adicionarInimigo(Inimigo i) { inimigos.add(i); }
    public void adicionarEspinho(Espinhos e) { espinhos.add(e); }
    public void adicionarLava(Lava l) { lavas.add(l); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fundo
        g.drawImage(gifFundo.getImage(), 0, 0, getWidth(), getHeight(), this);

        int alturaChao = 450;
        int alturaRestante = getHeight() - alturaChao;

        // chão COM BURACO para a lava 
        Lava lavaAtual = lavas.get(0); 
        int lavaX = lavaAtual.getX();
        int lavaLargura = lavaAtual.getLargura();

        // chão antes da lava
        g.setColor(Color.BLACK);
        g.fillRect(0, alturaChao, lavaX, alturaRestante);

        // chão depois da lava
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
            e.desenhar(g);
        }

        // lava
        for (Lava l : lavas) {
            l.desenhar(g);
        }
        
        // score
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Press Start 2P", Font.BOLD, 30)); 
        g.drawString("Score: " + (score / 10), 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

        // spawn inimigos
       

contadorSpawnInimigo++;
if (contadorSpawnInimigo >= intervaloSpawn) {
    contadorSpawnInimigo = 0;

    int posX = getWidth() + DISTANCIA_MINIMA_INIMIGO + (int)(Math.random() * 600);

    if (!inimigos.isEmpty()) {
        Inimigo ultimo = inimigos.get(inimigos.size() - 1);
        if (posX - ultimo.getX() < DISTANCIA_MINIMA_INIMIGO) {
            posX = ultimo.getX() + DISTANCIA_MINIMA_INIMIGO;
        }
    }

    inimigos.add(new Inimigo(posX, 400, velocidadeCenario));
}

        // criar novos espinhos
        contadorSpawnEspinho++;
        if (contadorSpawnEspinho >= intervaloEspinhos) {
            contadorSpawnEspinho = 0;

            int posX = getWidth() + DISTANCIA_MINIMA_ESPINHOS + (int)(Math.random() * 500);

// Verifica último inimigo
if (!inimigos.isEmpty()) {
    Inimigo ultimo = inimigos.get(inimigos.size() - 1);
    if (Math.abs(posX - ultimo.getX()) < DISTANCIA_MINIMA_ENTRE_OBSTACULOS) {
        posX = ultimo.getX() + DISTANCIA_MINIMA_ENTRE_OBSTACULOS;
    }
}

// Verifica último espinho
if (!espinhos.isEmpty()) {
    Espinhos ultimoEsp = espinhos.get(espinhos.size() - 1);
    if (Math.abs(posX - ultimoEsp.getX()) < DISTANCIA_MINIMA_ENTRE_OBSTACULOS) {
        posX = ultimoEsp.getX() + DISTANCIA_MINIMA_ENTRE_OBSTACULOS;
    }
}


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
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel painel = new JPanel() {
            ImageIcon fundo = new ImageIcon("src/res/pauseBackground.png");
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fundo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        painel.setLayout(null);
        dialog.setContentPane(painel);

        Font fonte = new Font("Press Start 2P", Font.PLAIN, 14);

        JLabel lbl = new JLabel(mensagem, SwingConstants.CENTER);
        lbl.setFont(fonte);
        lbl.setForeground(Color.WHITE);
        lbl.setBounds(50, 20, 300, 40);
        painel.add(lbl);

        JButton btnContinuar = new JButton("▶ Tentar novamente");
        btnContinuar.setFont(fonte);
        btnContinuar.setBounds(50, 80, 300, 40);
        btnContinuar.addActionListener(e -> {
            dialog.dispose();
            acaoContinuar.run();
        });
        painel.add(btnContinuar);

        JButton btnMenu = new JButton("Voltar ao menu");
        btnMenu.setFont(fonte);
        btnMenu.setBounds(50, 140, 300, 40);
        btnMenu.addActionListener(e -> {
            dialog.dispose();
            acaoMenu.run();
        });
        painel.add(btnMenu);

        dialog.setVisible(true);
    }

    private void verificarColisoes() {

        for (Inimigo i : inimigos) {
            if (i.isVisivel() && player.getBounds().intersects(i.getBounds())) {
                SalvarPontuacao();
                mostrarMenuOpcoes(
                    "Derrota",
                    "💀 Você perdeu!",
                    this::reiniciarJogo,
                    this::voltarMenu
                );
                return;
            }
        }

        for (Espinhos e : espinhos) {
            Rectangle r = new Rectangle(e.getX(), 430, e.getLargura(), 40);
            if (player.getBounds().intersects(r)) {
                SalvarPontuacao();
                mostrarMenuOpcoes(
                    "Derrota",
                    "💀 Você perdeu!",
                    this::reiniciarJogo,
                    this::voltarMenu
                );
                return;
            }
        }

        for (Lava l : lavas) {
            Rectangle r = new Rectangle(l.getX(), 445, l.getLargura(), 30);
            if (player.getBounds().intersects(r)) {
                SalvarPontuacao();
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
    int incremento = score / 100;
    int novaVelocidade = VELOCIDADE_BASE + incremento;
    return Math.min(novaVelocidade, 10); // sempre retorna um int
}


    private int calcularIntervaloSpawn() {
        int reducaoAtual = score / FATOR_REDUCAO_DISTANCIA;
        int novoIntervalo = INTERVALO_SPAWN_BASE - reducaoAtual;
        return Math.max(INTERVALO_SPAWN_MINIMO, novoIntervalo);
    }
    
    //@Mateus Ribeiro
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

    public void pararJogo() {
        if (timer != null) {
            timer.stop();
        }
    }
}
