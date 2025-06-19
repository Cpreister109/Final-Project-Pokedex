package com.preister.testfxapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PokemonController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private ImageView sprite;
    @FXML
    private Text ability;
    @FXML
    private Label type;
    @FXML
    private Text moves;
    @FXML
    private Text description;

    private Pokemon pokemon;

    // loads the pokemon data onto pokemon.fxml when page loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pokemon = App.getSelectedPokemon();

        if (pokemon != null) {
            name.setText(capitalize(pokemon.getName()));
            sprite.setImage(new Image(pokemon.getSprite()));
            ability.setText(String.join(", ", pokemon.getAbility()));
            type.setText(String.join(", ", pokemon.getType()));
            moves.setText(String.join("\n", pokemon.getMoves()));
            description.setText(pokemon.getDescription());
        }
    }

    // naviagte back to the user's party
    @FXML
    public void goBack() throws IOException {
        App.setRoot("party");
        javafx.application.Platform.runLater(() -> {
            javafx.stage.Stage stage = App.getPrimaryStage();
            stage.setWidth(1000);
            stage.setHeight(300);
        });
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
