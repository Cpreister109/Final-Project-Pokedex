package com.preister.testfxapp;

import java.util.ArrayList;

public class Party {

    private ArrayList<Pokemon> party;
    private int count;

    public Party(ArrayList<Pokemon> party, int count) {
        this.party = party;
        this.count = count;
    }

    // add pokemon to the party instance
    public void addMonParty(String pokemon) {
        System.out.println(pokemon + " Added to Party!");
        party.add(Api.fetchMon(pokemon));
        count += 1;
    }

    // delete pokemon based on index
    public void deleteMonParty(int i) {
        System.out.println("Pokemon Removed from Party!");
        party.remove(i);
        count -= 1;
    }

    public ArrayList<Pokemon> getParty() {
        return party;
    }

    public int getCount() {
        return count;
    }

}
