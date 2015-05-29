package br.edu.fatecriopreto.projetoandoid.Entity;

/**
 * Created by Jessica on 23/05/2015.
 */
public class Jogos {
    private String nome;
    private String descricao;
    private String produtora;
    private long ano;
    private long id;

    public Jogos(){}

    public Jogos(long id,String nome, String descricao,String produtora, long ano){
        this.setId(id);
        this.setAno(ano);
        this.setDescricao(descricao);
        this.setNome(nome);
        this.setProdutora(produtora);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    public long getAno() {
        return ano;
    }

    public void setAno(long ano) {
        this.ano = ano;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
