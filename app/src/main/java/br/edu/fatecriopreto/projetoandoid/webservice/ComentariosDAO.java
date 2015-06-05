package br.edu.fatecriopreto.projetoandoid.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.Topicos;

/**
 * Created by Jessica on 04/06/2015.
 */
public class ComentariosDAO {
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/ComentariosDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String LISTARCOMENTARIOS = "listarComentarios";
    private static final String INSERIRCOMENTARIOS = "inserirComentario";


    public boolean inserirComentario(Comentarios comentario){

        SoapObject inserirComentario= new SoapObject(NAMESPACE, INSERIRCOMENTARIOS);
        SoapObject usr = new SoapObject(NAMESPACE, "comentario");

        usr.addProperty("idpessoa",comentario.getIdpessoa());
        usr.addProperty("conteudo", comentario.getConteudo());
        usr.addProperty("data",comentario.getData());
        usr.addProperty("codtopico",comentario.getIdtopico());

        inserirComentario.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirComentario);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + comentario, envelope);
            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

  public List<Comentarios> listarComentariosporid(int id){
        List<Comentarios> lista = new ArrayList<>();
        SoapObject listarComentario= new SoapObject(NAMESPACE, LISTARCOMENTARIOS);
        listarComentario.addProperty("id", id);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(listarComentario);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTARCOMENTARIOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            int tamanho = resposta.size();

            //for (SoapObject soapObject : resposta){
            for (int i=0;i<tamanho-2; ++i){
                SoapObject so =  resposta.get(i);
                Comentarios topic = new Comentarios();
                topic.setId(Integer.parseInt(so.getProperty("id").toString()));
                topic.setConteudo(so.getProperty("conteudo").toString());
                topic.setIdpessoa(Integer.parseInt(so.getProperty("idpessoa").toString()));


                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
