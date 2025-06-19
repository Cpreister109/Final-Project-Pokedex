package com.preister.testfxapp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Api {

    // fetch the pokedex data, only the name and sprite for optimization
    public static Dex fetchDex() {

        ArrayList<PokeEntry> emptyList = new ArrayList<>();
        Dex pokedex = new Dex(emptyList, 0);

        try {
            // Create HttpClient to make the request
            HttpClient client = HttpClient.newHttpClient();

            // Create the HTTP request to fetch the Pokémon list
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon?limit=151")) // Limit to 10 for simplicity
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray results = jsonResponse.getJSONArray("results");

            // Print the names of the Pokémon
            for (int i = 0; i < results.length(); i++) {
                JSONObject basicPokemon = results.getJSONObject(i);
                String url = basicPokemon.getString("url"); // full details URL

                // Fetch full Pokémon data
                HttpRequest pokeRequest = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> pokeResponse = client.send(pokeRequest, HttpResponse.BodyHandlers.ofString());

                JSONObject fullPokemonData = new JSONObject(pokeResponse.body());

                // Now use the full JSON to create the object
                PokeEntry dexEntry = PokeEntry.fromJSON(fullPokemonData);

                // Append info
                pokedex.addMon(dexEntry);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return pokedex;
        }

        System.out.println("Dex Grabbed!");
        return pokedex;

    }

    // fetch the specific pokemon data for party members
    public static Pokemon fetchMon(String name) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject fullPokemonData = new JSONObject(response.body());

            return Pokemon.fromJSON(fullPokemonData);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
