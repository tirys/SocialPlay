package br.edu.fatecriopreto.projetoandoid;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import br.edu.fatecriopreto.projetoandoid.adapter.AutoCompleteAdapter;
import br.edu.fatecriopreto.projetoandoid.connection.DBadapter;
import br.edu.fatecriopreto.projetoandoid.domain.State;


public class Main extends ActionBarActivity implements OnItemSelectedListener {

    TextView txtUltimos;
    TextView txtSeguindo;
    TextView txtRelevantes;

    ImageView imgNewPost;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    String[] mDrawerListItems;
    int idUser;
    String nomeUser;
    String emailUser;

    //INICIA O ADAPTER
    SectionsPagerAdapter mSectionsPagerAdapter;
    Fragment fragmentozinho;
    //INICIA O PAGEVIEW
    ViewPager mViewPager;
    ListView lstUltimos;
    DBadapter dbAdapter;
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    private int mPositionClicked;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(Main.this, "onCheckedChanged: "+( b ? "true" : "false" ), Toast.LENGTH_SHORT).show();
        }
    };

    //private Spinner spCountry;
    public AutoCompleteTextView actvState;
    public static int id = 0;

    // STATES
    AutocompleteDAO dao = new AutocompleteDAO();
    private List<Autocomplete> listStatesCanada = dao.buscaTodosJogos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        imgNewPost = (ImageView)findViewById(R.id.imgNewPost);
        lstUltimos = (ListView)findViewById(R.id.lstUltimos);


        dbAdapter = new DBadapter(this);

        //Cria um adapter que retorna cada fragmento
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //Configura o PageView com as sessoes do adapter
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        LinearLayout llAddress = (LinearLayout) findViewById(R.id.llAddress);
        //Recebendo informações do Login
        final Intent intent = getIntent();
        Bundle param = intent.getExtras();

        idUser = param.getInt("idUsuario");
        nomeUser = param.getString("nomeUsuario");
        emailUser = param.getString("emailUsuario");

        //metodo para chamar nova postagem
        imgNewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpost = new Intent(Main.this,NovoPost.class);

                Bundle param = new Bundle();
                param.putInt("idUsuario", idUser);
                param.putString("nomeUsuario", nomeUser);
                param.putString("emailUsuario", emailUser);
                param.putString("nomeJogo", "");
                param.putInt("idJogo",0);
                intentpost.putExtras(param);

                startActivity(intentpost);

            }
        });

        // AUTO COMPLETE TEXT VIEW
        int position = 0;
        actvState = new AutoCompleteTextView(Main.this);
        actvState.setThreshold(1);
        actvState.setBackgroundColor(0);
        actvState.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
        float scale = getResources().getDisplayMetrics().density;
        actvState.setPadding((int)(5 * scale * 0.5f),(int)(4 * scale * 0.5f),(int)(5 * scale * 0.5f),(int)(0 * scale * 0.5f));
        //actvState.setHint("Estado / Provincia");
        //actvState = (AutoCompleteTextView) findViewById(R.id.actvState);
        actvState.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Integer cor = actvState.getCurrentTextColor();
                Log.i("Script", "beforeTextChanged("+s+")"+cor+"");
                Main.id = 0;
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

        llAddress.addView(actvState);
        actvState.setTextColor(Color.parseColor("#96141414"));
        List<Autocomplete> aux = position == 0 ? listStatesCanada : listStatesCanada;
        actvState.setAdapter(new AutoCompleteAdapter(Main.this, position, aux));

        // NAVIGATIOn DRAWER
        // END - RIGHT



        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.ffxv)
                .addProfiles(
                        new ProfileDrawerItem().withName(nomeUser).withEmail(emailUser).withIcon(getResources().getDrawable(R.drawable.jessica))
                        //new ProfileDrawerItem().withName("Person Two").withEmail("person2@gmail.com").withIcon(getResources().getDrawable(R.drawable.person_2)),

                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                        Toast.makeText(Main.this, "onProfileChanged: " + iProfile.getName(), Toast.LENGTH_SHORT).show();
                        headerNavigationLeft.setBackgroundRes(R.drawable.xv2);
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
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
                                aux.setIcon(getResources().getDrawable(getCorretcDrawerIcon(count, false)));
                                break;
                            }
                        }

                        if (i <= 3) {
                            ((PrimaryDrawerItem) iDrawerItem).setIcon(getResources().getDrawable(getCorretcDrawerIcon(i, true)));
                        }

                        mPositionClicked = i;
                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
                        if (i == 1) {
                            Intent menIntent3 = new Intent(Main.this, Perfil.class);
                            Bundle param = new Bundle();

                            param.putInt("idUsuario", idUser);
                            menIntent3.putExtras(param);

                            startActivityForResult(menIntent3, 1);
                        }
                        if (i == 2) {
                            Intent menIntent3 = new Intent(Main.this, Jogos.class);
                            startActivityForResult(menIntent3, 1);
                        }
                        if (i == 4) {
                            dbAdapter.open();
                            dbAdapter.editar(1,"","","","","","","");
                            dbAdapter.close();

                            Intent menIntent4 = new Intent(Main.this, Login.class);
                            startActivityForResult(menIntent4, 1);

                        }
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(Main.this, "onItemLongClick: " + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Inicio").withIcon(getResources().getDrawable(R.drawable.car_selected_1)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Perfil").withIcon(getResources().getDrawable(R.drawable.car_2)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Jogos").withIcon(getResources().getDrawable(R.drawable.car_3)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Amigos").withIcon(getResources().getDrawable(R.drawable.car_4)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Sair").withIcon(getResources().getDrawable(R.drawable.car_5)));
     //  navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Configurações"));
      //  navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notificação").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
    }



    // LISTENERS
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			/*String[] aux = getResources().getStringArray(position == 0 ? R.array.statesBrazil : R.array.statesCanada);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, aux);
			actvState.setAdapter(adapter);*/

        List<Autocomplete> aux = position == 0 ? listStatesCanada : listStatesCanada;
        //actvState.setAdapter(new AutoCompleteAdapter2(MainActivity.this, R.layout.auto_complete_item, R.id.tvState, aux));
        actvState.setAdapter(new AutoCompleteAdapter(Main.this, position, aux));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void Troca(Topicos post){
       Intent intent = new Intent(Main.this, DetalhesForum.class);

        Bundle param = new Bundle();
        param.putInt("idPost", post.getIdTopico());
        param.putString("titPost", post.getNome());
        param.putString("txtDesc",post.getDescricao());
        param.putInt("iduser",idUser);
        intent.putExtras(param);

        startActivityForResult(intent, 1);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    public void TrocaJogoCompleto(List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> lista){
        Intent intentdesc = new Intent(Main.this, br.edu.fatecriopreto.projetoandoid.DescJogo.class);

        Bundle param2 = new Bundle();

        param2.putInt("JogoId",lista.get(0).getIdDescJogo());
        param2.putString("JogoNome", lista.get(0).getDescJogo());
        param2.putString("JogoGenero",lista.get(0).getDescGenero());
        param2.putString("JogoFoto",lista.get(0).getDescImgByte());
        param2.putString("JogoDescricao",lista.get(0).getDescDescricao());
        param2.putString("JogoPlataforma",lista.get(0).getDescPlataforma());
        param2.putString("JogoProdutora",lista.get(0).getDescProdutora());
        param2.putInt("JogoAno",lista.get(0).getDescAno());
        param2.putInt("idUser",idUser);
        param2.putString("nomeUsuario",nomeUser);
        param2.putString("emailUsuario",emailUser);
        intentdesc.putExtras(param2);

        startActivity(intentdesc);
    }

    public void TrocaJogo(String jogoid, String jogonome, String jogogenero, String jogofoto){
        Intent intentjogo = new Intent(Main.this, DescJogo.class);
        Bundle param = new Bundle();
        param.putString("JogoId", jogoid);
        param.putString("JogoNome", jogonome);
        param.putString("JogoGenero",jogogenero);
        param.putString("JogoFoto",jogofoto);
        intentjogo.putExtras(param);
        startActivity(intentjogo);
    }

    //start: slider
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            txtRelevantes = (TextView)findViewById(R.id.txtRelevantes);
            txtUltimos = (TextView)findViewById(R.id.txtUltimos);
            txtSeguindo = (TextView)findViewById(R.id.txtSeguindo);

            switch (position) {
                case 0:
                    //txtRelevantes.setTextColor(Color.parseColor("A9A9A9"));
                    //txtSeguindo.setTextColor(Color.parseColor("#A9A9A9"));
                    txtUltimos.setTextColor(Color.parseColor("#008080"));
                    return new Ultimos();
                case 1:
                    txtUltimos.setTextColor(Color.parseColor("#A9A9A9"));
                    //txtRelevantes.setTextColor(Color.parseColor("#A9A9A9"));
                    //txtSeguindo.setTextColor(Color.parseColor("#008080"));
                    return new Seguindo();
                case 2:
                    txtUltimos.setTextColor(Color.parseColor("#A9A9A9"));
                    //txtRelevantes.setTextColor(Color.parseColor("#008080"));
                    //txtSeguindo.setTextColor(Color.parseColor("#A9A9A9"));
                    return new Relevantes();
            }
            return null;
        }



        @Override
        public int getCount() {
            // RETORNA O TOTAL DE PAGINAS
            return 3;
        }

    //apagar
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            //RETORNA O TITULO DE CADA SESSAO
            switch (position) {
                case 0:
                    return getString(R.string.nome1).toUpperCase(l);
                case 1:
                    return getString(R.string.nome2).toUpperCase(l);
                case 2:
                    return getString(R.string.nome3).toUpperCase(l);
            }
            return null;
        }
    }



    //PLACEHOLDER CONTENDO UM FRAGMENTO
    public static class PlaceholderFragment extends Fragment {

        //A STRING ABAIXO REPRESENTA O NUMERO DA SESSAO PARA O FRAGMENTO
        private static final String ARG_SECTION_NUMBER = "section_number";

        //RETORNA UMA NOVA INSTANCIA PARA O FRAGMENTO USANDO SEU NUMERO DE SESSAO
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }
        //end: slider
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