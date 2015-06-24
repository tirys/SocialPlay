package br.edu.fatecriopreto.projetoandoid;

/**
 * Created by Lucas Fernandes on 26/05/2015.
 */
public class Topicos {
        private int idTopico;
        private String nome;
        private String assunto;
        private String imagem;
        private String descricao;
        private int codCategoria;
        private int codUsuario;
        private String nomeUser;

        public Topicos(){
        }

        public Topicos(int idTopico, String nome, String assunto, String imagem, String descricao, int codCategoria, int codUsuario, String nomeUser) {
            this.idTopico = idTopico;
            this.nome = nome;
            this.assunto = assunto;
            this.imagem = imagem;
            this.descricao = descricao;
            this.codCategoria = codCategoria;
            this.codUsuario = codUsuario;
            this.nomeUser = nomeUser;
        }

        public int getIdTopico() {
            return idTopico;
        }

        public void setIdTopico(int idTopico) {
            this.idTopico = idTopico;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getAssunto() {
            return assunto;
        }

        public void setAssunto(String assunto) {
            this.assunto = assunto;
        }

        public String getImagem() {
            return imagem;
        }

        public void setImagem(String imagem) {
            this.imagem = imagem;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public int getCodCategoria() {
            return codCategoria;
        }

        public void setCodCategoria(int codCategoria) {
            this.codCategoria = codCategoria;
        }

        public int getCodUsuario() {
            return codUsuario;
        }

        public void setCodUsuario(int codUsuario) {
            this.codUsuario = codUsuario;
        }

        public String getNomeUser() {
            return nomeUser;
        }

        public void setNomeUser(String nomeUser) {
            this.nomeUser = nomeUser;
        }
}

