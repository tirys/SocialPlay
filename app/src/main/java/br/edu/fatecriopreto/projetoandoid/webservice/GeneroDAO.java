package br.edu.fatecriopreto.projetoandoid.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import br.edu.fatecriopreto.projetoandoid.Entity.Genero;

/**
 * Created by Jessica on 07/06/2015.
 */
public class GeneroDAO {
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/GeneroDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";


    private static final String BUSCAGENERO = "buscarGeneros";

    public List<Genero> buscaGenero(int id){
        List<Genero> lista = new ArrayList<>();
        SoapObject buscaGenero = new SoapObject(NAMESPACE, BUSCAGENERO);
        buscaGenero.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscaGenero);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try{
            http.call("urn" + BUSCAGENERO, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for(SoapObject soapObject : resposta){
                Genero gen = new Genero();
                gen.setIdGenero(Integer.parseInt(soapObject.getProperty("idGenero").toString()));
                gen.setGenero(soapObject.getProperty("genero").toString());
                //gen.setCodPlataforma(Integer.parseInt(soapObject.getProperty("codPlataforma").toString()));
                lista.add(gen);
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return lista;
    }
}
