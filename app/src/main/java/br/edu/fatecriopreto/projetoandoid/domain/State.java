package br.edu.fatecriopreto.projetoandoid.domain;

import java.util.HashMap;

import br.edu.fatecriopreto.projetoandoid.R;

public class State {
    private String name;

    public State(){}
    public State(String name){
        this.name = name;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return(this.getName());
    }
}
