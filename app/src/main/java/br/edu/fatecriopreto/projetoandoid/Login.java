package br.edu.fatecriopreto.projetoandoid;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.edu.fatecriopreto.projetoandoid.connection.DBadapter;


public class Login extends ActionBarActivity {

    ImageView ImgLogo;
    Button btnEntrar;
    Button btnCadastrar;
    EditText edtLogin;
    EditText edtSenha;
    int id;
    String nome;
    String email;
    String foto;
    DBadapter  dbAdapter;
    //String URL = "http://192.168.20.205:8080/WSSocialPlay/entity.usuarios";
    Usuario userexistente;
    Usuario userLogin = null;

    Animation animBounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pegando os objetos no layout
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        final Context context = getApplicationContext();
        final CharSequence textError = "Usuário ou Senha Incorretos!";
        final CharSequence textEmpty = "Usuário ou Senha Vazios!";
        final int duration = Toast.LENGTH_SHORT;


        ConnectivityManager connectivity = (ConnectivityManager)
        context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            // Se não existe nenhum tipo de conexão retorna false
            if (netInfo != null) {
                Toast toast = Toast.makeText(Login.this,"Post excluído com sucesso!",Toast.LENGTH_LONG);
                toast.show();
            }
        }

        ImgLogo = (ImageView) findViewById(R.id.ImgLogo);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarse);

        // load the animation
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        // set animation listener
        //animBounce.setAnimationListener(this);

        ImgLogo.startAnimation(animBounce);

        dbAdapter = new DBadapter(this);

        dbAdapter.open();
        userexistente = dbAdapter.getUsuario(1);
        dbAdapter.close();
        UsuarioDAO userLog = new UsuarioDAO();




        if (!userexistente.getSenha().isEmpty() && !userexistente.getUsuario().isEmpty()) {
            if (Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                //resposta[0] = String.valueOf(userLog.verificaLogin(user, pass));
                userLogin = userLog.verificaLogin(userexistente.getUsuario(), userexistente.getSenha());
                id = userLogin.getIdUsuario();
                nome = userLogin.getNome();
                email = userLogin.getEmail();
                foto = userLogin.getImagem();

            }
            if (userLogin != null) {
                Intent intent = new Intent(Login.this, Main.class);
                Bundle param = new Bundle();
                param.putInt("idUsuario", id);
                param.putString("nomeUsuario", nome);
                param.putString("emailUsuario", email);
                param.putString("fotoUsuario", foto);
                intent.putExtras(param);
                startActivity(intent);
            }

        }


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsuarioDAO userLog = new UsuarioDAO();
                String user = edtLogin.getText().toString();
                String pass = edtSenha.getText().toString();

                //txtError.setError(null);

                if (!user.isEmpty() && !pass.isEmpty()) {

                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        //resposta[0] = String.valueOf(userLog.verificaLogin(user, pass));
                        userLogin = userLog.verificaLogin(user, pass);
                        id = userLogin.getIdUsuario();
                        nome = userLogin.getNome();
                        email = userLogin.getEmail();
                        foto = userLogin.getImagem();
                    }
                    if (userLogin != null) {

                        dbAdapter.open();
                        dbAdapter.editar(1,user,pass,"","","","","");
                        dbAdapter.close();

                        Intent intent = new Intent(Login.this, Main.class);
                        Bundle param = new Bundle();
                        param.putInt("idUsuario", id);
                        param.putString("nomeUsuario", nome);
                        param.putString("emailUsuario", email);
                        param.putString("fotoUsuario", foto);
                        intent.putExtras(param);
                        startActivity(intent);
                    }else{
                        Toast toast = Toast.makeText(context, textError, duration);
                        toast.show();
                        edtSenha.setText("");
                    }
                }else{
                    Toast toast = Toast.makeText(context, textEmpty, duration);
                    toast.show();
                }
            }

        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, Registrar.class);
                startActivityForResult(intent2,1);
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
