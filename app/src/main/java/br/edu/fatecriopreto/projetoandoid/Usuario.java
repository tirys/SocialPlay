package br.edu.fatecriopreto.projetoandoid;

/**
 * Created by Lucas Fernandes on 17/05/2015.
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String senha;
    private String email;
    private String nome;
    private int idade;
    private String imagem;
    private String local;


    public Usuario() {
        // TODO Auto-generated constructor stub
    }


    public Usuario(int idUsuario, String usuario, String senha, String email, String nome, int idade, String imagem, String local) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.idade = idade;
        this.imagem = imagem;
        this.local = local;
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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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


    public String getImagem() {
        return imagem;
    }


    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}