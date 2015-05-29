package br.edu.fatecriopreto.projetoandoid.Entity;

/**
 * Created by Jéssica on 23/05/2015.
 */
public class Categorias {
    public Categorias(){}

    public Categorias(long id, String nome)
    {
        this.setNome(nome);
        this.setId(id);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nome;
    private long id;


}
