
package MapasFases;
import java.awt.Font;
import javax.swing.*;
//author @Mateus Ribeiro
public class TelaSelecaoFases extends JFrame {
    
    ImageIcon icone1 = new ImageIcon("src/res/BotãoMapa1.png");
    
    public TelaSelecaoFases (){
      setTitle ("Selecione o Mapa");
        setSize (1280,720);
        setLayout (null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (null);
        //criação do titulo e dos botões de mapa
        setVisible (true);
        JLabel txt_titulo = new JLabel ("Selecione o Mapa");
        txt_titulo.setBounds(550, 50, 300 , 60);
        txt_titulo.setFont(new Font("Comic Sans", Font.BOLD, 30));
        add (txt_titulo);
        
        JButton btn_mapa1 = new JButton ();
        btn_mapa1.setBounds(250, 125, 350, 200);
        btn_mapa1.setIcon(icone1);
        add (btn_mapa1);
        
        
        JButton btn_mapa2 = new JButton ();
        btn_mapa2.setBounds(700, 125, 350, 200);
        add (btn_mapa2);
        
        JButton btn_mapa3 = new JButton ();
        btn_mapa3.setBounds(480, 350, 350, 200);
        add (btn_mapa3);
        //selecionar os mapas
         btn_mapa1.addActionListener (e ->{
         new Mapa1 ();
         dispose ();
             
         });
}
}
