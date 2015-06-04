package br.edu.fatecriopreto.projetoandoid;

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

/**
 * Created by Jéssica on 04/06/2015.
 */
public class NovoPost extends ActionBarActivity {

    EditText edtTitulo;
    EditText edtJogo;
    EditText edtConteudo;

    Button btnCadastrar;
    Button btnCancelar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_postagem);

        //atribuindo item do layout
        edtTitulo = (EditText)findViewById(R.id.edtTituloPost);
        edtJogo = (EditText)findViewById(R.id.edtJogoPost);
        edtConteudo = (EditText)findViewById(R.id.edtConteudoPost);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrarPost);
        btnCancelar = (Button)findViewById(R.id.btnCancelarPost);

        //cadastrar post
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            String titulo = edtTitulo.getText().toString();
            String jogo = edtJogo.getText().toString();
            String conteudo = edtConteudo.getText().toString();


            @Override
            public void onClick(View v) {
             /*   if(!titulo.isEmpty() && !jogo.isEmpty() && !conteudo.isEmpty()) {

                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        TopicosDAO dao = new TopicosDAO();
                        boolean resultado = dao.inserirTopicos(new Topicos(0, usuario, senha, email, nome, 0, null,"",""));
                        if(resultado == true){
                            Toast toast = Toast.makeText(context, textSucess, duration);
                            toast.show();

                            Intent intent = new Intent(Registrar.this, Login.class);
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
            }*/
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
