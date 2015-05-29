package br.edu.fatecriopreto.projetoandoid;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Lucas Fernandes on 18/05/2015.
 */
public class UsuarioDAO {

    //private static final String URL = "http://192.168.20.205:8080/SocialPlay/services/UsuarioDAO?wsdl";
    //private static final String URL = "http://10.0.2.2:8080/SocialPlay/services/UsuarioDAO?wsdl";
    private static final String URL = "http://192.168.1.105:8080/SocialPlay/services/UsuarioDAO?wsdl";
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
            user.setIdUsuario(Integer.parseInt(resposta.getProperty("idUsuario").toString()));
            user.setNome(resposta.getProperty("nome").toString());
            user.setEmail(resposta.getProperty("email").toString());
            //user.setImagem(resposta.getProperty("imagem").toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public boolean inserirUsuario(Usuario usuario){

        SoapObject inserirUsuario = new SoapObject(NAMESPACE, INSERIR);
        SoapObject usr = new SoapObject(NAMESPACE, "usuario");
        //usr.addProperty("idUsuario", usuario.getIdUsuario());
        usr.addProperty("usuario", usuario.getUsuario());
        usr.addProperty("senha",usuario.getSenha());
        usr.addProperty("email", usuario.getEmail());
        usr.addProperty("nome", usuario.getNome());
        usr.addProperty("idade", usuario.getIdade());
        //usr.addProperty("imagem", usuario.getImagem());
        inserirUsuario.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(inserirUsuario);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);
        try {
            http.call("urn" + INSERIR, envelope);
            //Pegar Resposta do WebServices, ele retorna true or false
            SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarUsuario(Usuario usuario){

        return true;
    }

    public boolean excluirUsuario(Usuario usuario){

        return true;
    }


}
