package br.edu.fatecriopreto.projetoandoid;

/**
 * Created by Andrey on 30/05/2015.
 */
public class Autocomplete {
        private int idJogo;
        private String jogo;
        private String genero;
        private String imagem;

        public Autocomplete(){
        }

        public Autocomplete(int idJogo, String jogo, String genero, String imagem) {
            this.idJogo = idJogo;
            this.jogo = jogo;
            this.genero = genero;
            //this.imagem = imagem;
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

        public String getImagem() {
            return imagem;
        }

        public void setImagem(String imagem) {
            this.imagem = imagem;
        }
}

