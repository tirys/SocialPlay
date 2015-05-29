package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Categorias;
import br.edu.fatecriopreto.projetoandoid.adapter.GridCatAdaptercons;
import br.edu.fatecriopreto.projetoandoid.adapter.GridCatAdaptermob;

/**
 * Created by Jessica on 23/05/2015.
 */
public class Catmob extends ActionBarActivity {
    GridView grid;
    TextView myImageViewText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.genpcgrid);

        final GridView grid = (GridView) findViewById(R.id.gvCategoria);

        Categorias categoria1 = new Categorias(1, "Ação");
        Categorias categoria2 = new Categorias(2, "Arcade");
        Categorias categoria3 = new Categorias(3, "Aventura");
        Categorias categoria4 = new Categorias(4, "Cartas");
        Categorias categoria5 = new Categorias(5, "Casual");


        List<Categorias> categoria = new ArrayList<>();
        categoria.add(categoria1);
        categoria.add(categoria2);
        categoria.add(categoria3);
        categoria.add(categoria4);
        categoria.add(categoria5);


        grid.setAdapter(new GridCatAdaptermob(this, categoria));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categorias categoria = (Categorias) parent.getItemAtPosition(position); //codigo para cast
                Intent pc= new Intent(Catmob.this, ListaJogos.class);

                Bundle param = new Bundle();
                param.putLong("idCat", categoria.getId());
                param.putString("plataforma","mob");
                pc.putExtras(param);

                startActivityForResult(pc, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);

            }
        });
    }
}
