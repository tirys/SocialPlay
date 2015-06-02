package br.edu.fatecriopreto.projetoandoid;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        imgback = (ImageView)findViewById(R.id.imgbackperfil);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent voltar = new Intent(Perfil.this,Main.class);
             //   startActivityForResult(voltar,1);
               // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
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
