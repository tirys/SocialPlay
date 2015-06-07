package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jessica on 04/06/2015.
 */
public class DescJogo extends ActionBarActivity {

    Button btnLstPostJogo;
    Button btnPostJogoo;
    int JogoId;
    int idUser;
    String nomeUser;
    String emailUser;
    String JogoNome;
    String JogoGenero;
    String JogoImagem;
    String ImagemByte;
    String JogoPlataforma;
    String JogoProdutora;
    String JogoAno;
    String JogoDescricao;
    String fotoUser;
    TextView txtNome;
    TextView txtDescricao;
    TextView txtPlataforma;
    TextView txtProdutora;
    TextView txtAno;
    TextView txtGenero;
    ImageView imgFoto;
    ImageView imgbackDescjogo;
    ImageView imgnovopostdescjogo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_jogo);

        btnLstPostJogo = (Button) findViewById(R.id.btnLstPostJogo);
        btnPostJogoo = (Button) findViewById(R.id.btnPostJogoo);
        txtNome = (TextView) findViewById(R.id.tvNomeJogo);
        txtDescricao = (TextView) findViewById(R.id.tvDescJogo);
        txtPlataforma = (TextView) findViewById(R.id.tvPlataformaJogo);
        txtAno = (TextView) findViewById(R.id.tvAno);
        txtGenero = (TextView) findViewById(R.id.tvGenero);
        txtProdutora = (TextView) findViewById(R.id.tvProdutora);
        //txtGenero = (TextView) findViewById(R.id.);
        imgFoto = (ImageView) findViewById(R.id.imgFotoJogo);
        imgbackDescjogo = (ImageView) findViewById(R.id.imgbackDescjogo);
        imgnovopostdescjogo = (ImageView) findViewById(R.id.imgnovopostdescjogo);


        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        JogoId = param.getInt("JogoId");
        String JogoId2 = Integer.toString(JogoId);
        JogoNome = param.getString("JogoNome");
        JogoGenero = param.getString("JogoGenero");
        JogoImagem = param.getString("JogoFoto");
        JogoPlataforma = param.getString("JogoPlataforma");
        JogoProdutora = param.getString("JogoProdutora");
        JogoAno = Integer.toString(param.getInt("JogoAno"));
        JogoDescricao = param.getString("JogoDescricao");
        idUser = param.getInt("idUser");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");
        fotoUser = param.getString("fotoUsuario");

        //ImagemByte = param.getString("JogoFoto");
        byte[] bt = Base64.decode(JogoImagem, Base64.DEFAULT);
        //String bt = listAux.get(position).getImgByte();
        Bitmap jogofoto = BitmapFactory.decodeByteArray(bt, 0, bt.length);
        txtNome.setText(JogoNome);
        txtPlataforma.setText(JogoPlataforma);
        txtDescricao.setText(JogoDescricao);
        txtProdutora.setText(JogoProdutora);
        imgFoto.setImageBitmap(jogofoto);
        txtGenero.setText(JogoGenero);
        txtAno.setText(JogoAno);
        //Bitmap jogofoto = BitmapFactory.decodeByteArray(JogoImagem, 0, JogoImagem.length);

        //Listar posts com o id do jogo
        btnLstPostJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DescJogo.this, ListaPostsJogo.class);
                Bundle param = new Bundle();
                param.putInt("idJogo", JogoId);
                param.putInt("idUser", idUser);
                intent.putExtras(param);
                startActivity(intent);
            }
        });

        btnPostJogoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DescJogo.this, NovoPost.class);
                Bundle param = new Bundle();
                param.putInt("idUsuario", idUser);
                param.putString("nomeUsuario", nomeUser);
                param.putString("emailUsuario", emailUser);
                param.putString("nomeJogo",JogoNome);
                param.putInt("idJogo",JogoId);
                param.putString("fotoUsuario",fotoUser);

                intent2.putExtras(param);
                startActivity(intent2);
            }
        });

        //novo post

        imgnovopostdescjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpost = new Intent(DescJogo.this,NovoPost.class);

                Bundle param = new Bundle();
                param.putInt("idUsuario", idUser);
                param.putString("nomeUsuario", nomeUser);
                param.putString("emailUsuario", emailUser);
                param.putString("nomeJogo", "");
                param.putInt("idJogo",0);
                param.putString("fotoUsuario",fotoUser);

                intentpost.putExtras(param);

                startActivity(intentpost);
            }
        });

        //voltando activity
        imgbackDescjogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescJogo.this.finish();
            }
        });

    }

}
