package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jessica on 06/06/2015.
 */
public class EditarForum extends ActionBarActivity {

    Button btnExcluirPost;

    EditText editarTituloPost;
    EditText editarConteudoPost;

    ImageView imgEditarForum2;
    ImageView imgVoltarEditarForum;

    int idpost;
    int iduser;
    int idautor;

    String titForum;
    String descricao;
    String novotitulo;
    String novoconteudo;
    String nomeUser;
    String emailUser;
    String fotoUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_forum);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idpost = param.getInt("idPost");
        titForum = param.getString("titPost");
        descricao = param.getString("descricao");
        iduser = param.getInt("iduser");
        idautor = param.getInt("idautor");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");
        fotoUser = param.getString("fotoUsuario");

        btnExcluirPost = (Button)findViewById(R.id.btnExcluirPost);
        editarConteudoPost = (EditText)findViewById(R.id.editarConteudoPost);
        editarTituloPost = (EditText)findViewById(R.id.editarTituloPost);
        imgEditarForum2 = (ImageView)findViewById(R.id.imgEditarForum2);
        imgVoltarEditarForum = (ImageView)findViewById(R.id.imgVoltarEditarForum);

       editarTituloPost.setText(titForum);
       editarConteudoPost.setText(descricao);

        imgEditarForum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicosDAO topdao = new TopicosDAO();

                novotitulo=editarTituloPost.getText().toString();
                novoconteudo=editarConteudoPost.getText().toString();

                if(!novotitulo.isEmpty() && !novoconteudo.isEmpty()){
                   boolean resultado = topdao.atualizarTopico(idpost,novotitulo,novoconteudo);
                   if (resultado){
                       Toast toast = Toast.makeText(EditarForum.this,"Post atualizado com sucesso!",Toast.LENGTH_LONG);
                       toast.show();

                       Intent intent3 = new Intent(EditarForum.this,DetalhesForum.class);
                       Bundle param = new Bundle();

                       param.putInt("idPost", idpost);
                       param.putString("titPost", novotitulo);
                       param.putString("txtDesc", novoconteudo);
                       param.putInt("iduser", iduser);
                       param.putInt("codUsuario", idautor);
                       param.putString("nomeUsuario", nomeUser);
                       param.putString("emailUsuario", emailUser);
                       param.putString("fotoUsuario",fotoUser);

                       intent3.putExtras(param);

                       startActivity(intent3);
                   }
                    else
                   {
                       Toast toast = Toast.makeText(EditarForum.this,"Erro ao atualizar post",Toast.LENGTH_LONG);
                       toast.show();
                   }
                }
                else
                {
                    Toast toast = Toast.makeText(EditarForum.this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //excluir post
        btnExcluirPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicosDAO excluirtop = new TopicosDAO();

                boolean resultado2 = excluirtop.excluirTopico(idpost);
                if (resultado2)
                {
                    Toast toast = Toast.makeText(EditarForum.this,"Post excluído com sucesso!",Toast.LENGTH_LONG);
                    toast.show();

                    Intent voltar = new Intent(EditarForum.this,Main.class);
                    Bundle param = new Bundle();
                    param.putInt("idUsuario",iduser);
                    param.putString("nomeUsuario",nomeUser);
                    param.putString("emailUsuario",emailUser);
                    param.putString("fotoUsuario",fotoUser);
                    voltar.putExtras(param);


                    startActivity(voltar);
                }
                else
                {
                    Toast toast = Toast.makeText(EditarForum.this,"Erro ao excluir post",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //finalizando activity
        imgVoltarEditarForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(EditarForum.this,DetalhesForum.class);
                Bundle param = new Bundle();

                param.putInt("idPost", idpost);
                param.putString("titPost", titForum);
                param.putString("txtDesc", descricao);
                param.putInt("iduser", iduser);
                param.putInt("codUsuario", idautor);
                param.putString("nomeUsuario",nomeUser);
                param.putString("emailUsuario",emailUser);
                param.putString("fotoUsuario",fotoUser);

                intent2.putExtras(param);

                startActivity(intent2);

            }
        });


    }

}
