package br.edu.fatecriopreto.projetoandoid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Entity.Comentarios;
import br.edu.fatecriopreto.projetoandoid.R;
import br.edu.fatecriopreto.projetoandoid.Topicos;

/**
 * Created by Jessica on 29/05/2015.
 */
public class LstComentariosAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Comentarios> posts;

    public LstComentariosAdapter(Context context,List<Comentarios> posts){
        this.posts=posts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return posts.size(); //retorna qtd de itens

    }

    @Override
    public Comentarios getItem(int position){
        return posts.get(position); //retorna o item na posicao indicada
    }



    @Override
    public  long getItemId(int position){
        return position;
    }

    private class ItemSuporte{

        TextView txtComentario;
        TextView txtNomepessoa;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //metodo que preenche o layout com os dados do item
        ItemSuporte item;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.lista_comentarios, null); //coloca o layout na visualização

            item = new ItemSuporte();

            item.txtComentario = (TextView) convertView.findViewById(R.id.txtComentario);
            item.txtNomepessoa = (TextView) convertView.findViewById(R.id.txtnomeuser);

            convertView.setTag(item);

        }else{
            item=(ItemSuporte) convertView.getTag(); //pega o layout
        }

        Comentarios post = getItem(position); //recupera o objeto veiculo da lista
        // item.txtTituloPost.setText(veiculo.getPlaca()); //adicionando no itemplaca configurado no layout
        // item.txtDescricaoPost.setText(veiculo.getMarca());
       //  item.txtTituloPost.setText(post.getNome());
     //   item.txtDescricaoPost.setText(post.getDescricao());

        item.txtComentario.setText(post.getConteudo());
        item.txtNomepessoa.setText(post.getNomePessoa());
       // item.imgUser.setImageResource(R.drawable.user);


        return convertView;
    }

}
