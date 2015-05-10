package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterListView extends BaseAdapter{
    private LayoutInflater inflater;
   private List<Posts> posts;

    public AdapterListView(Context context,List<Posts> posts){
        this.posts=posts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return posts.size(); //retorna qtd de itens
    }

    @Override
    public Posts getItem(int position){
        return posts.get(position); //retorna o item na posicao indicada
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    private class ItemSuporte{

        TextView txtTituloPost;
        TextView txtDescricaoPost;
        ImageView imgUser;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //metodo que preenche o layout com os dados do item
        ItemSuporte item;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.lista_topicos, null); //coloca o layout na visualização

            item = new ItemSuporte();

            item.txtTituloPost = (TextView) convertView.findViewById(R.id.txtTituloPost);
            item.txtDescricaoPost = (TextView) convertView.findViewById(R.id.txtDescricaoPost);
            item.imgUser = (ImageView) convertView.findViewById(R.id.imgUser);

            convertView.setTag(item);

        }else{
            item=(ItemSuporte) convertView.getTag(); //pega o layout
        }

        Posts post = getItem(position); //recupera o objeto veiculo da lista
       // item.txtTituloPost.setText(veiculo.getPlaca()); //adicionando no itemplaca configurado no layout
       // item.txtDescricaoPost.setText(veiculo.getMarca());
        item.txtTituloPost.setText(post.getTitulo());
        item.txtDescricaoPost.setText(post.getDescricao());

      //  item.txtDescricaoPost.setText("conteudo conteudo conteudo");
       item.imgUser.setImageResource(R.drawable.user);


        return convertView;
    }

}
