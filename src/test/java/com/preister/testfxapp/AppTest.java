package com.preister.testfxapp;

import javafx.application.Application;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    @Start
    public void onStart(Stage stage) throws Exception {
        Application app = new App();
        app.start(stage);
    }

    // tests view funcitonality in party
    @Test
    public void viewCharizard(FxRobot robot) {

        robot.clickOn("#dexButton");
        robot.clickOn("#venusaur");
        robot.clickOn("Add to Party");
        robot.clickOn("#charizard");
        robot.clickOn("Add to Party");

        robot.clickOn("#homeButton");
        robot.clickOn("#partyButton");
        robot.clickOn("#charizard");
        robot.clickOn("View Details");

    }

    // tests removal
    @Test
    public void addAndDeleteParty(FxRobot robot) {

        robot.clickOn("#dexButton");
        robot.clickOn("#bulbasaur");
        robot.clickOn("Add to Party");
        robot.clickOn("#charmander");
        robot.clickOn("Add to Party");
        robot.clickOn("#beedrill");
        robot.clickOn("Add to Party");
        robot.clickOn("#butterfree");
        robot.clickOn("Add to Party");
        robot.clickOn("#kakuna");
        robot.clickOn("Add to Party");

        robot.clickOn("#homeButton");
        robot.clickOn("#partyButton");
        robot.clickOn("#bulbasaur");
        robot.clickOn("Remove");
        robot.clickOn("#charmander");
        robot.clickOn("Remove");
        robot.clickOn("#beedrill");
        robot.clickOn("Remove");
        robot.clickOn("#butterfree");
        robot.clickOn("Remove");
        robot.clickOn("#kakuna");
        robot.clickOn("Remove");
        robot.clickOn("#backToParty");
        robot.clickOn("#homeButton");

    }

    // tries to add more than the max of 6 in your party
    @Test
    public void tryAddingMoreThanSix(FxRobot robot) {

        robot.clickOn("#dexButton");
        robot.clickOn("#bulbasaur");
        robot.clickOn("Add to Party");
        robot.clickOn("#charmander");
        robot.clickOn("Add to Party");
        robot.clickOn("#beedrill");
        robot.clickOn("Add to Party");
        robot.clickOn("#butterfree");
        robot.clickOn("Add to Party");
        robot.clickOn("#charmander");
        robot.clickOn("Add to Party");
        robot.clickOn("#metapod");
        robot.clickOn("Add to Party");
        robot.clickOn("#venusaur");
        robot.clickOn("Add to Party");
        robot.clickOn("#charmeleon");
        robot.clickOn("Add to Party");

        robot.clickOn("#homeButton");
        robot.clickOn("#partyButton");
        robot.clickOn("#bulbasaur");
        robot.clickOn("Remove");

    }

}
