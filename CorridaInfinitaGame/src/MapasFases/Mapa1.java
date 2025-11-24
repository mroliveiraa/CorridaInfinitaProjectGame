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

        // adan - fecha corretamente quando clicar no X
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //adan - cria o painel do jogo (MAPA 1)
        PainelJogo painel = new PainelJogo();
        setContentPane(painel);

        //adan - quando a janela fechar, para o jogo
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                painel.pararJogo();  
                // adan - para o timer do MAPA1 (evita o lag no Mapa2)
            }
        });

        setVisible(true);
    }
}
