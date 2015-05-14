package br.edu.fatecriopreto.projetoandoid;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebViewFragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import java.util.Locale;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import br.edu.fatecriopreto.projetoandoid.adapter.AutoCompleteAdapter;
import br.edu.fatecriopreto.projetoandoid.domain.State;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;


public class Main extends ActionBarActivity implements OnItemSelectedListener {

    TextView txtUltimos;
    TextView txtSeguindo;
    TextView txtRelevantes;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    String[] mDrawerListItems;
    //INICIA O ADAPTER
    SectionsPagerAdapter mSectionsPagerAdapter;
    Fragment fragmentozinho;
    //INICIA O PAGEVIEW
    ViewPager mViewPager;
    ListView lstUltimos;

    //private Spinner spCountry;
    private AutoCompleteTextView actvState;
    public static int id = 0;

    // STATES

    private List<State> listStatesCanada = Arrays.asList(new State[]{new State("Ontario (ON)", R.drawable.ontario),
            new State("Quebec (QC)", R.drawable.quebec),
            new State("Nova Scotia (NS)", R.drawable.nova_scotia),
            new State("New Brunswick (NB)", R.drawable.new_brunswick),
            new State("Manitoba (MB)", R.drawable.manitoba),
            new State("British Columbia (BC)", R.drawable.british_columbia),
            new State("Prince Edward Island (PE)", R.drawable.prince_edward_island),
            new State("Saskatchewan (SK)", R.drawable.saskatchewan),
            new State("Alberta (AB)", R.drawable.alberta),
            new State("Newfoundland and Labrador (NL)", R.drawable.newfoundland_and_labrador)
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        lstUltimos = (ListView)findViewById(R.id.lstUltimos);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mDrawerList = (ListView)findViewById(android.R.id.list);
        mDrawerListItems = getResources().getStringArray(R.array.drawer_list);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDrawerListItems));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int editedPosition = position+1;

                Toast.makeText(Main.this, "You selected item " + editedPosition, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();


        //Cria um adapter que retorna cada fragmento
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //Configura o PageView com as sessoes do adapter
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        LinearLayout llAddress = (LinearLayout) findViewById(R.id.llAddress);

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
        List<State> aux = position == 0 ? listStatesCanada : listStatesCanada;
        actvState.setAdapter(new AutoCompleteAdapter(Main.this, position, aux));
    }

    // LISTENERS
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			/*String[] aux = getResources().getStringArray(position == 0 ? R.array.statesBrazil : R.array.statesCanada);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, aux);
			actvState.setAdapter(adapter);*/

        List<State> aux = position == 0 ? listStatesCanada : listStatesCanada;
        //actvState.setAdapter(new AutoCompleteAdapter2(MainActivity.this, R.layout.auto_complete_item, R.id.tvState, aux));
        actvState.setAdapter(new AutoCompleteAdapter(Main.this, position, aux));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void Troca(Posts post){
       Intent intent = new Intent(Main.this, DetalhesForum.class);

        Bundle param = new Bundle();
        param.putLong("idPost", post.getId());
        param.putString("titPost", post.getTitulo());
        param.putString("txtDesc",post.getDescricao());
        intent.putExtras(param);

        startActivityForResult(intent, 1);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

       /* TextView Idpost;

        Perfil fragment2 = new Perfil();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.drawer, fragment2);
        fragmentTransaction.commit();*/


        //return new Perfil();
       // Toast.makeText(Main.this, "You selected item " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home: {
                if (mDrawerLayout.isDrawerOpen(mDrawerList)){
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
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

}