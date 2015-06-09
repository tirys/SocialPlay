package br.edu.fatecriopreto.projetoandoid;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Games;
import br.edu.fatecriopreto.projetoandoid.adapter.ListaJogosAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.GamesDAO;

/**
 * Created by Jessica on 08/06/2015.
 */
public class PostsUsuario extends Activity {

    ListView lstjogos;
    int idUsuario;
    String fotoUsuario;
    ImageView imgbackListagemUsuario;
    String nomeUser;
    String emailUser;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TopicosDAO dao = new TopicosDAO();
        setContentView(R.layout.posts_usuario);

        final ListView lstposts = (ListView) findViewById(R.id.lstPostUsuario);
        imgbackListagemUsuario = (ImageView) findViewById(R.id.imgbackListagemUsuario);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();
        idUsuario = param.getInt("idUsuario");
        fotoUsuario = param.getString("fotoUsuario");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");


        List<Topicos> listaJogos = dao.listarPostsporUsuario(idUsuario);
        lstposts.setAdapter(new AdapterListView(this,listaJogos));


        imgbackListagemUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menIntent3 = new Intent(PostsUsuario.this, Main.class);
                Bundle param = new Bundle();

                param.putString("fotoUsuario",fotoUsuario);
                param.putInt("idUsuario", idUsuario);
                param.putString("nomeUsuario",nomeUser);
                param.putString("emailUsuario",emailUser);
                menIntent3.putExtras(param);

                startActivityForResult(menIntent3, 1);
            }
        });

        lstposts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topicos post=  (Topicos) parent.getItemAtPosition(position); //codigo para cast

                Intent intent = new Intent(PostsUsuario.this, DetalhesForum.class);

                Bundle param = new Bundle();
                param.putInt("idPost", post.getIdTopico());
                param.putString("titPost", post.getNome());
                param.putString("txtDesc",post.getDescricao());
                param.putInt("codUsuario",post.getCodUsuario());
                param.putString("nomeUsuario",nomeUser);
                param.putString("emailUsuario",emailUser);
                param.putString("fotoUsuario",fotoUsuario);

                param.putInt("iduser",idUsuario);
                intent.putExtras(param);

                startActivityForResult(intent, 1);
            }
        });
    }
}
