
package MapasFases;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class Mapa1 extends JPanel {
    
    
    
    
   
    //construtor
    public Mapa1(){
        setLayout (null);
        
      
       URL location = getClass().getResource("/res/BackgroundAnimado1.gif");
        
    
            ImageIcon gifAnimado = new ImageIcon(location);
           
            JLabel fundoLabel = new JLabel (gifAnimado);
            fundoLabel.setBounds(0, 0, gifAnimado.getIconWidth(), gifAnimado.getIconHeight());
            add (fundoLabel);
            
    
    }
    }
    
  
        
    

