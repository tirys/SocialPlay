package br.edu.fatecriopreto.projetoandoid.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Jogos;
import br.edu.fatecriopreto.projetoandoid.Posts;
import br.edu.fatecriopreto.projetoandoid.R;

public class ListaJogosAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<br.edu.fatecriopreto.projetoandoid.Entity.Games> jogos;

    public ListaJogosAdapter(Context context,List<br.edu.fatecriopreto.projetoandoid.Entity.Games> jogos){
        this.jogos=jogos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return jogos.size(); //retorna qtd de itens
    }

    @Override
    public br.edu.fatecriopreto.projetoandoid.Entity.Games getItem(int position){
        return jogos.get(position); //retorna o item na posicao indicada
    }



    @Override
    public  long getItemId(int position){
        return position;
    }

    private class ItemSuporte{

        TextView txtTitulo;
        ImageView imgUser;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //metodo que preenche o layout com os dados do item
        ItemSuporte item;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_jogos, null); //coloca o layout na visualização

            item = new ItemSuporte();

            item.txtTitulo = (TextView) convertView.findViewById(R.id.nomejogo2);
            item.imgUser = (ImageView) convertView.findViewById(R.id.imgjogo2);

            convertView.setTag(item);

        }else{
            item=(ItemSuporte) convertView.getTag(); //pega o layout
        }

        br.edu.fatecriopreto.projetoandoid.Entity.Games jogo = getItem(position); //recupera o objeto veiculo da lista
        // item.txtTituloPost.setText(veiculo.getPlaca()); //adicionando no itemplaca configurado no layout
        // item.txtDescricaoPost.setText(veiculo.getMarca());
        item.txtTitulo.setText(jogo.getJogo());


        //item.txtDescricaoPost.setText("conteudo conteudo conteudo");
        item.imgUser.setImageResource(R.drawable.user);


        return convertView;
    }


}
