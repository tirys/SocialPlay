package br.edu.fatecriopreto.projetoandoid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Ultimos extends Fragment {

    ListView lstUltimos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //FRAGMENT_MAIN É UMA ACTIVITY QUE VAI SER USADA, PRECISA CRIAR ELE
        View rootView = inflater.inflate(R.layout.frag_ultimos, container, false);


        ListView lstUltimos = (ListView) rootView.findViewById(R.id.lstUltimos);

        Posts post = new Posts(1,"Tutorial","descricao, descricao, descricao");
        Posts post2 = new Posts(2,"Tutorial2","descricao2, descricao2, descricao2");
        Posts post3 = new Posts(3,"Tutorial3","descricao3, descricao3, descricao3");
        Posts post4 = new Posts(1,"Tutorial","descricao, descricao, descricao");
        Posts post5 = new Posts(2,"Tutorial2","descricao2, descricao2, descricao2");
        Posts post6 = new Posts(3,"Tutorial3","descricao3, descricao3, descricao3");
        Posts post7 = new Posts(1,"Tutorial","descricao, descricao, descricao");
        Posts post8 = new Posts(2,"Tutorial2","descricao2, descricao2, descricao2");
        Posts post9 = new Posts(3,"Tutorial3","descricao3, descricao3, descricao3");
        List<Posts> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        posts.add(post6);
        posts.add(post7);
        posts.add(post8);
        posts.add(post9);
        //AdapterListView adapter = new AdapterListView(getActivity().getApplicationContext(),posts);
        //AdapterListView adapter = new AdapterListView(getActivity(),posts);

        lstUltimos.setAdapter(new AdapterListView(getActivity(), posts));


        return rootView;



    }


}
