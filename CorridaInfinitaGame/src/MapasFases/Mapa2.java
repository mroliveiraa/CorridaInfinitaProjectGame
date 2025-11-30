package MapasFases;

import Mecanicas.PainelJogo;
import javax.swing.JFrame;

public class Mapa2 extends JFrame {

    public Mapa2() {

        setTitle("Mapa 2");
        setSize(1280, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Usa o painel padrão
        PainelJogo painel = new PainelJogo();
        
        //fecha corretamente quando clicar no X
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Troca o fundo do mapa 2
        painel.setFundo("src/res/fundoMapa2.png");

        setContentPane(painel);
        
         //quando a janela fechar, para o jogo
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                painel.pararJogo();  
                //para o timer do MAPA2 (evita rodar por trás)
            }
        });

        setVisible(true);
    }
}
