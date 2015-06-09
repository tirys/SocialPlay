package br.edu.fatecriopreto.projetoandoid;


import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class Seguindo extends Fragment {
    ListView lstSeguindoo;
    TopicosDAO dao = new TopicosDAO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //FRAGMENT_MAIN É UMA ACTIVITY QUE VAI SER USADA, PRECISA CRIAR ELE
        View rootView = inflater.inflate(R.layout.frag_seguindo, container, false);

        List<Topicos> lista = dao.listarPostsSeguindo(((Main)getActivity()).idUser);
        final ListView lstSeguindoo = (ListView) rootView.findViewById(R.id.lstSeguindoo);


        //AdapterListView adapter = new AdapterListView(getActivity().getApplicationContext(),posts);
        //AdapterListView adapter = new AdapterListView(getActivity(),posts);

        lstSeguindoo.setAdapter(new AdapterListView(getActivity(), lista));

        lstSeguindoo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
