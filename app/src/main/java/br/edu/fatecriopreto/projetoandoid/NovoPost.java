package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.webservice.ComentariosDAO;

/**
 * Created by Jéssica on 04/06/2015.
 */
public class NovoPost extends ActionBarActivity {

    EditText edtTitulo;
    EditText edtJogo;
    EditText edtConteudo;

    int idUser;
    String nomeUser;
    String emailUser;

    Button btnCadastrar;
    Button btnCancelar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_postagem);
        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Post inserido com sucesso";
        final CharSequence textError = "Erro ao efetuar Cadastro!";

        final int duration = Toast.LENGTH_LONG;
        //atribuindo item do layout
        edtTitulo = (EditText)findViewById(R.id.edtTituloPost);
        edtJogo = (EditText)findViewById(R.id.edtJogoPost);
        edtConteudo = (EditText)findViewById(R.id.edtConteudoPost);

        btnCadastrar = (Button)findViewById(R.id.btnSalvarPost);
        btnCancelar = (Button)findViewById(R.id.btnCancelarPost);

        final Intent intentpost = getIntent();
        Bundle param = intentpost.getExtras();

        idUser = param.getInt("idUsuario");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");

        //cadastrar post
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String titulo = edtTitulo.getText().toString();
            String jogostring= edtJogo.getText().toString();


         //   int jogo = Integer.parseInt(edtJogo.getText().toString());
            String conteudo = edtConteudo.getText().toString();



               if(!titulo.isEmpty()  && !conteudo.isEmpty() && !jogostring.isEmpty()) {
                   final int jogo = Integer.parseInt(jogostring);


                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        ComentariosDAO comm = new ComentariosDAO();
                        TopicosDAO dao = new TopicosDAO();
                        boolean resultado = dao.inserirTopicos(new Topicos(0,titulo," "," ",conteudo,jogo,idUser));

                        if(resultado == true){
                            Toast toast = Toast.makeText(context, textSucess, duration);
                            toast.show();

                            //gambiarra pra inserir 2 comentarios
                            TopicosDAO dao2 = new TopicosDAO();
                            List<Topicos> lista = dao2.buscaTodosTopicos();
                            int tamanho = lista.size();
                            int idpostagem = lista.get(tamanho-1).getIdTopico();


                            boolean resultado2 = comm.inserirComentario(new Comentarios(1,1,"Fantasma","25/05/2015",idpostagem));
                            boolean resultado3 = comm.inserirComentario(new Comentarios(1,1,"Fantasma","25/05/2015",idpostagem));

                            Intent intent = new Intent(NovoPost.this, Main.class);

                            Bundle param = new Bundle();
                            param.putInt("idUsuario", idUser);
                            param.putString("nomeUsuario", nomeUser);
                            param.putString("emailUsuario", emailUser);
                            intent.putExtras(param);

                            startActivityForResult(intent,1);
                        }else {
                            Toast toast = Toast.makeText(context, textError, duration);
                            toast.show();
                        }
                    }
                }else{
                    Toast toast = Toast.makeText(context, textEmpty, duration);
                    toast.show();
                }

            }
        });

        //cancelar post
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NovoPost.this.finish();
            }
        });


    }
}
