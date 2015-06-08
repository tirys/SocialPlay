package br.edu.fatecriopreto.projetoandoid.Entity;

/**
 * Created by Jessica on 07/06/2015.
 */
public class Genero {
    private int idGenero;
    private String genero;
    private int codPlataforma;

    public Genero(){

    }

    public Genero(int idGenero, String genero, int codPlataforma) {
        this.idGenero = idGenero;
        this.genero = genero;
        this.codPlataforma = codPlataforma;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCodPlataforma() {
        return codPlataforma;
    }

    public void setCodPlataforma(int codPlataforma) {
        this.codPlataforma = codPlataforma;
    }
}
