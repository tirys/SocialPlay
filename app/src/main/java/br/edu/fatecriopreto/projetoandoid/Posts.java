package br.edu.fatecriopreto.projetoandoid;

/**
 * Created by Jessica on 10/05/2015.
 */
public class Posts {

    private long id;
    private String titulo;
    private String descricao;
    private long diamantes;

    public Posts(){}
    public Posts(long id, String titulo, String descricao,long diamantes)
    {
        this.setTitulo(titulo);
        this.setId(id);
        this.setDescricao(descricao);
        this.setDiamantes(diamantes);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public void setDiamantes(long diamantes) {
        this.diamantes = diamantes;
    }
}
