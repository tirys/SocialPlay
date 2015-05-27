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
import android.widget.Toast;


public class Registrar extends ActionBarActivity{

    Button btnCadastrar;
    EditText edcadNome;
    EditText edcadUsuario;
    EditText edcadSenha;
    EditText edcadEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_registrar);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        edcadNome = (EditText) findViewById(R.id.edcadLogin);
        edcadUsuario = (EditText) findViewById(R.id.edcadUsuario);
        edcadSenha = (EditText) findViewById(R.id.edcadSenha);
        edcadEmail = (EditText) findViewById(R.id.edcadEmail);

        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Bem-Vindo ao SocialPlay!";
        final CharSequence textError = "Erro ao efetuar Cadastro!";
        final int duration = Toast.LENGTH_LONG;

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edcadNome.getText().toString();
                String usuario = edcadUsuario.getText().toString();
                String senha = edcadSenha.getText().toString();
                String email = edcadEmail.getText().toString();

                //final String[] resposta = {""};

                /*if (!login.isEmpty() && !usuario.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                    resposta[0] = Registra.cadastraJson(login, usuario, email, senha, getApplicationContext());

                    Toast.makeText(getApplicationContext(),
                            resposta[0], Toast.LENGTH_LONG)
                            .show();
                }*/
                //Rodar código de rede da thread principal da activity
                if(!nome.isEmpty() && !usuario.isEmpty() && !senha.isEmpty() && !email.isEmpty()) {

                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        UsuarioDAO dao = new UsuarioDAO();
                        boolean resultado = dao.inserirUsuario(new Usuario(0, usuario, senha, email, nome, 0, null));
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
            }
        });


    }
}

