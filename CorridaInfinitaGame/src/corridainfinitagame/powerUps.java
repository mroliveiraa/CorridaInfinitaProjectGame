
package corridainfinitagame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class powerUps extends JFrame {
    
    //atributos
    
    private int vida = 2 ;
    private double invulneravel = 0.5 ;
    private double ima = 0.5;
    
    public JLabel lblStatus;
    //Interface
    public powerUps (){
        setTitle ("Aprimoramento de Habilidades");
        setSize (1600, 1200);//tamanho
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//botao de fechar
        setLayout(new FlowLayout ());
        
        //Botoes
JButton btnVida = new JButton ("Aumentar vida");
JButton btnInvulneravel = new JButton ("Aumentar vulnerabilidade");
JButton btnIma = new JButton ("Aumentar força do ima");
   
// Label para mostrar status
        lblStatus = new JLabel(getStatus());

        // Ações dos botões
        btnVida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vida++;
                atualizarStatus();
            }
        });

        btnInvulneravel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invulneravel++;
                atualizarStatus();
            }
        });

        btnIma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ima++;
                atualizarStatus();
            }
        });

        // Adiciona componentes na janela
        add(btnVida);
        add(btnInvulneravel);
        add(btnIma);
        add(lblStatus);

        setVisible(true);
    }

    private void atualizarStatus() {
        lblStatus.setText(getStatus());
    }

    private String getStatus() {
        return "Vida: " + vida + " | Invulnerabilidade: " + invulneravel + " | Forca do Ima: " + ima;
    }



public static void main (String [] args ){
    new powerUps ();
    
}
        
    }
    

