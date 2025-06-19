package com.preister.testfxapp;

import org.json.JSONObject;

public class PokeEntry {

    private String name;
    private String sprite;

    public PokeEntry(String name, String sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public String getSprite() {
        return sprite;
    }

    // grabs sprite & name data from each pokemon for dex
    public static PokeEntry fromJSON(JSONObject dexEntry) {
        String name = dexEntry.getString("name");
        String sprite = dexEntry.getJSONObject("sprites").getString("front_default");

        return new PokeEntry(name, sprite);
    }
}
