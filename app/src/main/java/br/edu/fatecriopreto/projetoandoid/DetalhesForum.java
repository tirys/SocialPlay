package br.edu.fatecriopreto.projetoandoid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import br.edu.fatecriopreto.projetoandoid.Entity.Categorias;
import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.adapter.AutoCompleteAdapter;
import br.edu.fatecriopreto.projetoandoid.adapter.GridCatAdaptercons;
import br.edu.fatecriopreto.projetoandoid.adapter.LstComentariosAdapter;
import br.edu.fatecriopreto.projetoandoid.domain.State;
import br.edu.fatecriopreto.projetoandoid.webservice.ComentariosDAO;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DetalhesForum extends ActionBarActivity {
    String titForum;
    String descricao;
    TextView txtTitDet;
    TextView txtDesc;
    EditText edtComment;
    int iduser;
    ImageView enviarComentario;

    int idpost;
    ListView lstComentarios;
    ImageView imgback;

    //alert
    private AlertDialog alerta;

    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    private int mPositionClicked;


    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(DetalhesForum.this, "onCheckedChanged: "+( b ? "true" : "false" ), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_forum);

        final Context context = getApplicationContext();
        final CharSequence textEmpty = "Há campos preenchidos incorretamente!";
        final CharSequence textSucess = "Comentário inserido com sucesso";
        final CharSequence textError = "Erro ao efetuar Cadastro!";

        final int duration = Toast.LENGTH_LONG;

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //força hidden no teclado ao startar a activity

        txtTitDet = (TextView) findViewById(R.id.txtTitDet);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        lstComentarios = (ListView) findViewById(R.id.lstComentarios);
        enviarComentario = (ImageView) findViewById(R.id.enviarComentario);
        edtComment = (EditText) findViewById(R.id.edtComment);

        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idpost = param.getInt("idPost");
        titForum = param.getString("titPost");
        descricao = param.getString("txtDesc");
        iduser = param.getInt("iduser");

        txtTitDet.setText(titForum);
        txtDesc.setText(descricao);


        ComentariosDAO listacom = new ComentariosDAO();

        final List<Comentarios> lstcomentarios = listacom.listarComentariosporid(idpost);



     //   List<Comentarios> lstcomentarios=listacom.listarComentariosporid(idpost);
        enviarComentario.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {

                String comentario = edtComment.getText().toString();

                if(!comentario.isEmpty()) {

                    if (Build.VERSION.SDK_INT > 9) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        ComentariosDAO dao = new ComentariosDAO();
                        boolean resultado = dao.inserirComentario(new Comentarios(1,iduser,comentario,"25/05/2015",idpost));

                        if(resultado == true){
                            Toast toast = Toast.makeText(context, textSucess, duration);
                            toast.show();

                           edtComment.setText("");
                            //atualizando a lista de comentarios
                            ComentariosDAO listacom = new ComentariosDAO();
                            lstcomentarios.clear();
                            lstcomentarios.addAll(listacom.listarComentariosporid(idpost));
                            lstComentarios.setAdapter(new LstComentariosAdapter(DetalhesForum.this, lstcomentarios));



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

        lstComentarios.setAdapter(new LstComentariosAdapter(this, lstcomentarios));
        // NAVIGATIOn DRAWER
        // END - RIGHT

        //se o usuario clicar e segurar em seu proprio comentario
        lstComentarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               final Comentarios comm = (Comentarios) parent.getItemAtPosition(position); //codigo para cast

                if (iduser==comm.getIdpessoa()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetalhesForum.this);

                    builder.setTitle("");
                    builder.setMessage("Tem certeza de que deseja excluir este comentário?");

                    builder.setPositiveButton("EXCLUIR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            ComentariosDAO commDAO = new ComentariosDAO();
                           boolean oi = commDAO.excluirComentario(comm.getId());
                            if(oi==true){
                            lstcomentarios.clear();
                            lstcomentarios.addAll(commDAO.listarComentariosporid(idpost));
                            lstComentarios.setAdapter(new LstComentariosAdapter(DetalhesForum.this, lstcomentarios));
                            Toast.makeText(DetalhesForum.this, "Comentário excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(DetalhesForum.this, "Erro ao excluir o comentário", Toast.LENGTH_SHORT).show();
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

        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.ffxv)
                .addProfiles(
                        new ProfileDrawerItem().withName("Jéssica").withEmail("jessica.j.costa@gmail.com").withIcon(getResources().getDrawable(R.drawable.jessica))
                        //new ProfileDrawerItem().withName("Person Two").withEmail("person2@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_2)),
                        //new ProfileDrawerItem().withName("Person Three").withEmail("person3@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_3)),
                        //new ProfileDrawerItem().withName("Person Four").withEmail("person4@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_4))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                        Toast.makeText(DetalhesForum.this, "onProfileChanged: " + iProfile.getName(), Toast.LENGTH_SHORT).show();
                        headerNavigationLeft.setBackgroundRes(R.drawable.xv2);
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationLeft)
                    /*.withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                        @Override
                        public boolean onNavigationClickListener(View view) {
                            return false;
                        }
                    })*/
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size(); count < tam; count++) {
                            if (count == mPositionClicked && mPositionClicked <= 3) {
                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count);
                                aux.setIcon(getResources().getDrawable( getCorretcDrawerIcon( count, false ) ));
                                break;
                            }
                        }

                        if(i <= 3){
                            ((PrimaryDrawerItem) iDrawerItem).setIcon(getResources().getDrawable( getCorretcDrawerIcon( i, true ) ));
                        }

                        mPositionClicked = i;
                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(DetalhesForum.this, "onItemLongClick: " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Inicio").withIcon(getResources().getDrawable(R.drawable.car_selected_1)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Perfil").withIcon(getResources().getDrawable(R.drawable.car_2)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Jogos").withIcon(getResources().getDrawable(R.drawable.car_3)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Amigos").withIcon(getResources().getDrawable(R.drawable.car_4)));
        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Configurações"));
        navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notificação").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));


        //voltando para main quando clicar no icone de voltar
        imgback = (ImageView)findViewById(R.id.imgbackperfil);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent voltar = new Intent(Perfil.this,Main.class);
                //   startActivityForResult(voltar,1);
                // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                DetalhesForum.this.finish();

            }
        });
    }

    private int getCorretcDrawerIcon(int position, boolean isSelecetd){
        switch(position){
            case 0:
                return( isSelecetd ? R.drawable.car_selected_1 : R.drawable.car_1 );
            case 1:
                return( isSelecetd ? R.drawable.car_selected_2 : R.drawable.car_2 );
            case 2:
                return( isSelecetd ? R.drawable.car_selected_3 : R.drawable.car_3 );
            case 3:
                return( isSelecetd ? R.drawable.car_selected_4 : R.drawable.car_4 );
        }
        return(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = navigationDrawerLeft.saveInstanceState(outState);
        outState = headerNavigationLeft.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(navigationDrawerLeft.isDrawerOpen()){
            navigationDrawerLeft.closeDrawer();
        }
        else{
            super.onBackPressed();
        }
    }
}
