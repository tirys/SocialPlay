package br.edu.fatecriopreto.projetoandoid;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Perfil extends ActionBarActivity {

    ImageView imgback;
    ImageView imgeditar;
    Integer idUser;

    //informações do usuario
    TextView txtNomeUser;
    TextView txtEmailUser;
    TextView txtLocalUser;
    TextView txtJogoPrefer;

    int id;
    String nome;
    String email;
    Usuario userPerfil = null;
    String local;
    String jogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        imgeditar = (ImageView)findViewById(R.id.imgEditarPerfil);
        imgback = (ImageView)findViewById(R.id.imgbackperfil);
        txtNomeUser = (TextView)findViewById(R.id.txtNomeUser);
        txtEmailUser = (TextView)findViewById(R.id.txtEmailUser);
        txtLocalUser = (TextView)findViewById(R.id.txtLocalUser);
        txtJogoPrefer = (TextView)findViewById(R.id.txtJogoPrefer);


        //coletando id do usuario para fazer busca de suas informações
        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idUser = param.getInt("idUsuario");


        //pegando as informações do usuario

        UsuarioDAO userLog = new UsuarioDAO();

            if (Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                userPerfil = userLog.buscarUsuarioPorId(idUser);
                id = userPerfil.getIdUsuario();
                nome = userPerfil.getNome();
                email = userPerfil.getEmail();
                local = userPerfil.getLocal();
                jogo = userPerfil.getJogo();

            }

        txtNomeUser.setText(nome);
        txtEmailUser.setText(email);

        if(local!="") {
            txtLocalUser.setText(local);
        }
        else{
            txtLocalUser.setText("Não especificado");
        }

        txtJogoPrefer.setText(jogo);

        //exibindo activity para edicao
        imgeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Perfil.this,PerfilEditar.class);

                //enviando informações do usuario para a outra activity
                Bundle param = new Bundle();
                param.putInt("id", id);
                param.putString("nome", nome);
                param.putString("email", email);
                param.putString("local", local);
                param.putString("jogo", jogo);
                intent2.putExtras(param);

                startActivity(intent2);
            }
        });

        //finalizando a activity
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil.this.finish();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
