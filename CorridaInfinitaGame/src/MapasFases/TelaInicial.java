
package MapasFases;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class TelaInicial extends JPanel {
    
       //construtor
    public TelaInicial(){ //objeto
        setLayout (null);
         URL local = getClass ().getResource("/res/TituloAnimado.gif");
            
       ImageIcon tituloAnimado = new ImageIcon (local);
             JLabel tituloInicio = new JLabel (tituloAnimado);
             tituloInicio.setBounds (300,100, tituloAnimado.getIconWidth(), tituloAnimado.getIconHeight());
             add (tituloInicio);
             
        URL location = getClass().getResource("/res/BackgroundPixelado.gif"); //seleciona o local do bg
     
       ImageIcon gifAnimado = new ImageIcon(location); //instancia o objeto na localização
           
       JLabel fundoLabel = new JLabel (gifAnimado);// cria a estrutura que fica o bg
       fundoLabel.setBounds(0, 0, gifAnimado.getIconWidth(), gifAnimado.getIconHeight());
       add(fundoLabel);
       
      
       
       
            
        }
    }
    
  
        
    

