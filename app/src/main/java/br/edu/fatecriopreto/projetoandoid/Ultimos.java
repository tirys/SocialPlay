package br.edu.fatecriopreto.projetoandoid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class Ultimos extends Fragment{

    ListView lstUltimos;
    TopicosDAO dao = new TopicosDAO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //FRAGMENT_MAIN É UMA ACTIVITY QUE VAI SER USADA, PRECISA CRIAR ELE
        View rootView = inflater.inflate(R.layout.frag_ultimos, container, false);

       List<Topicos> lista = dao.buscaTodosTopicos();
       final ListView lstUltimos = (ListView) rootView.findViewById(R.id.lstUltimos);



        //AdapterListView adapter = new AdapterListView(getActivity().getApplicationContext(),posts);
        //AdapterListView adapter = new AdapterListView(getActivity(),posts);

       lstUltimos.setAdapter(new AdapterListView(getActivity(), lista));

       lstUltimos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topicos post = (Topicos) parent.getItemAtPosition(position); //codigo para cast

                //((Main)getActivity()).Troca(post.getId());
                ((Main)getActivity()).Troca(post);
            }
        });




        return rootView;



    }


}


