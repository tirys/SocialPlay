package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.fatecriopreto.projetoandoid.webservice.Registra;


public class Registrar extends ActionBarActivity{

    Button btnCadastrar;
    EditText edcadLogin;
    EditText edcadUsuario;
    EditText edcadSenha;
    EditText edcadEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_registrar);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        edcadLogin = (EditText) findViewById(R.id.edcadLogin);
        edcadUsuario = (EditText) findViewById(R.id.edcadUsuario);
        edcadSenha = (EditText) findViewById(R.id.edcadSenha);
        edcadEmail = (EditText) findViewById(R.id.edcadEmail);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edcadLogin.getText().toString();
                String usuario = edcadUsuario.getText().toString();
                String senha = edcadSenha.getText().toString();
                String email = edcadEmail.getText().toString();
                final String[] resposta = {""};

                if (!login.isEmpty() && !usuario.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                    resposta[0] = Registra.cadastraJson(login, usuario, email, senha, getApplicationContext());

                    Toast.makeText(getApplicationContext(),
                            resposta[0], Toast.LENGTH_LONG)
                            .show();
                }

            }
        });


    }
}

