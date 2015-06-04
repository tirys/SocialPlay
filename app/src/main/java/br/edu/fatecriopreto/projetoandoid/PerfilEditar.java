package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jéssica on 03/06/2015.
 */
public class PerfilEditar extends ActionBarActivity {

    ImageView back;
    ImageView salvar;
    EditText txtnome;
    EditText txtemail;
    EditText txtlocal;
    EditText txtjogo;



    //informações do usuario
    int id;
    String nome;
    String email;
    String local;
    String jogo;

    //informações novas
    String novonome;
    String novolocal;
    String novojogo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_edt);

        //mensagens de erro
        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Bem-Vindo ao SocialPlay!";
        final CharSequence textError = "Erro ao efetuar Cadastro!";
        final int duration = Toast.LENGTH_LONG;

        back = (ImageView)findViewById(R.id.imgVoltarEditar);
        txtnome = (EditText)findViewById(R.id.edtNomeEditar);
        txtemail = (EditText)findViewById(R.id.edtEmailEditar);
        txtlocal = (EditText)findViewById(R.id.edtLocalEditar);
        txtjogo = (EditText)findViewById(R.id.edtJogoEditar);
        salvar = (ImageView)findViewById(R.id.imgEditarPerfil);


        final Intent intent2 = getIntent();
        Bundle param = intent2.getExtras();

        id = param.getInt("id");
        nome = param.getString("nome");
        email = param.getString("email");
        local = param.getString("local");
        jogo = param.getString("jogo");


        txtnome.setText(nome);
        txtemail.setText(email);
        txtlocal.setText(local);
        txtjogo.setText(jogo);


        //atualizando
       salvar.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Build.VERSION.SDK_INT > 9) {
                   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                   StrictMode.setThreadPolicy(policy);

                   UsuarioDAO dao = new UsuarioDAO();

                   novonome=txtnome.getText().toString();
                   novolocal=txtlocal.getText().toString();
                   novojogo=txtjogo.getText().toString();

                   boolean resultado = dao.atualizarUsuario(new Usuario(id, "", "", email, novonome, 0, null, novolocal, novojogo));
                   if (resultado == true) {
                       Toast toast = Toast.makeText(context, textSucess, duration);
                       toast.show();

                       Intent intent = new Intent(PerfilEditar.this, Perfil.class);
                       Bundle param = new Bundle();
                       param.putInt("idUsuario", id);
                       intent.putExtras(param);
                       startActivityForResult(intent, 1);

                   }
               }
           }
       });


        //voltando a activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfilEditar.this.finish();

            }
        });
    }

    }
