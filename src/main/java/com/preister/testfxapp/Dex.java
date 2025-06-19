package com.preister.testfxapp;

import java.util.ArrayList;

public class Dex {

    private ArrayList<PokeEntry> pokedex;
    private int count;

    public Dex(ArrayList<PokeEntry> pokedex, int count) {
        this.pokedex = pokedex;
        this.count = count;
    }

    // add a pokemon
    public void addMon(PokeEntry pokemon) {
        pokedex.add(pokemon);
        count += 1;
    }

    public ArrayList<PokeEntry> getDex() {
        return pokedex;
    }

    public int getCount() {
        return count;
    }
}
