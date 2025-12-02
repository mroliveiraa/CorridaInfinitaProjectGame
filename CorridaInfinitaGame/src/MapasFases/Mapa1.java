package MapasFases;

import Mecanicas.PainelJogo;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Mapa1 extends JFrame {

    public Mapa1(String personagemSelecionado) {

        setTitle("Mapa 1");
        setSize(1280, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        String caminhoFundo = "/res/BackgroundMapa1.png";

        // Usa o painel padrão
        PainelJogo painel = new PainelJogo(personagemSelecionado, caminhoFundo);
        setContentPane(painel);

        //fecha corretamente quando clicar no X
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Troca o fundo do mapa 2
        painel.setFundo("src/res/BackgroundMapa1.png");

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
