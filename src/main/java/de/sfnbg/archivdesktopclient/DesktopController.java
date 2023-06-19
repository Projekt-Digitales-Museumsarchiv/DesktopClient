package de.sfnbg.archivdesktopclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DesktopController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}