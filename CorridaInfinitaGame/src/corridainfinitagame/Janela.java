
package corridainfinitagame;

import MapasFases.Mapa1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.PopupMenu;
import javax.swing.JFrame;

public class Janela extends JFrame {
    //atributos
    
      
    //construtor
   public Janela (){
   add (new Mapa1 ());
   setLayout(new BorderLayout());
   add(new Mapa1(), BorderLayout.CENTER);

   setTitle ("Run to Program");
   setSize(1280,720);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fechar o jogo no x 
   setLocationRelativeTo (null);//centralizar
   setResizable (false); //não deixar redimensionar a janela
   setVisible (true);
    
    
            
    }

    private static class label extends PopupMenu {

        public label() {
        }
    }
    
}
