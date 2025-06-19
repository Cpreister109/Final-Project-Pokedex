package com.preister.testfxapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label results;

    // goes to dex
    @FXML
    private void navDex() throws IOException {
        App.setRoot("dex");
    }

    // goes to party
    @FXML
    private void navParty() throws IOException {
        App.setRoot("party");
    }
}
