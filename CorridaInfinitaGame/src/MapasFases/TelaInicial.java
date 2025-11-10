
package MapasFases;
import Personagem.TelaSelecaoPersonagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
//author @Mateus Ribeiro
public class TelaInicial extends JPanel {
    
       //construtor
    public TelaInicial(){ //objeto
        
       
       
       //Titulo pulando
        setLayout (null);
         URL local = getClass ().getResource("/res/TituloAnimado.gif");
            
             ImageIcon tituloAnimado = new ImageIcon (local);
             JLabel tituloInicio = new JLabel (tituloAnimado);
             tituloInicio.setBounds (300,100, tituloAnimado.getIconWidth(), tituloAnimado.getIconHeight());
             add (tituloInicio);
             
             
              JButton btn_iniciar = new JButton ("Iniciar");
              btn_iniciar.setSize(100, 250);
              btn_iniciar.setBounds(500,350, 200, 50);
              add (btn_iniciar);
              
              //ativar o botão
              btn_iniciar.addActionListener ((ActionEvent e) -> {
              new TelaSelecaoPersonagem(); //abre a tela de personagem
              javax.swing.SwingUtilities.getWindowAncestor(this).dispose();//Fecha a tela inicial
              });
                      
              JButton btn_opcoes = new JButton ("Opcoes");
              btn_opcoes.setSize(100,250);
              btn_opcoes.setBounds(500, 450, 200, 50);
              add (btn_opcoes);
              
               //Fundo animado do bg
        
              
             
        
       
       
       
       
       
            
     
    }

    }

   
    
    
  
        
    

