package br.edu.fatecriopreto.projetoandoid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Lucas Fernandes on 26/05/2015.
 */
public class TopicosDAO {
    private static final String URL = "http://192.168.1.105:8080/SocialPlay/services/TopicosDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String LISTAR_TODOS_TOPICOS = "buscarTodosTopicos";

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
                topic.setAssunto(soapObject.getPropertyAsString("assunto".toString()));

                lista.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
