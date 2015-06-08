package br.edu.fatecriopreto.projetoandoid.Entity;

/**
 * Created by Jessica on 07/06/2015.
 */
public class Games {
    private int idJogo;
    private String Jogo;
    private int codGenero;

    public Games(){

    }
    public Games(int idJogo, String jogo, int codGenero) {
        this.idJogo = idJogo;
        this.Jogo = jogo;
        this.codGenero = codGenero;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getJogo() {
        return Jogo;
    }

    public void setJogo(String jogo) {
        Jogo = jogo;
    }

    public int getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
    }
}
