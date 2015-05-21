package br.edu.fatecriopreto.projetoandoid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Lucas Fernandes on 18/05/2015.
 */
public class UsuarioDAO {

    //private static final String URL = "http://192.168.20.205:8080/SocialPlay/services/UsuarioDAO?wsdl";
    private static final String URL = "http://10.0.2.2:8080/SocialPlay/services/UsuarioDAO?wsdl";
    private static final String NAMESPACE = "http://ws.socialplay.com.br";

    private static final String INSERIR = "inserirUsuario";
    private static final String EDITAR = "atualizarUsuario";
    private static final String DELETAR = "excluirUsuario";
    private static final String VERIFICALOGIN = "verificaLogin";

    public Usuario verificaLogin(String usuario, String senha){
        Usuario user = null;

        SoapObject verificaLogin = new SoapObject(NAMESPACE, VERIFICALOGIN);
        verificaLogin.addProperty("usuario", usuario);
        verificaLogin.addProperty("senha", senha);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(verificaLogin);
        envelope.implicitTypes = true;
        HttpTransportSE http = new HttpTransportSE(URL);


        try {
            http.call("urn:" + VERIFICALOGIN, envelope);
            SoapObject resposta = (SoapObject) envelope.getResponse();

            user = new Usuario();
            //user.setIdUsuario((Integer) resposta.getProperty("idUsuario"));
            user.setNome(resposta.getProperty("nome").toString());
            //user.setIdade(Integer.parseInt(resposta.getProperty("idade").toString()));


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public boolean inserirUsuario(Usuario usuario){

        return true;
    }

    public boolean atualizarUsuario(Usuario usuario){

        return true;
    }

    public boolean excluirUsuario(Usuario usuario){

        return true;
    }


}
