package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.adapter.AutoCompleteAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.ComentariosDAO;

/**
 * Created by Jéssica on 04/06/2015.
 */
public class NovoPost extends ActionBarActivity {

    EditText edtTitulo;
    EditText edtJogo;
    EditText edtConteudo;
    public EditText edtIdJogo;
    public AutoCompleteTextView actvPostagem;

    int idUser;
    String jogoid;
    String nomeUser;
    String emailUser;
    String nomejogo;
    String fotoUser;

    Button btnCadastrar;
    Button btnCancelar;

    //public AutoCompleteTextView actvState;
    public static int id = 0;

    // STATES
    AutocompleteDAO dao = new AutocompleteDAO();
    private List<Autocomplete> listJogos = dao.buscaTodosJogos();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_postagem);
        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Post inserido com sucesso";
        final CharSequence textError = "Erro ao efetuar Cadastro!";

        final int duration = Toast.LENGTH_LONG;
        //atribuindo item do layout
        edtTitulo = (EditText)findViewById(R.id.edtTituloPost);
        edtJogo = (EditText)findViewById(R.id.actvJogoPost);
        edtConteudo = (EditText)findViewById(R.id.edtConteudoPost);
        edtIdJogo = (EditText)findViewById(R.id.edtIdJogo);

        btnCadastrar = (Button)findViewById(R.id.btnSalvarPost);
        btnCancelar = (Button)findViewById(R.id.btnCancelarPost);

        final Intent intentpost = getIntent();
        Bundle param = intentpost.getExtras();

        idUser = param.getInt("idUsuario");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");
        fotoUser = param.getString("fotoUsuario");

        if(!param.getString("nomeJogo").isEmpty() && param.getInt("idJogo")!=0){
            nomejogo = param.getString("nomeJogo");
            jogoid = String.valueOf(param.getInt("idJogo"));

            edtJogo.setText(nomejogo);
            edtIdJogo.setText(jogoid);
        }
        else
        {
            nomejogo="";
            jogoid="";
        }


        //cadastrar post
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String titulo = edtTitulo.getText().toString();
            String jogostring= edtIdJogo.getText().toString();


         //   int jogo = Integer.parseInt(edtJogo.getText().toString());
            String conteudo = edtConteudo.getText().toString();



               if(!titulo.isEmpty()  && !conteudo.isEmpty() && !jogostring.isEmpty()) {
                   final int jogo = Integer.parseInt(jogostring);


                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        ComentariosDAO comm = new ComentariosDAO();
                        TopicosDAO dao = new TopicosDAO();
                        boolean resultado = dao.inserirTopicos(new Topicos(0,titulo," "," ",conteudo,jogo,idUser, ""));

                        if(resultado == true){
                            Toast toast = Toast.makeText(context, textSucess, duration);
                            toast.show();

                            //gambiarra pra inserir 2 comentarios
                            TopicosDAO dao2 = new TopicosDAO();
                            List<Topicos> lista = dao2.buscaTodosTopicos();
                         //   int tamanho = lista.size();
                          //  int idpostagem = lista.get(tamanho-1).getIdTopico();




                            Intent intent = new Intent(NovoPost.this, Main.class);

                            Bundle param = new Bundle();
                            param.putInt("idUsuario", idUser);
                            param.putString("nomeUsuario", nomeUser);
                            param.putString("emailUsuario", emailUser);
                            param.putString("fotoUsuario", fotoUser);
                            intent.putExtras(param);

                            startActivityForResult(intent,1);
                        }else {
                            Toast toast = Toast.makeText(context, textError, duration);
                            toast.show();
                        }
                    }
                }else{
                    Toast toast = Toast.makeText(context, textEmpty, duration);
                    toast.show();
                }

            }
        });

        //cancelar post
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NovoPost.this.finish();
            }
        });

        // AUTO COMPLETE TEXT VIEW
        int position = 0;
        //actvState = new AutoCompleteTextView(NovoPost.this);
        actvPostagem = (AutoCompleteTextView) findViewById(R.id.actvJogoPost);
        actvPostagem.setThreshold(1);
        actvPostagem.setBackgroundColor(0);
        //float scale = getResources().getDisplayMetrics().density;
        //actvPostagem.setPadding((int)(5 * scale * 0.5f),(int)(4 * scale * 0.5f),(int)(5 * scale * 0.5f),(int)(0 * scale * 0.5f));
        //actvState.setHint("Estado / Provincia");
        //actvState = (AutoCompleteTextView) findViewById(R.id.actvState);
        actvPostagem.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Integer cor = actvPostagem.getCurrentTextColor();
                Log.i("Script", "beforeTextChanged(" + s + ")" + cor + "");
                NovoPost.id = 0;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("Script", "onTextChanged("+s+")");
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.i("Script", "afterTextChanged("+s+")");
            }
        });

        //llAddress.addView(actvState);
        actvPostagem.setTextColor(Color.parseColor("#96141414"));
        List<Autocomplete> aux = position == 0 ? listJogos : listJogos;
        actvPostagem.setAdapter(new AutoCompleteAdapter(NovoPost.this, position, aux));
    }
}
