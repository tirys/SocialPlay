package br.edu.fatecriopreto.projetoandoid.Entity;

import android.graphics.Bitmap;

/**
 * Created by Andrey on 04/06/2015.
 */
public class DescJogo {

    private int idDescJogo;
    private String descJogo;
    private String descGenero;
    private int descAno;
    private String descProdutora;
    private String descDescricao;
    private Bitmap descImagem;
    private String descPlataforma;
    private String descImgByte;


    public DescJogo(){
    }

    public DescJogo(int idDescJogo, String descJogo, String descGenero, int descAno, String descProdutora, String descDescricao, Bitmap descImagem, String descPlataforma, String descImgByte) {
        this.idDescJogo = idDescJogo;
        this.descJogo = descJogo;
        this.descGenero = descGenero;
        this.descAno = descAno;
        this.descProdutora = descProdutora;
        this.descDescricao = descDescricao;
        this.descImagem = descImagem;
        this.descPlataforma = descPlataforma;
        this.descImgByte = descImgByte;
    }

    public int getIdDescJogo() {
        return idDescJogo;
    }

    public void setIdDescJogo(int idDescJogo) {
        this.idDescJogo = idDescJogo;
    }

    public String getDescJogo() {
        return descJogo;
    }

    public void setDescJogo(String descJogo) {
        this.descJogo = descJogo;
    }

    public String getDescGenero() {
        return descGenero;
    }

    public void setDescGenero(String descGenero) {
        this.descGenero = descGenero;
    }

    public int getDescAno() {
        return descAno;
    }

    public void setDescAno(int descAno) {
        this.descAno = descAno;
    }

    public String getDescProdutora() {
        return descProdutora;
    }

    public void setDescProdutora(String descProdutora) {
        this.descProdutora = descProdutora;
    }

    public String getDescDescricao() {
        return descDescricao;
    }

    public void setDescDescricao(String descDescricao) {
        this.descDescricao = descDescricao;
    }

    public Bitmap getDescImagem() {
        return descImagem;
    }

    public void setDescImagem(Bitmap descImagem) {
        this.descImagem = descImagem;
    }

    public String getDescPlataforma() {
        return descPlataforma;
    }

    public void setDescPlataforma(String descPlataforma) {
        this.descPlataforma = descPlataforma;
    }

    public String getDescImgByte() {
        return descImgByte;
    }

    public void setDescImgByte(String descImgByte) {
        this.descImgByte = descImgByte;
    }

}

