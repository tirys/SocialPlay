package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.adapter.LstComentariosAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.ComentariosDAO;

/**
 * Created by Jessica on 05/06/2015.
 */
public class ListaPostsJogo extends ActionBarActivity {

    ListView listapostagem;
    int JogoId;
    int idUser;
    ImageView imgbackListagem;

    String nomeUser;
    String emailUser;
    String fotoUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listando_posts_jogo);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        JogoId = param.getInt("idJogo");
        idUser = param.getInt("idUser");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");
        fotoUser = param.getString("fotoUsuario");


        listapostagem = (ListView)findViewById(R.id.lstPostJogo);
        imgbackListagem = (ImageView)findViewById(R.id.imgbackListagem);

        TopicosDAO lista = new TopicosDAO();

        final List<Topicos> topicos = lista.listarPostsporId(JogoId);

        listapostagem.setAdapter(new AdapterListView(this, topicos));

        //ao clicar em um topico
        listapostagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topicos post = (Topicos) parent.getItemAtPosition(position); //codigo para cast

                Intent intent = new Intent(ListaPostsJogo.this, DetalhesForum.class);

                /*Bundle param = new Bundle();
                param.putInt("idPost", post.getIdTopico());
                param.putString("titPost", post.getNome());
                param.putString("txtDesc",post.getDescricao());
                param.putInt("codUsuario",post.getCodUsuario());
                param.putInt("iduser",idUser);
                param.putString("nomeUsuario", nomeUser);
                param.putString("emailUsuario", emailUser);
                param.putString("fotoUsuario",fotoUser);
                intent.putExtras(param);*/

                Bundle param = new Bundle();
                param.putInt("idPost", post.getIdTopico());
                param.putString("titPost", post.getNome());
                param.putString("txtDesc",post.getDescricao());
                param.putInt("codUsuario",post.getCodUsuario());
                param.putInt("iduser",idUser);
                param.putString("nomeUsuario", post.getNomeUser());
                param.putString("emailUsuario", emailUser);
                param.putString("nomeMenu", nomeUser);
                param.putString("fotoMenu",fotoUser);
                param.putString("fotoUsuario",post.getImagem());
                intent.putExtras(param);

                startActivityForResult(intent, 1);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        //voltando a activity
        imgbackListagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ListaPostsJogo.this.finish();
            }
        });


    }
}
