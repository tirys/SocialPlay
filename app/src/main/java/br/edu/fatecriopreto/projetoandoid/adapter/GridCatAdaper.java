package br.edu.fatecriopreto.projetoandoid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Categorias;
import br.edu.fatecriopreto.projetoandoid.Entity.Genero;
import br.edu.fatecriopreto.projetoandoid.Entity.Jogos;
import br.edu.fatecriopreto.projetoandoid.R;

/**
 * Created by Jéssica on 23/05/2015.
 */
public class GridCatAdaper extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Genero> categorias;

    public GridCatAdaper(Context context,List<Genero> categorias){
        this.categorias=categorias;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return categorias.size(); //retorna qtd de itens
    }

    @Override
    public Genero getItem(int position){
        return categorias.get(position); //retorna o item na posicao indicada
    }



    @Override
    public  long getItemId(int position){
        return position;
    }

    private class ItemSuporte{

        TextView txtTitulo;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //metodo que preenche o layout com os dados do item
        ItemSuporte item;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.itemgridpc, null); //coloca o layout na visualização

            item = new ItemSuporte();

            item.txtTitulo = (TextView) convertView.findViewById(R.id.titulocat);


            convertView.setTag(item);

        }else{
            item=(ItemSuporte) convertView.getTag(); //pega o layout
        }

        Genero categoria = getItem(position); //recupera o objeto veiculo da lista
        // item.txtTituloPost.setText(veiculo.getPlaca()); //adicionando no itemplaca configurado no layout
        // item.txtDescricaoPost.setText(veiculo.getMarca());
        item.txtTitulo.setText(categoria.getGenero());


        //item.txtDescricaoPost.setText("conteudo conteudo conteudo");



        return convertView;
    }

}
