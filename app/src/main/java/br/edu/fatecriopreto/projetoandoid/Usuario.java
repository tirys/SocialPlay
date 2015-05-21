package br.edu.fatecriopreto.projetoandoid;

/**
 * Created by Lucas Fernandes on 17/05/2015.
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String senha;
    private String nome;
    private int idade;

    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    public Usuario(int idUsuario, String usuario, String senha, String nome, int idade) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }


    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getUsuario() {
        return usuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
    }

}