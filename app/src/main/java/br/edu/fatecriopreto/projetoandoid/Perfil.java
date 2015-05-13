package br.edu.fatecriopreto.projetoandoid;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Perfil extends Fragment {
    TextView Idpost;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //FRAGMENT_MAIN É UMA ACTIVITY QUE VAI SER USADA, PRECISA CRIAR ELE
        View rootView = inflater.inflate(R.layout.perfil, container, false);



        return rootView;
    }
}
