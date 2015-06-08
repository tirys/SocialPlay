package br.edu.fatecriopreto.projetoandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.*;
import br.edu.fatecriopreto.projetoandoid.Entity.Jogos;
import br.edu.fatecriopreto.projetoandoid.adapter.ListaJogosAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.GamesDAO;


public class ListaJogos extends Activity {

    ListView lstjogos;
    int IdCategoria;
    int idPlataforma;
    //String plataforma;

    GamesDAO dao = new GamesDAO();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listajogos);

        final ListView lstjogos = (ListView) findViewById(R.id.lstJogoscat);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();
        IdCategoria = param.getInt("idCategoria");
        idPlataforma = param.getInt("idPlataforma");

        //plataforma  = param.getString("plataforma");
        List<Games> listaJogos = dao.buscarJogosPorGenero(IdCategoria,idPlataforma);
        lstjogos.setAdapter(new ListaJogosAdapter(this,listaJogos));


        lstjogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Games jogo=  (Games) parent.getItemAtPosition(position); //codigo para cast
            }
        });


    }

}
