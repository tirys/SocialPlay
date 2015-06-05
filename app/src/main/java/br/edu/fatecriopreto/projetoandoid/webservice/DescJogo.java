package br.edu.fatecriopreto.projetoandoid.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 04/06/2015.
 */
public class DescJogo {
    //private static final String URL = "http://192.168.20.205:8080/SocialPlay/services/UsuarioDAO?wsdl";
    //private static final String URL = "http://10.0.2.2:8080/SocialPlay/services/UsuarioDAO?wsdl";
    private static final String URL = "http://socialplay.no-ip.biz:8080/SocialPlay/services/JogosDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String LISTAR_JOGOS_ID = "buscarJogosPorID";


    public List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> buscaTodosJogos(int id){
        List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> lista = new ArrayList<>();
        SoapObject buscarJogos = new SoapObject(NAMESPACE, LISTAR_JOGOS_ID);
        buscarJogos.addProperty("id", id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(buscarJogos);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + LISTAR_JOGOS_ID, envelope);

            SoapObject resposta = (SoapObject) envelope.getResponse();
            //for (SoapObject soapObject : resposta){
                br.edu.fatecriopreto.projetoandoid.Entity.DescJogo descjogos = new br.edu.fatecriopreto.projetoandoid.Entity.DescJogo();
                descjogos.setIdDescJogo(Integer.parseInt(resposta.getProperty("idJogo").toString()));
                descjogos.setDescJogo(resposta.getProperty("jogo").toString());
                descjogos.setDescGenero(resposta.getProperty("genero").toString());
                descjogos.setDescAno(Integer.parseInt(resposta.getProperty("ano").toString()));
                descjogos.setDescProdutora(resposta.getProperty("produtora").toString());
                descjogos.setDescDescricao(resposta.getProperty("descricao").toString());
                descjogos.setDescPlataforma(resposta.getProperty("plataformas").toString());
                String foto = resposta.getProperty("img_jogo").toString();
                descjogos.setDescImgByte(foto);
                    //byte[] bt = Base64.decode(foto, Base64.DEFAULT);
                    //Bitmap bm = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                    //descjogos.setImagem(bm);
                lista.add(descjogos);
            //}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }
}
