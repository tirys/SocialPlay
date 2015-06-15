package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Perfil extends ActionBarActivity {

    ImageView imgback;
    ImageView imgeditar;
    ImageView imgpessoa;
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
    String fotoUser;

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
        imgpessoa = (ImageView)findViewById(R.id.imgpc);


        //coletando id do usuario para fazer busca de suas informações
        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idUser = param.getInt("idUsuario");
        fotoUser = param.getString("fotoUsuario");

        byte[] bt = Base64.decode(fotoUser, Base64.DEFAULT);
        Bitmap fotousuario = BitmapFactory.decodeByteArray(bt, 0, bt.length);
        imgpessoa.setImageBitmap(fotousuario);


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
                param.putString("fotoUsuario",fotoUser);
                intent2.putExtras(param);

                startActivity(intent2);


            }
        });

        //finalizando a activity
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(Perfil.this,Main.class);
                Bundle param = new Bundle();
                param.putInt("idUsuario",id);
                param.putString("nomeUsuario",nome);
                param.putString("emailUsuario",email);
                param.putString("fotoUsuario",fotoUser);
                voltar.putExtras(param);


                startActivity(voltar);



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
