package com.preister.testfxapp;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartyController implements Initializable {

    @FXML
    private GridPane partyGrid;
    @FXML
    private Button dexButton;

    private Party party = App.getParty();

    // when navigated to the page loads with this
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int columns = 6;
        int row = 0;
        int col = 0;

        for (Pokemon pokemon : party.getParty()) {
            VBox pokeBox = createPokeBox(pokemon);
            partyGrid.add(pokeBox, col, row);

            col++;
            if (col == columns) {
                col = 0;
                row++;
            }
        }

        javafx.application.Platform.runLater(() -> {
            javafx.stage.Stage stage = (javafx.stage.Stage) partyGrid.getScene().getWindow();
            stage.setWidth(1000);
            stage.setHeight(300);
        });
    }

    // creates each vbox where pokemon data stored
    private VBox createPokeBox(Pokemon pokemon) {
        VBox box = new VBox(5);
        box.setStyle("-fx-alignment: center;");
        box.setId(pokemon.getName());

        ImageView sprite = new ImageView(new Image(pokemon.getSprite(), 120, 120, true, true));

        Button spriteButton = new Button();
        spriteButton
                .setStyle("-fx-background-color: linear-gradient(to bottom, #99d0e6, #6bc1e6, #45accd, #219fd4);");
        spriteButton.setGraphic(sprite);
        spriteButton.setOnAction(e -> {
            try {
                handleMonClick(pokemon);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Label nameLabel = new Label(capitalize(pokemon.getName()));

        box.getChildren().addAll(spriteButton, nameLabel);
        return box;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // sends alert, gives option to remove or view that pokemon
    @FXML
    public void handleMonClick(Pokemon pokemon) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose Action");
        alert.setHeaderText("What would you like to do with " + capitalize(pokemon.getName()) + "?");

        ButtonType viewButton = new ButtonType("View Details");
        ButtonType removeButton = new ButtonType("Remove");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(viewButton, removeButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == viewButton) {
                App.setSelectedPokemon(pokemon);
                App.setRoot("pokemon");
                javafx.application.Platform.runLater(() -> {
                    javafx.stage.Stage stage = App.getPrimaryStage();
                    stage.setWidth(400);
                    stage.setHeight(480);
                });
            } else if (result.get() == removeButton) {
                int index = party.getParty().indexOf(pokemon);
                if (index != -1) {
                    party.deleteMonParty(index);
                }
                App.setRoot("party");
            }
        }
    }

    // navigate home
    @FXML
    public void navHome() throws IOException {

        App.setRoot("home");

        javafx.application.Platform.runLater(() -> {
            Stage stage = App.getPrimaryStage();
            stage.setWidth(640);
            stage.setHeight(480);
        });
    }

}
