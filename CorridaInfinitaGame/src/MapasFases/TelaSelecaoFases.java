package MapasFases;

import java.awt.Font;
import javax.swing.*;

// author @Mateus Ribeiro

public class TelaSelecaoFases extends JFrame {
    
    // Ícone do botão do mapa 1
    ImageIcon icone1 = new ImageIcon("src/res/BotãoMapa1.png");

    private String personagemSelecionado;
    
    public TelaSelecaoFases(String personagemSelecionado) {
this.personagemSelecionado = personagemSelecionado;
        setTitle("Selecione o Mapa");

        // tamanho fixo da tela de seleção
        setSize(1280, 720);
        setResizable(false);

        // usando layout absoluto para manter seus botões no mesmo lugar
        setLayout(null);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Título
        JLabel txt_titulo = new JLabel("Selecione o Mapa");
        txt_titulo.setBounds(400, 50, 700, 60);
        txt_titulo.setFont(new Font("Press Start 2P", Font.BOLD, 30));
        add(txt_titulo);

        // Botão MAPA 1
        JButton btn_mapa1 = new JButton();
        btn_mapa1.setBounds(250, 125, 350, 200);

        // coloca a imagem do botão do mapa 1
        btn_mapa1.setIcon(icone1);
        add(btn_mapa1);

        // ação do botão MAPA 1
        btn_mapa1.addActionListener(e -> {

            //abre mapa 1 e fecha o menu
            new Mapa1(personagemSelecionado);
            dispose();
        });

        //Botão MAPA 2
        JButton btn_mapa2 = new JButton("Mapa 2");
        btn_mapa2.setBounds(700, 125, 350, 200);
        btn_mapa2.setFont(new Font("Press Start 2P", Font.BOLD, 26));
        add(btn_mapa2);

        btn_mapa2.addActionListener(e -> {

            //abre mapa 2 e fecha o menu
            new Mapa2(personagemSelecionado);
            dispose();
        });

        // deixa a tela visível no final
        setVisible(true);
    }
}
