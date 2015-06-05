package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jessica on 04/06/2015.
 */
public class DescJogo extends ActionBarActivity {

    int JogoId;
    String JogoNome;
    String JogoGenero;
    String JogoImagem;
    String ImagemByte;
    String JogoPlataforma;
    String JogoProdutora;
    String JogoAno;
    String JogoDescricao;
    TextView txtNome;
    TextView txtDescricao;
    TextView txtPlataforma;
    TextView txtProdutora;
    TextView txtAno;
    TextView txtGenero;
    ImageView imgFoto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_jogo);

        txtNome = (TextView) findViewById(R.id.tvNomeJogo);
        txtDescricao = (TextView) findViewById(R.id.tvDescJogo);
        txtPlataforma = (TextView) findViewById(R.id.tvPlataformaJogo);
        txtAno = (TextView) findViewById(R.id.tvAno);
        txtGenero = (TextView) findViewById(R.id.tvGenero);
        txtProdutora = (TextView) findViewById(R.id.tvProdutora);
        //txtGenero = (TextView) findViewById(R.id.);
        imgFoto = (ImageView) findViewById(R.id.imgFotoJogo);

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
    }

}
