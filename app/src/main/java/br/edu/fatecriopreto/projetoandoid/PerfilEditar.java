package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
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
    ImageView imgpessoa;
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
    String fotoUser;

    //informações novas
    String novonome;
    String novolocal;
    String novojogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_edt);

        //mensagens de erro
        final Context context = getApplicationContext();
        final CharSequence textEmpty = "O campo nome não pode ser vazio";
        final CharSequence textSucess = "Informações salvas com sucesso";
        final CharSequence textError = "Erro ao efetuar edição!";
        final int duration = Toast.LENGTH_LONG;

        back = (ImageView)findViewById(R.id.imgVoltarEditar);
        imgpessoa = (ImageView)findViewById(R.id.imgpc2);
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
        fotoUser = param.getString("fotoUsuario");

        byte[] bt = Base64.decode(fotoUser, Base64.DEFAULT);
        Bitmap fotousuario = BitmapFactory.decodeByteArray(bt, 0, bt.length);
        imgpessoa.setImageBitmap(fotousuario);


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

                   //se os campos forem vazios
                   if(novojogo.isEmpty()){
                       novojogo=" ";
                   }
                   if(novolocal.isEmpty()){
                       novolocal=" ";
                   }
                   //se o nome for vazio, mostrar erro
                    if (novonome.isEmpty()){
                        Toast toast = Toast.makeText(context, textEmpty, duration);
                        toast.show();
                    }
                   else{

                   boolean resultado = dao.atualizarUsuario(new Usuario(id, "", "", email, novonome, 0, null, novolocal, novojogo));
                   if (resultado == true) {
                       Toast toast = Toast.makeText(context, textSucess, duration);
                       toast.show();

                       Intent intent = new Intent(PerfilEditar.this, Perfil.class);
                       Bundle param = new Bundle();
                       param.putInt("idUsuario", id);
                        param.putString("fotoUsuario",fotoUser);

                       intent.putExtras(param);
                       startActivityForResult(intent, 1);

                   }
                   else if(resultado==false){
                       Toast toast = Toast.makeText(context, textError, duration);
                       toast.show();
                   }
               }}
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
