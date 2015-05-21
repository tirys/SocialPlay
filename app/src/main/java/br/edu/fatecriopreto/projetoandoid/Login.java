package br.edu.fatecriopreto.projetoandoid;


import android.content.Intent;
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
import android.widget.TextView;


public class Login extends ActionBarActivity {

    ImageView ImgLogo;
    Button btnEntrar;
    Button btnCadastrar;
    EditText edtLogin;
    EditText edtSenha;
    TextView txtError;

    String URL = "http://192.168.20.205:8080/WSSocialPlay/entity.usuarios";

    Usuario userLogin = null;

    Animation animBounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pegando os objetos no layout
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        ImgLogo = (ImageView) findViewById(R.id.ImgLogo);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarse);

        // load the animation
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        // set animation listener
        //animBounce.setAnimationListener(this);

        ImgLogo.startAnimation(animBounce);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*UsuarioDAO userLog = new UsuarioDAO();
                String user = edtLogin.getText().toString();
                String pass = edtSenha.getText().toString();
                final String[] resposta = {""};

                txtError.setError(null);

                if (!user.isEmpty() && !pass.isEmpty()) {

                    if(Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        //chama o webservice
                        //resposta[0] = String.valueOf(userLog.verificaLogin(user, pass));

                        userLogin = userLog.verificaLogin(user,pass);
                    }
                    if(userLogin != null){*/
                        Intent intent = new Intent(Login.this, Main.class);
                        startActivity(intent);
                    }
                /*}
            }*/
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
