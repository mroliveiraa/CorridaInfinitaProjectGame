package BancoDeDados;

import java.io.Serializable;

public class UsuarioDto implements Serializable{
    private String name;
    private String senha;
    private double pontuacao;

    public UsuarioDto(){}

    public UsuarioDto(String name, String senha, double pontuacao) {
        this.name = name;
        this.senha = senha;
        this.pontuacao = pontuacao;
    }
    
    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
