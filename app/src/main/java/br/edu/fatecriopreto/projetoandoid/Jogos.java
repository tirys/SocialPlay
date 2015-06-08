package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Jogos extends ActionBarActivity {
    ImageView pc;
    ImageView console;
    ImageView mobile;
    ImageView portatil;
    int idPlataformaPC;
    int idPlataformaConsole;
    int idPlataformaMobile;
    int idPlataformaPortatil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogos);

        pc = (ImageView) findViewById(R.id.imgpc);
        console = (ImageView) findViewById(R.id.imgcon);
        mobile = (ImageView) findViewById(R.id.imgmob);
        portatil = (ImageView) findViewById(R.id.imgport);
        idPlataformaPC = 1;
        idPlataformaConsole = 2;
        idPlataformaMobile = 3;
        idPlataformaPortatil = 4;

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pc = new Intent(Jogos.this, Catpc.class);
                Bundle param = new Bundle();
                param.putInt("idPlataformaPC", idPlataformaPC);
                pc.putExtras(param);
                startActivityForResult(pc, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);
            }
        });

        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent console = new Intent(Jogos.this, Catcon.class);
                Bundle param = new Bundle();
                param.putInt("idPlataformaConsole", idPlataformaConsole);
                console.putExtras(param);
                startActivityForResult(console, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mobile = new Intent(Jogos.this, Catmob.class);
                Bundle param = new Bundle();
                param.putInt("idPlataformaMobile", idPlataformaMobile);
                mobile.putExtras(param);
                startActivityForResult(mobile, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);
            }
        });
        portatil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portatil = new Intent(Jogos.this, catport.class);
                Bundle param = new Bundle();
                param.putInt("idPlataformaPortatil", idPlataformaPortatil);
                portatil.putExtras(param);
                startActivityForResult(portatil, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);
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
