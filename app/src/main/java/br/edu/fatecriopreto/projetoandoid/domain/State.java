package br.edu.fatecriopreto.projetoandoid.domain;

import java.util.HashMap;

import br.edu.fatecriopreto.projetoandoid.R;

public class State {
    private String name;
    private int imgResource;
    public static HashMap<String, Integer> imgMap = new HashMap<String, Integer>();
    static {
        imgMap.put("Ontario (ON)", R.drawable.ontario);
        imgMap.put("Quebec (QC)", R.drawable.quebec);
        imgMap.put("Nova Scotia (NS)", R.drawable.nova_scotia);
        imgMap.put("New Brunswick (NB)", R.drawable.new_brunswick);
        imgMap.put("Manitoba (MB)", R.drawable.manitoba);
        imgMap.put("British Columbia (BC)", R.drawable.british_columbia);
        imgMap.put("Prince Edward Island (PE)", R.drawable.prince_edward_island);
        imgMap.put("Saskatchewan (SK)", R.drawable.saskatchewan);
        imgMap.put("Alberta (AB)", R.drawable.alberta);
        imgMap.put("Newfoundland and Labrador (NL)", R.drawable.newfoundland_and_labrador);
    }



    public State(){}
    public State(String name, int imgResource){
        this.name = name;
        this.imgResource = imgResource;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImgResource() {
        return imgResource;
    }
    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }


    public String toString() {
        return(this.getName());
    }
}
