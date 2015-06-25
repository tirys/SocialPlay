package br.edu.fatecriopreto.projetoandoid.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.Entity.Games;

/**
 * Created by Jessica on 07/06/2015.
 */
public class GamesDAO {
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/JogosDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String BUSCARJOGOPORGENERO = "buscarJogosPorGenero";

    public List<Games> buscarJogosPorGenero(int id,int idplataforma){
        List<Games> listaJogos = new ArrayList<>();
        SoapObject buscarJogosPorGenero = new SoapObject(NAMESPACE, BUSCARJOGOPORGENERO);
        buscarJogosPorGenero.addProperty("id", id);
        buscarJogosPorGenero.addProperty("idplataforma", idplataforma);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarJogosPorGenero);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call("urn" + BUSCARJOGOPORGENERO, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            int tamanho = resposta.size();

            //for (SoapObject soapObject : resposta){
            for (int i=0;i<tamanho-2; ++i){
                SoapObject so =  resposta.get(i);
                Games jog = new Games();
                jog.setIdJogo(Integer.parseInt(so.getProperty("idJogo").toString()));
                jog.setJogo(so.getProperty("jogo").toString());
                jog.setImgJogoGene(so.getPropertyAsString("img_jogo").toString());
                //jog.setCodGenero(Integer.parseInt(soapObject.getProperty("codGenero").toString()));

                listaJogos.add(jog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*
        try {
            http.call("urn" + BUSCARJOGOPORGENERO, envelope);
            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for (SoapObject soapObject : resposta){
                Games jog = new Games();
                jog.setIdJogo(Integer.parseInt(soapObject.getProperty("idJogo").toString()));
                jog.setJogo(soapObject.getProperty("jogo").toString());
                //jog.setCodGenero(Integer.parseInt(soapObject.getProperty("codGenero").toString()));
                listaJogos.add(jog);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
        return listaJogos;
    }
}



