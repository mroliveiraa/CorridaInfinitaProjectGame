
package Personagem;

import MapasFases.TelaSelecaoFases;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
//author @Mateus Ribeiro

public class TelaSelecaoPersonagem extends JFrame {
    public TelaSelecaoPersonagem (){
         setTitle ("Selecione o Personagem");
        setSize (1280,720);
        setLayout (null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (null);
        
      
              
        //botão selecionar kai
        JButton btn_personagemKai = new JButton ("Kai");
        btn_personagemKai.setBounds (325,500,150,50);
        add (btn_personagemKai);
         //botão selecionar eli
        JButton btn_personagemEli = new JButton ("Eli");
        btn_personagemEli.setBounds (805,500,150,50);
        add (btn_personagemEli);
         //inicia o jogo com o Kai
        btn_personagemKai.addActionListener(e -> {
            JOptionPane.showMessageDialog (this, "Você selecionou Kai!");
             new TelaSelecaoFases ();
             dispose ();
           //inicia o jogo com a Eli
             });
        btn_personagemEli.addActionListener(e -> {
            JOptionPane.showMessageDialog (this, "Você selecionou Eli!");
             new TelaSelecaoFases ();
             dispose ();
            
            });
        
       setVisible (true);
   
       
       
            
        //fundo animado do bg
        URL location = getClass().getResource("/res/BackgroundPixelado.gif"); //seleciona o local do bg
     
       ImageIcon gifAnimado = new ImageIcon(location); //instancia o objeto na localização
           
       JLabel fundoLabel = new JLabel (gifAnimado);// cria a estrutura que fica o bg
       fundoLabel.setBounds(0, 0, gifAnimado.getIconWidth(), gifAnimado.getIconHeight());
       add(fundoLabel);
       
       
       
       
   
}
}
