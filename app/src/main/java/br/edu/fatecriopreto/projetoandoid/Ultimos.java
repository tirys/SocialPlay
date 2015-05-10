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
  //  private ActionBarActivity aba;
    ListView lstUltimos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  aba = new ActionBarActivity();
        ListView lstUltimos = (ListView) getActivity().findViewById(R.id.lstUltimos);


        Posts post = new Posts(1,"Tutorial","descricao, descricao, descricao");
        List<Posts> posts = new ArrayList<>();
        posts.add(post);
       //AdapterListView adapter = new AdapterListView(getActivity().getApplicationContext(),posts);
       AdapterListView adapter = new AdapterListView(container.getContext(),posts);


        //FRAGMENT_MAIN É UMA ACTIVITY QUE VAI SER USADA, PRECISA CRIAR ELE
        View rootView = inflater.inflate(R.layout.frag_ultimos, container, false);

        return rootView;


    }


}
