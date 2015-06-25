package br.edu.fatecriopreto.projetoandoid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.adapter.LstComentariosAdapter;
import br.edu.fatecriopreto.projetoandoid.webservice.ComentariosDAO;

/**
 * Created by Jessica on 24/06/2015.
 */
public class ComentariosAct extends ActionBarActivity {

    int idpost;
    ListView lstComentarios;
    ImageView enviarComentario;
    EditText edtComment;
    int iduser;
    private AlertDialog alerta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comentarios);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idpost = param.getInt("idPost");
        iduser = param.getInt("idUser");

        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Comentário inserido com sucesso";
        final CharSequence textError = "Erro ao efetuar Cadastro!";
        final int duration = Toast.LENGTH_LONG;

        edtComment = (EditText) findViewById(R.id.edtComment);
        enviarComentario = (ImageView) findViewById(R.id.enviarComentario);
        lstComentarios = (ListView) findViewById(R.id.lstComentarios);



        ComentariosDAO listacom = new ComentariosDAO();

       final List<Comentarios> lstcomentarios = listacom.listarComentariosporid(idpost);
        lstComentarios.setAdapter(new LstComentariosAdapter(this, lstcomentarios));

        //List<Comentarios> lstcomentarios=listacom.listarComentariosporid(idpost);

      enviarComentario.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String comentario = edtComment.getText().toString();

                if(!comentario.isEmpty()) {

                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        ComentariosDAO dao = new ComentariosDAO();
                        boolean resultado = dao.inserirComentario(new Comentarios(1,iduser,comentario,"25/05/2015",idpost,"",""));

                        if(resultado == true){
                            Toast toast = Toast.makeText(context, textSucess, duration);
                            toast.show();

                           edtComment.setText("");
                            //atualizando a lista de comentarios
                            ComentariosDAO listacom = new ComentariosDAO();
                            lstcomentarios.clear();
                            lstcomentarios.addAll(listacom.listarComentariosporid(idpost));
                            lstComentarios.setAdapter(new LstComentariosAdapter(ComentariosAct.this, lstcomentarios));



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


        // NAVIGATIOn DRAWER
        // END - RIGHT

        //se o usuario clicar e segurar em seu proprio comentario
        lstComentarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               final Comentarios comm = (Comentarios) parent.getItemAtPosition(position); //codigo para cast

                if (iduser==comm.getIdpessoa()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ComentariosAct.this);

                    builder.setTitle("");
                    builder.setMessage("Tem certeza de que deseja excluir este comentário?");

                    builder.setPositiveButton("EXCLUIR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            ComentariosDAO commDAO = new ComentariosDAO();
                           boolean oi = commDAO.excluirComentario(comm.getId());
                            if(oi==true){
                            lstcomentarios.clear();
                            lstcomentarios.addAll(commDAO.listarComentariosporid(idpost));
                            lstComentarios.setAdapter(new LstComentariosAdapter(ComentariosAct.this, lstcomentarios));
                            Toast.makeText(ComentariosAct.this, "Comentário excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ComentariosAct.this, "Erro ao excluir o comentário", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(DetalhesForum.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                      });
                    alerta = builder.create();
                    alerta.show();
                    }

                return false;
            }
        });

    }
}
