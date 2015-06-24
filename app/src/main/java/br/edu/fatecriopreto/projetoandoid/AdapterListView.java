package br.edu.fatecriopreto.projetoandoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterListView extends BaseAdapter{
    private LayoutInflater inflater;
   private List<Topicos> posts;

    public AdapterListView(Context context,List<Topicos> posts){
        this.posts=posts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
    return posts.size(); //retorna qtd de itens

    }

    @Override
    public Topicos getItem(int position){
        return posts.get(position); //retorna o item na posicao indicada
    }



    @Override
    public  long getItemId(int position){
        return position;
    }

    private class ItemSuporte{

        TextView txtTituloPost;
        TextView txtDescricaoPost;
        ImageView imgUserTopico;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //metodo que preenche o layout com os dados do item
        ItemSuporte item;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.lista_topicos, null); //coloca o layout na visualização


            item = new ItemSuporte();

            item.txtTituloPost = (TextView) convertView.findViewById(R.id.txtTituloPost);
            item.txtDescricaoPost = (TextView) convertView.findViewById(R.id.txtDescricaoPost);
            item.imgUserTopico = (ImageView) convertView.findViewById(R.id.imgUserTopico);

            convertView.setTag(item);

        }else{
            item=(ItemSuporte) convertView.getTag(); //pega o layout
        }

        Topicos post = getItem(position); //recupera o objeto veiculo da lista
       // item.txtTituloPost.setText(veiculo.getPlaca()); //adicionando no itemplaca configurado no layout
       // item.txtDescricaoPost.setText(veiculo.getMarca());
        String ImgUser = post.getImagem();
        if(ImgUser != null) {
        item.txtTituloPost.setText(post.getNome());
        item.txtDescricaoPost.setText(post.getDescricao());

            byte[] bt = Base64.decode(ImgUser, Base64.DEFAULT);
            //String bt = listAux.get(position).getImgByte();
            Bitmap userfoto = BitmapFactory.decodeByteArray(bt, 0, bt.length);
            //imgFoto.setImageBitmap(userfoto);

            //item.txtDescricaoPost.setText("conteudo conteudo conteudo");
            item.imgUserTopico.setImageBitmap(userfoto);
        }

        return convertView;
    }

}
