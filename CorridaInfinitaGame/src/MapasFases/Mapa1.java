package MapasFases;

import Mecanicas.PainelJogo;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

// author @Mateus Ribeiro

public class Mapa1 extends JFrame {

    public Mapa1() {

        setTitle("Mapa 1");
        setSize(1280, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        //fecha corretamente quando clicar no X
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //cria o painel do jogo
        PainelJogo painel = new PainelJogo();
        setContentPane(painel);

        //quando a janela fechar, para o jogo
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                painel.pararJogo();  
                //para o timer do MAPA1 (evita rodar por trás)
            }
        });

        setVisible(true);
    }
}
