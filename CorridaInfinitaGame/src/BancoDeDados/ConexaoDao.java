package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexaoDao {
    
    private String url = "jdbc:mysql://localhost:3306/programadoresvsrobos";
    private Connection conexao;
    
    public Connection conectarBd(){
        try {
            // quando conseguir um banco de dados publico troque por:
          //conexao = DriverManager.getConnection(url);
            conexao = DriverManager.getConnection(url, "root", "1234");
            JOptionPane.showMessageDialog(null, "Conexão feita");
            return conexao;
        }catch(Exception e){
            return null;
        }
    }
}
