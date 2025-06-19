package com.preister.testfxapp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pokemon {
    private String name;
    private String sprite;
    private ArrayList<String> types;
    private ArrayList<String> ability;
    private ArrayList<String> moves;
    private String description;

    public Pokemon(String name, String sprite, ArrayList<String> types, ArrayList<String> ability,
            ArrayList<String> moves, String description) {

        this.name = name;
        this.sprite = sprite;
        this.types = types;
        this.ability = ability;
        this.moves = moves;
        this.description = description;

    }

    // grabs specific data from a party pokemon
    // (name, sprite, type, abilities, moves, and description)
    public static Pokemon fromJSON(JSONObject pokemon) {
        String name = pokemon.getString("name");
        String sprite = pokemon.getJSONObject("sprites").getString("front_default");

        ArrayList<String> types = new ArrayList<>();
        JSONArray typeArray = pokemon.getJSONArray("types");
        for (int i = 0; i < typeArray.length(); i++) {
            String typeName = typeArray.getJSONObject(i).getJSONObject("type").getString("name");
            types.add(typeName);
        }

        ArrayList<String> abilities = new ArrayList<>();
        JSONArray abilityArray = pokemon.getJSONArray("abilities");
        for (int i = 0; i < abilityArray.length(); i++) {
            String abilityName = abilityArray.getJSONObject(i).getJSONObject("ability").getString("name");
            abilities.add(abilityName);
        }

        ArrayList<String> moves = new ArrayList<>();
        JSONArray movesArray = pokemon.getJSONArray("moves");
        for (int i = 0; i < movesArray.length(); i++) {
            String moveName = movesArray.getJSONObject(i).getJSONObject("move").getString("name");
            moves.add(moveName);
        }

        String description = "Description unavailable.";
        try {
            String speciesUrl = pokemon.getJSONObject("species").getString("url");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(speciesUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject speciesData = new JSONObject(response.body());
            JSONArray flavorTextEntries = speciesData.getJSONArray("flavor_text_entries");

            for (int i = 0; i < flavorTextEntries.length(); i++) {
                JSONObject entry = flavorTextEntries.getJSONObject(i);
                String language = entry.getJSONObject("language").getString("name");

                if (language.equals("en")) {
                    description = entry.getString("flavor_text").replace("\n", " ").replace("\f", " ");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            description = "Error retrieving description.";
        }

        return new Pokemon(name, sprite, types, abilities, moves, description);
    }

    public String getName() {
        return name;
    }

    public String getSprite() {
        return sprite;
    }

    public ArrayList<String> getType() {
        return types;
    }

    public ArrayList<String> getAbility() {
        return ability;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public String getDescription() {
        return description;
    }
}
