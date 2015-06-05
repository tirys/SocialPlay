package br.edu.fatecriopreto.projetoandoid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecriopreto.projetoandoid.Autocomplete;
import br.edu.fatecriopreto.projetoandoid.Main;
import br.edu.fatecriopreto.projetoandoid.NovoPost;
import br.edu.fatecriopreto.projetoandoid.R;
import br.edu.fatecriopreto.projetoandoid.webservice.DescJogo;

//import br.edu.fatecriopreto.projetoandoid.Main;

public class AutoCompleteAdapter extends ArrayAdapter<Autocomplete> implements Filterable {
    private Context context;
    private List<Autocomplete> listFull;
    private List<Autocomplete> listAux;
    private Filter filter;
    private LayoutInflater inflater;
    private int country;

    public AutoCompleteAdapter(Context context, int country, List<Autocomplete> listFull) {
        super(context, 0, 0, listFull);
        this.context = context;
        this.country = 0;
        this.listFull = listFull;
        this.listAux = new ArrayList<Autocomplete>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount(){
        return(listAux.size());
    }


    @Override
    public Autocomplete getItem(int position){
        return(listAux.get(position));
    }


    @Override
    public View getView(final int position, View view, ViewGroup root){
        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.auto_complete_item, null);
            holder = new ViewHolder();
            view.setTag(holder);

            holder.ivFlag = (ImageView) view.findViewById(R.id.ivFlag);
            holder.tvState = (TextView) view.findViewById(R.id.tvState);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.ivFlag.setImageBitmap(listAux.get(position).getImagem());
        holder.tvState.setText(listAux.get(position).getJogo());

        holder.tvState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classeAtual = context.getClass().getName().toString();
                String classeNovaPostagem = "br.edu.fatecriopreto.projetoandoid.NovoPost";
                if(classeAtual.equals(classeNovaPostagem)) {
                    if (context instanceof NovoPost) {
                        //((Main)context).actvState.setText(listAux.get(position).getJogo());
                        //int jogoid = listAux.get(position).getIdJogo();
                        String jogonome = listAux.get(position).getJogo();
                        String jogoid = Integer.toString(listAux.get(position).getIdJogo());
                        ((NovoPost)context).actvPostagem.setText(jogonome);
                        ((NovoPost)context).edtIdJogo.setText(jogoid);
                        //String jogogenero = listAux.get(position).getGenero();

                        //String foto = listAux.get(position).getImagem().toString();
                        //byte[] bt = Base64.decode(foto, Base64.DEFAULT);
                        //String bt = listAux.get(position).getImgByte();
                        //Bitmap jogofoto = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                        //DescJogo dao = new DescJogo();
                        //List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> lista = dao.buscaTodosJogos(jogoid);
                        //((Main) context).TrocaJogoCompleto(lista);
                    }
                }else{
                    if (context instanceof Main) {
                        //((Main)context).actvState.setText(listAux.get(position).getJogo());

                        int jogoid = listAux.get(position).getIdJogo();
                        //String jogonome = listAux.get(position).getJogo();
                        //String jogogenero = listAux.get(position).getGenero();

                        //String foto = listAux.get(position).getImagem().toString();
                        //byte[] bt = Base64.decode(foto, Base64.DEFAULT);
                        //String bt = listAux.get(position).getImgByte();
                        //Bitmap jogofoto = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                        DescJogo dao = new DescJogo();
                        List<br.edu.fatecriopreto.projetoandoid.Entity.DescJogo> lista = dao.buscaTodosJogos(jogoid);
                        ((Main) context).TrocaJogoCompleto(lista);
                    }
                }
            }
        });

        return(view);
    }

    static class ViewHolder{
        ImageView ivFlag;
        TextView tvState;
    }


    // FILTER
    @Override
    public Filter getFilter(){
        if(filter == null){
            filter = new ArrayFilter();
        }
        return(filter);
    }
		private class ArrayFilter extends Filter{

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults results = new FilterResults();
				String constraintString = (constraint+"").toLowerCase();
				
				if(constraint == null || constraint.length() == 0){
					List<Autocomplete> list = new ArrayList<Autocomplete>(listFull);
					results.count = list.size();
					results.values = list;
				}
				else{
					int qtdConstraint = constraintString.length();
                    String classeAtual = context.getClass().getName().toString();
                    String classeNovaPostagem = "br.edu.fatecriopreto.projetoandoid.NovoPost";
                    if(classeAtual.equals(classeNovaPostagem)){
                        ((NovoPost) context).id = 1;
                    }else{
                        ((Main) context).id = 1;
                    }

					//ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://www.villopim.com.br/android/state.php", country, constraintString);
					//ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://10.0.2.2:82/android/state.php", country, constraintString);
					//ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://socialplay.no-ip.biz:80/state/state.php", country, constraintString);

					ArrayList<Autocomplete> newValues = new ArrayList<Autocomplete>(listFull.size());
					for (int i = 0; i < listFull.size(); i++) {
						String item = listFull.get(i).getJogo();
						item = item.replaceAll("[çÇ]", "c");
						item = item.replaceAll("[àáâãä]", "a");
						item = item.replaceAll("[ÀÁÂÃÄ]", "A");
						item = item.replaceAll("[èéêë]", "e");
						item = item.replaceAll("[ÉÈÊË]", "E");
						item = item.replaceAll("[íìîï]", "i");
						item = item.replaceAll("[ÌÍÎÏ]", "I");
						item = item.replaceAll("[óòôõö]", "o");
						item = item.replaceAll("[ÒÔÖÓÕ]", "O");
						item = item.replaceAll("[ùúûü]", "u");
						item = item.replaceAll("[ÙÛÜÚ]", "U");
						item = item.toLowerCase();
						
						if(item.substring(0, qtdConstraint).equalsIgnoreCase(constraintString)
							|| listFull.get(i).getJogo().toLowerCase().substring(0, qtdConstraint).equalsIgnoreCase(constraintString)){
							newValues.add(listFull.get(i));
						}
					}
					
					results.count = newValues.size();
					results.values = newValues;
				}
				
				return(results);
			}

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
                String classeAtual = context.getClass().getName().toString();
                String classeNovaPostagem = "br.edu.fatecriopreto.projetoandoid.NovoPost";
                if(classeAtual.equals(classeNovaPostagem)){
                    if(results.values != null && ((NovoPost) context).id > 0){
                        listAux = (ArrayList<Autocomplete>) results.values;
                    }
                    else{
                        listAux = new ArrayList<Autocomplete>();
                    };
                }else{
                    if(results.values != null && ((Main) context).id > 0){
                        listAux = (ArrayList<Autocomplete>) results.values;
                    }
                    else{
                        listAux = new ArrayList<Autocomplete>();
                    }
                }

				if(results.count == 0){
					notifyDataSetInvalidated();
				}
				else{
					notifyDataSetChanged();
				}
			}
			
		}
}
