package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
import android.widget.TextView;


public class DetalhesForum extends ActionBarActivity {
    String titForum;
    String descricao;
    TextView txtTitDet;
    TextView txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_forum);

        txtTitDet = (TextView) findViewById(R.id.txtTitDet);
        txtDesc = (TextView) findViewById(R.id.txtDesc);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        titForum = param.getString("titPost");
        descricao = param.getString("txtDesc");
        txtTitDet.setText(titForum);
        txtDesc.setText(descricao);

    }
}
