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
import br.edu.fatecriopreto.projetoandoid.Entity.Genero;
import br.edu.fatecriopreto.projetoandoid.adapter.GridCatAdaptercons;
import br.edu.fatecriopreto.projetoandoid.adapter.GridCatAdaptermob;
import br.edu.fatecriopreto.projetoandoid.webservice.GeneroDAO;

/**
 * Created by Jessica on 23/05/2015.
 */
public class Catmob extends ActionBarActivity {
    GridView grid;
    TextView myImageViewText;
    int idPlataforma;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.genpcgrid);

        final GridView grid = (GridView) findViewById(R.id.gvCategoria);

        GeneroDAO dao = new GeneroDAO();
        //Pega o id da Plataforma
        final Intent intent = getIntent();
        Bundle param = intent.getExtras();
        idPlataforma = param.getInt("idPlataformaMobile");

        //Cria a Lista de Generos da plataforma selecionada
        ArrayList<Genero> listaGenero = (ArrayList<Genero>) dao.buscaGenero(idPlataforma);
        grid.setAdapter(new GridCatAdaptermob(this, listaGenero));


      //  grid.setAdapter(new GridCatAdaptermob(this, categoria));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Genero genero =  (Genero) parent.getItemAtPosition(position); //codigo para cast
                Intent pc= new Intent(Catmob.this, ListaJogos.class);

                Bundle param = new Bundle();
                param.putInt("idCategoria", genero.getIdGenero());
                param.putInt("idPlataforma",idPlataforma);
                //param.putString("plataforma","pc");
                pc.putExtras(param);

                startActivityForResult(pc, 1);
                overridePendingTransition(R.anim.animacao1, R.anim.animacao2);

            }
        });
    }
}
