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


public class ListaJogos extends Activity {

    ListView lstjogos;
    Long IdCategoria;
    String plataforma;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listajogos);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        IdCategoria = param.getLong("idCat");
        plataforma  = param.getString("plataforma");


        final ListView lstjogos = (ListView) findViewById(R.id.lstJogoscat);

        br.edu.fatecriopreto.projetoandoid.Entity.Jogos jogo1 = new Jogos(1,"Jogo1 categoria: "+IdCategoria+"p: "+plataforma+"","descricao1","produtora1",1995);
        br.edu.fatecriopreto.projetoandoid.Entity.Jogos jogo2 = new Jogos(2,"Jogo2","descricao2","produtora2",1995);
        br.edu.fatecriopreto.projetoandoid.Entity.Jogos jogo3 = new Jogos(3,"Jogo3","descricao3","produtora3",1995);
        br.edu.fatecriopreto.projetoandoid.Entity.Jogos jogo4 = new Jogos(4,"Jogo4","descricao4","produtora4",1995);

        List<Jogos> games = new ArrayList<>();
        games.add(jogo1);
        games.add(jogo2);
        games.add(jogo3);
        games.add(jogo4);

        lstjogos.setAdapter(new ListaJogosAdapter(this,games));

        lstjogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Jogos jogo=  (Jogos) parent.getItemAtPosition(position); //codigo para cast


            }
        });


    }

}
