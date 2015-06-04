package br.edu.fatecriopreto.projetoandoid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

    public boolean inserirTopicos(Topicos topico){

        SoapObject inserirTopicos = new SoapObject(NAMESPACE, CADASTRARTOPICO);
        SoapObject usr = new SoapObject(NAMESPACE, "topico");
        //usr.addProperty("idUsuario", usuario.getIdUsuario());
        usr.addProperty("nome", topico.getNome());
        usr.addProperty("descricao",topico.getDescricao());
        usr.addProperty("codJogo", topico.getCodCategoria());
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
        usr.addProperty("titulo",titulo);
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

    public boolean excluirTopico(int id){
        SoapObject excluirtopico = new SoapObject(NAMESPACE, EXCLUIRTOPICO);
        SoapObject usr = new SoapObject(NAMESPACE, "topico");
        usr.addProperty("id",id);


        excluirtopico.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(excluirtopico);
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

                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
