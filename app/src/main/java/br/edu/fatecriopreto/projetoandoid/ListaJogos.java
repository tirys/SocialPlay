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
import br.edu.fatecriopreto.projetoandoid.Entity.DescJogo;
import br.edu.fatecriopreto.projetoandoid.Entity.Jogos;
import br.edu.fatecriopreto.projetoandoid.adapter.ListaJogosAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.*;


public class ListaJogos extends Activity {

    ListView lstjogos;
    int IdCategoria;
    int idPlataforma;
    int idUsuario;
    String fotoUsuario;
    String nomeUser;
    String emailUser;
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
        idUsuario = param.getInt("idUsuario");
        fotoUsuario = param.getString("fotoUsuario");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");

        //plataforma  = param.getString("plataforma");
        List<Games> listaJogos = dao.buscarJogosPorGenero(IdCategoria,idPlataforma);
        lstjogos.setAdapter(new ListaJogosAdapter(this,listaJogos));


        lstjogos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Games jogo=  (Games) parent.getItemAtPosition(position); //codigo para cast

                //pegando todas as informações necessárias
                br.edu.fatecriopreto.projetoandoid.webservice.DescJogo dao = new br.edu.fatecriopreto.projetoandoid.webservice.DescJogo();
                List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> lista = dao.buscaTodosJogos(jogo.getIdJogo());


                Intent intentdesc = new Intent(ListaJogos.this, br.edu.fatecriopreto.projetoandoid.DescJogo.class);

                Bundle param2 = new Bundle();

                param2.putInt("JogoId",lista.get(0).getIdDescJogo());
                param2.putString("JogoNome", lista.get(0).getDescJogo());
                param2.putString("JogoGenero",lista.get(0).getDescGenero());
                param2.putString("JogoFoto",lista.get(0).getDescImgByte());
                param2.putString("JogoDescricao",lista.get(0).getDescDescricao());
                param2.putString("JogoPlataforma",lista.get(0).getDescPlataforma());
                param2.putString("JogoProdutora",lista.get(0).getDescProdutora());
                param2.putInt("JogoAno",lista.get(0).getDescAno());
                param2.putInt("idUser",idUsuario);
                param2.putString("nomeUsuario",nomeUser);
                param2.putString("emailUsuario",emailUser);
                param2.putString("fotoUsuario",fotoUsuario);


                intentdesc.putExtras(param2);

                startActivity(intentdesc);

            }
        });


    }

}
