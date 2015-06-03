package br.edu.fatecriopreto.projetoandoid;

import android.graphics.Bitmap;

/**
 * Created by Andrey on 30/05/2015.
 * private static final long serialVersionUID = -2229832341556924673L;
 *
 * implements Serializable
 */
public class Autocomplete {
        private int idJogo;
        private String jogo;
        private String genero;
        private Bitmap imagem;

        public Autocomplete(){
        }

        public Autocomplete(int idJogo, String jogo, String genero,  Bitmap imagem) {
            this.idJogo = idJogo;
            this.jogo = jogo;
            this.genero = genero;
            this.imagem = imagem;
        }

        public int getIdJogo() {
            return idJogo;
        }

        public void setIdJogo(int idJogo) {
            this.idJogo = idJogo;
        }

        public String getJogo() {
            return jogo;
        }

        public void setJogo(String jogo) {
            this.jogo = jogo;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public  Bitmap getImagem() {
            return imagem;
        }

        public void setImagem( Bitmap imagem) {
            this.imagem = imagem;
        }
}

