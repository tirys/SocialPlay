package br.edu.fatecriopreto.projetoandoid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andrey on 30/05/2015.
 */
public class AutocompleteDAO {

    //private static final String URL = "http://192.168.20.205:8080/SocialPlay/services/UsuarioDAO?wsdl";
    //private static final String URL = "http://10.0.2.2:8080/SocialPlay/services/UsuarioDAO?wsdl";
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/AutocompleteDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String LISTAR_TODOS_JOGOS = "buscarTodosJogos";


    public List<Autocomplete> buscaTodosJogos(){
        List<Autocomplete> lista = new ArrayList<>();
        SoapObject buscarJogos = new SoapObject(NAMESPACE, LISTAR_TODOS_JOGOS);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarJogos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTAR_TODOS_JOGOS, envelope);

            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for (SoapObject soapObject : resposta){
                Autocomplete jogos = new Autocomplete();
                jogos.setIdJogo(Integer.parseInt(soapObject.getProperty("idJogo").toString()));
                jogos.setJogo(soapObject.getProperty("jogo").toString());
               // jogos.setGenero(soapObject.getPropertyAsString("genero".toString()));

                String foto = soapObject.getProperty("imagem").toString();
                byte[] bt = Base64.decode(foto, Base64.DEFAULT);
                Bitmap bm = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                jogos.setImagem(bm);
                jogos.setImgByte(foto);

                lista.add(jogos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
