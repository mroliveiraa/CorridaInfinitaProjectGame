
package corridainfinitagame;

import MapasFases.TelaInicial;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Janela extends JFrame {
    //atributos
    
      
    //construtor
   public Janela (){
   add (new TelaInicial ());
   setLayout(new BorderLayout());
   add(new TelaInicial(), BorderLayout.CENTER);
   
   setTitle ("Run the Program");
   setSize(1280,720);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fechar o jogo no x 
   setLocationRelativeTo (null);//centralizar
   setResizable (false); //não deixar redimensionar a janela
   setVisible (true);
   
  
   }
}

   
   
   
    
    
    