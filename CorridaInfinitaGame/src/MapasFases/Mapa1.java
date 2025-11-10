
package MapasFases;

import Mecanicas.PainelJogo;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
//author @Mateus Ribeiro


public class Mapa1 extends JFrame{
     public Mapa1 (){
        setTitle ("Mapa 1");
        setSize (1280,720);
        setLayout (null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (null);
       
        
         PainelJogo painel = new PainelJogo ();
         setContentPane(painel);
         
         
        
         setVisible (true);
        
 
    
}
}

