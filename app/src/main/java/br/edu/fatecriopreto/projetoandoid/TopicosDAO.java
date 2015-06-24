package br.edu.fatecriopreto.projetoandoid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;

/**
 * Created by Lucas Fernandes on 26/05/2015.
 */
public class TopicosDAO {
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/TopicosDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String LISTAR_TODOS_TOPICOS = "buscarTodosTopicos";
    private static final String CADASTRARTOPICO = "cadastrarTopico";
    private static final String EDITARTOPICO = "editarTopico";
    private static final String EXCLUIRTOPICO = "excluirTopico";
    private static final String LISTARTOPICOSPORID = "listarPostsporId";
    private static final String BUSCARRELEVANTES = "buscarTopicosrelevantes";
    private static final String PESSOASEGUE= "pessoaSegue";
    private static final String LISTARPOSTSUSUARIO ="listarPostsporUsuario";
    private static final String LISTARPOSTSSEGUINDO ="listarPostsSeguindo";
    private static final String SEGUIR  ="seguirPost";
    private static final String NSEGUIR  ="NaoseguirPost";

    public boolean inserirTopicos(Topicos topico){

        SoapObject inserirTopicos = new SoapObject(NAMESPACE, CADASTRARTOPICO);
        SoapObject usr = new SoapObject(NAMESPACE, "topico");
        //usr.addProperty("idUsuario", usuario.getIdUsuario());
        usr.addProperty("nome", topico.getNome());
        usr.addProperty("descricao",topico.getDescricao());
        usr.addProperty("codCategoria", topico.getCodCategoria());
        usr.addProperty("codUsuario",topico.getCodUsuario());

        //usr.addProperty("imagem", usuario.getImagem());
        inserirTopicos.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirTopicos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + topico, envelope);
            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarTopico(int id, String titulo,String descricao){

        SoapObject atualizarUsuario = new SoapObject(NAMESPACE, EDITARTOPICO);
        SoapObject usr = new SoapObject(NAMESPACE, "topico");
        usr.addProperty("idTopico", id);
        usr.addProperty("nome",titulo);
        usr.addProperty("descricao", descricao);

        atualizarUsuario.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(atualizarUsuario);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + EDITARTOPICO, envelope);
            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int pessoaSegue(int idPessoa,int idTopico){

        SoapObject pessoaSegue = new SoapObject(NAMESPACE, PESSOASEGUE);
        pessoaSegue.addProperty("idPessoa", idPessoa);
        pessoaSegue.addProperty("idTopico", idTopico);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(pessoaSegue);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + PESSOASEGUE, envelope);

            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Integer.parseInt(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean excluirTopico(int id){
        SoapObject excluirTopico = new SoapObject(NAMESPACE, EXCLUIRTOPICO);
        excluirTopico.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(excluirTopico);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + EXCLUIRTOPICO, envelope);
            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Topicos> buscaTodosTopicos(){
        List<Topicos> lista = new ArrayList<>();
        SoapObject buscarTopicos = new SoapObject(NAMESPACE, LISTAR_TODOS_TOPICOS);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarTopicos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTAR_TODOS_TOPICOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for (SoapObject soapObject : resposta){
                Topicos topic = new Topicos();
                topic.setIdTopico(Integer.parseInt(soapObject.getProperty("idTopico").toString()));
                topic.setNome(soapObject.getProperty("nome").toString());
                topic.setDescricao(soapObject.getPropertyAsString("descricao".toString()));
                topic.setCodUsuario(Integer.parseInt(soapObject.getProperty("codUsuario").toString()));
                topic.setImagem(soapObject.getPropertyAsString("imagem".toString()));
                topic.setNomeUser(soapObject.getProperty("nomeUsuario").toString());
                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public List<Topicos>  buscarTopicosrelevantes(){
        List<Topicos> lista = new ArrayList<>();
        SoapObject buscarTopicos = new SoapObject(NAMESPACE, BUSCARRELEVANTES);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarTopicos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + BUSCARRELEVANTES, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for (SoapObject soapObject : resposta){
                Topicos topic = new Topicos();
                topic.setIdTopico(Integer.parseInt(soapObject.getProperty("idTopico").toString()));
                topic.setNome(soapObject.getProperty("nome").toString());
                topic.setDescricao(soapObject.getPropertyAsString("descricao".toString()));
                topic.setCodUsuario(Integer.parseInt(soapObject.getProperty("codUsuario").toString()));
                topic.setImagem(soapObject.getPropertyAsString("imagem".toString()));
                topic.setNomeUser(soapObject.getProperty("nomeUsuario").toString());

                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public List<Topicos> listarPostsporId(int id){
        List<Topicos> lista = new ArrayList<>();
        SoapObject listarPost= new SoapObject(NAMESPACE, LISTARTOPICOSPORID);
        listarPost.addProperty("id", id);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarPost);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTARTOPICOSPORID, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            int tamanho = resposta.size();

            //for (SoapObject soapObject : resposta){
            for (int i=0;i<tamanho-2; ++i){
                SoapObject so =  resposta.get(i);
                Topicos topic = new Topicos();
                topic.setIdTopico(Integer.parseInt(so.getProperty("idTopico").toString()));
                topic.setNome(so.getProperty("nome").toString());
                topic.setDescricao(so.getPropertyAsString("descricao".toString()));
                topic.setCodUsuario(Integer.parseInt(so.getProperty("codUsuario").toString()));
                topic.setImagem(so.getPropertyAsString("imagem".toString()));
                topic.setNomeUser(so.getPropertyAsString("nomeUsuario".toString()));

                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public List<Topicos> listarPostsporUsuario(int id){
        List<Topicos> lista = new ArrayList<>();
        SoapObject listarPost= new SoapObject(NAMESPACE, LISTARPOSTSUSUARIO);
        listarPost.addProperty("id", id);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarPost);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTARPOSTSUSUARIO, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            int tamanho = resposta.size();

            //for (SoapObject soapObject : resposta){
            for (int i=0;i<tamanho-2; ++i){
                SoapObject so =  resposta.get(i);
                Topicos topic = new Topicos();
                topic.setIdTopico(Integer.parseInt(so.getProperty("idTopico").toString()));
                topic.setNome(so.getProperty("nome").toString());
                topic.setDescricao(so.getPropertyAsString("descricao".toString()));
                topic.setCodUsuario(Integer.parseInt(so.getProperty("codUsuario").toString()));
                topic.setImagem(so.getPropertyAsString("imagem".toString()));
                topic.setNomeUser(so.getPropertyAsString("nomeUsuario".toString()));



                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public List<Topicos> listarPostsSeguindo(int id){
        List<Topicos> lista = new ArrayList<>();
        SoapObject listarPost= new SoapObject(NAMESPACE, LISTARPOSTSSEGUINDO);
        listarPost.addProperty("id", id);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarPost);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTARPOSTSSEGUINDO, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            int tamanho = resposta.size();

            //for (SoapObject soapObject : resposta){
            for (int i=0;i<tamanho-2; ++i){
                SoapObject so =  resposta.get(i);
                Topicos topic = new Topicos();
                topic.setIdTopico(Integer.parseInt(so.getProperty("idTopico").toString()));
                topic.setNome(so.getProperty("nome").toString());
                topic.setDescricao(so.getPropertyAsString("descricao".toString()));
                topic.setCodUsuario(Integer.parseInt(so.getProperty("codUsuario").toString()));
                topic.setImagem(so.getPropertyAsString("imagem".toString()));
                topic.setNomeUser(so.getProperty("nomeUsuario").toString());

                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public boolean seguir(int idPost, int idUser){

        SoapObject inserirTopicos = new SoapObject(NAMESPACE, SEGUIR);
        inserirTopicos.addProperty("idPost",idPost);
        inserirTopicos.addProperty("idUser",idUser);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirTopicos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + SEGUIR, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean Naoseguir(int idPost, int idUser){

        SoapObject inserirTopicos = new SoapObject(NAMESPACE, NSEGUIR);
        inserirTopicos.addProperty("idPost",idPost);
        inserirTopicos.addProperty("idUser",idUser);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirTopicos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + NSEGUIR, envelope);

            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
