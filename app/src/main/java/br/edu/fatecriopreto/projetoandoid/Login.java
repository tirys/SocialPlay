package br.edu.fatecriopreto.projetoandoid;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Login extends ActionBarActivity {

    ImageView ImgLogo;
    Button btnEntrar;
    Button btnCadastrar;

    Animation animBounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //pegando os objetos no layout
        ImageView ImgLogo = (ImageView) findViewById(R.id.ImgLogo);
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrarse);

        // load the animation
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

        // set animation listener
        //animBounce.setAnimationListener(this);


        ImgLogo.startAnimation(animBounce);

        btnEntrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Main.class);
                startActivityForResult(intent,1);
                //startActivity();
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
