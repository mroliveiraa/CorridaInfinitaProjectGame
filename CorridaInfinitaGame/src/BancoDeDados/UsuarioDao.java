package BancoDeDados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {
    
    private Connection conexao;
   
    public void cadastrarUsuario (UsuarioDto usuario){
        conexao = new ConexaoDao().conectarBd();
        
        try{
            String sql = "insert into usuario (Nome, Senha, Pontuacao) values (?,?,?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
            
            pstm.setString(1, usuario.getName());
            pstm.setString(2, usuario.getSenha());
            pstm.setString(3, String.valueOf(usuario.getPontuacao()) );
            
            pstm.execute();
            pstm.close();
        } catch(SQLException e){
        
        }
    }
    public void atualizarUsuario(UsuarioDto usuario){
        conexao = new ConexaoDao().conectarBd();
        
        try{
            String sql = "update usuario set Pontuacao = ? where Name = ? and Senha = ?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            pstm.setString(1, String.valueOf(usuario.getPontuacao()) );
            pstm.setString(2, usuario.getName());
            pstm.setString(3, usuario.getSenha());
            
            pstm.executeUpdate();
            pstm.close();
        } catch(SQLException e){}
    }
    public ResultSet Listar(){
        conexao = new ConexaoDao().conectarBd();
        
        try{
            String sql = "select * from usuario order by Pontuacao desc";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch(SQLException e){return null;}
    }
}
