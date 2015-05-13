package br.edu.fatecriopreto.projetoandoid;

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

import java.util.List;
import java.util.Locale;


public class Main extends ActionBarActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }

    public void Troca(long id){
       Intent intent = new Intent(Main.this, Perfil.class);
      // startActivityForResult(intent, 2);

        Perfil fragment2 = new Perfil();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment2);
        fragmentTransaction.commit();



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