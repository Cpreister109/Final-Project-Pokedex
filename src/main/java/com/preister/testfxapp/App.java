package com.preister.testfxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Dex pokedex;
    private static Pokemon selectedPokemon;
    private static Stage primaryStage;

    static ArrayList<Pokemon> emptyList = new ArrayList<>();
    private static Party party = new Party(emptyList, 0);

    @Override
    public void start(Stage stage) throws IOException {
        pokedex = Api.fetchDex(); // grab dex at the beginning of program to optimize program
        primaryStage = stage;
        scene = new Scene(loadFXML("home"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static Dex getDex() {
        return pokedex;
    }

    public static Party getParty() {
        return party;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setSelectedPokemon(Pokemon pokemon) throws IOException {
        selectedPokemon = pokemon;
    }

    static Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // main function
    public static void main(String[] args) {
        launch();
    }

}