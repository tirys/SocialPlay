package br.edu.fatecriopreto.projetoandoid.Entity;

import android.content.Intent;

import java.util.Date;

/**
 * Created by Jessica on 29/05/2015.
 */
public class Comentarios {


    private int idtopico;
    private String conteudo;
    private String data;
    private Integer idpessoa;

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    private String nomePessoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Comentarios(){}

    public Comentarios(int id,Integer idpessoa, String conteudo,String data,int idtopico, String nomePessoa)
    {
        this.setId(id);
        this.setIdpessoa(idpessoa);
        this.setConteudo(conteudo);
        this.setData(data);
        this.setIdtopico(idtopico);
        this.setNomePessoa(nomePessoa);
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public int getIdtopico() {
        return idtopico;
    }

    public void setIdtopico(int idtopico) {
        this.idtopico = idtopico;
    }



}
