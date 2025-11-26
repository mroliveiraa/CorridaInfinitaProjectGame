package corridainfinitagame;

import MapasFases.TelaInicial;
import java.awt.BorderLayout;
import javax.swing.JFrame;

//author @Mateus Ribeiro

public class Janela extends JFrame {

   public Janela (){

       setLayout(new BorderLayout());

       //adiciona somente uma tela inicial
       add(new TelaInicial(), BorderLayout.CENTER);
       //coloca a tela inicial dentro da janela
       
       setTitle("Run the Program");
       setSize(1280,720);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setResizable(false);
       setVisible(true);
   }
}
