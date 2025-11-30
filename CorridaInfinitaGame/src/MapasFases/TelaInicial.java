
package MapasFases;
import Personagem.TelaSelecaoPersonagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
                  String nome;
                  BancoDeDados.UsuarioDto data;
                  
                  var LastSave = BancoDeDados.PersistenciaDeDados.Read();
            if (LastSave == null || JOptionPane.showConfirmDialog(TelaInicial.this, "Continuar com Save antigo?") != 0){ 
                do{
                    nome = JOptionPane.showInputDialog(TelaInicial.this, "Digite o seu nome"); //Abre a janela para digitar nome
                     //Confere se o nome não está vazio
                     if (nome == null){
                         JOptionPane.showMessageDialog(null, "Cancelada.");
                         return;
                     }
                     if (nome.trim().isEmpty()) { //Se estiver
                         JOptionPane.showMessageDialog(null, "Nome invalido. Tente novamente.");
                     }

                }while (nome.trim().isEmpty());
                
                String senha;
                do{
                    senha = JOptionPane.showInputDialog(TelaInicial.this, "Digite uma senha");
                    if (senha == null){
                        JOptionPane.showMessageDialog(null, "Cancelada.");
                        return;
                    }
                    if (senha.trim().isEmpty()) { 
                        JOptionPane.showMessageDialog(null, "Senha invalida. Tente novamente.");
                    }

                } while (senha.trim().isEmpty());
                data = new BancoDeDados.UsuarioDto(nome, senha, 0);
                LastSave = null;
            }else{
                nome = LastSave.getName();
                data = LastSave;
            }
                   JOptionPane.showMessageDialog(TelaInicial.this, "Bem vindo " +nome+" !");
                   
                   BancoDeDados.PersistenciaDeDados.Create(data);
                   
                   /*@Yago
                    Bug: essa parte faz com que o jogador tenha que 
                    clicar no botão iniciar duas vezes caso o banco de dados fique inativo
                   */
                   if (LastSave == null)
                    new BancoDeDados.UsuarioDao().cadastrarUsuario(data);
                   
                   new TelaSelecaoPersonagem(); //abre a tela de personagem
                  javax.swing.SwingUtilities.getWindowAncestor(TelaInicial.this).dispose(); //Fecha a tela inicial
        });
               
                      
              JButton btn_opcoes = new JButton ("Ranking");
              btn_opcoes.setSize(100,250);
              btn_opcoes.setBounds(500, 450, 200, 50);
              
              btn_opcoes.addActionListener ((ActionEvent e) -> {
                    BancoDeDados.TelaRanking panel = new BancoDeDados.TelaRanking();
                    panel.setVisible(true);
                    javax.swing.SwingUtilities.getWindowAncestor(TelaInicial.this).dispose();
              });
              
              add (btn_opcoes);
          }
              
             
}
       
       
       
       
       
            
     
   

  

   
    
    
  
        
    

