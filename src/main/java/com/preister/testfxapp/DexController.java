package com.preister.testfxapp;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DexController implements Initializable {
    @FXML
    private GridPane dexGrid;

    private Party party = App.getParty();
    private Dex dex = App.getDex();

    // when navigated to the page loads with this
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int columns = 5;
        int row = 0;
        int col = 0;

        for (PokeEntry pokemon : dex.getDex()) {
            VBox pokeBox = createPokeBox(pokemon);
            dexGrid.add(pokeBox, col, row);

            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }
    }

    // creates pokemon Vbox to display data
    private VBox createPokeBox(PokeEntry entry) {
        VBox box = new VBox(5);
        box.setStyle("-fx-alignment: center;");
        box.setId(entry.getName());

        ImageView sprite = new ImageView(new Image(entry.getSprite(), 80, 80, true, true));

        Button spriteButton = new Button();
        spriteButton
                .setStyle("-fx-background-color: linear-gradient(to bottom, #99d0e6, #6bc1e6, #45accd, #219fd4);");
        spriteButton.setGraphic(sprite);
        spriteButton.setOnAction(e -> {
            try {
                handleMonClick(entry);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Label nameLabel = new Label(capitalize(entry.getName()));

        box.getChildren().addAll(spriteButton, nameLabel);
        return box;
    }

    // gives user option to add to their party
    @FXML
    public void handleMonClick(PokeEntry pokemon) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose Action");
        alert.setHeaderText("What would you like to do with " + capitalize(pokemon.getName()) + "?");

        ButtonType addButton = new ButtonType("Add to Party");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(addButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == addButton && party.getCount() < 6) {
                party.addMonParty(pokemon.getName());
            } else {
                alert.setHeaderText("Party is Full!");
            }
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // navigate back to the home page
    @FXML
    public void navHome() throws IOException {
        App.setRoot("home");
    }
}
