package MapasFases;

import Mecanicas.PainelJogoMapa2;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Mapa2 extends JFrame {

    public Mapa2() {

        setTitle("Mapa 2");

        // Cria o painel do mapa 2
        PainelJogoMapa2 painel = new PainelJogoMapa2();

        // Usa BorderLayout para o painel preencher tudo corretamente
        setLayout(new BorderLayout());
        setContentPane(painel);

        // Ajusta o tamanho da janela exatamente ao tamanho do painel
        pack();

        // Configurações padrão
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}
