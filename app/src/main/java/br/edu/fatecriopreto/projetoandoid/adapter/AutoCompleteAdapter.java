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

import br.edu.fatecriopreto.projetoandoid.Main;
import br.edu.fatecriopreto.projetoandoid.R;
import br.edu.fatecriopreto.projetoandoid.connection.HttpConnection;
import br.edu.fatecriopreto.projetoandoid.domain.State;

//import br.edu.fatecriopreto.projetoandoid.Main;

public class AutoCompleteAdapter extends ArrayAdapter<State> implements Filterable {
    private Context context;
    private List<State> listFull;
    private List<State> listAux;
    private Filter filter;
    private LayoutInflater inflater;
    private int country;

    public AutoCompleteAdapter(Context context, int country, List<State> listFull) {
        super(context, 0, 0, listFull);
        this.context = context;
        this.country = 0;
        this.listFull = listFull;
        this.listAux = new ArrayList<State>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount(){
        return(listAux.size());
    }


    @Override
    public State getItem(int position){
        return(listAux.get(position));
    }


    @Override
    public View getView(int position, View view, ViewGroup root){
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

        holder.ivFlag.setImageResource(listAux.get(position).getImgResource());
        holder.tvState.setText(listAux.get(position).getName());

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
					List<State> list = new ArrayList<State>(listFull);
					results.count = list.size();
					results.values = list;
				}
				else{
					int qtdConstraint = constraintString.length();
					
					((Main) context).id = 1;
					//ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://www.villopim.com.br/android/state.php", country, constraintString);
					ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://10.0.2.2:80/android/state.php", country, constraintString);
					//ArrayList<State> newValues = (ArrayList<State>) HttpConnection.getStateListWeb("http://socialplay.no-ip.biz:80/state/state.php", country, constraintString);

					/*ArrayList<State> newValues = new ArrayList<State>(listFull.size());
					for (int i = 0; i < listFull.size(); i++) {
						String item = listFull.get(i).getName();
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
							|| listFull.get(i).getName().toLowerCase().substring(0, qtdConstraint).equalsIgnoreCase(constraintString)){
							newValues.add(listFull.get(i));
						}
					}*/
					
					results.count = newValues.size();
					results.values = newValues;
				}
				
				return(results);
			}

			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				if(results.values != null && ((Main) context).id > 0){
					listAux = (ArrayList<State>) results.values;
				}
				else{
					listAux = new ArrayList<State>();
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
