package de.sfnbg.archivdesktopclient;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DesktopController {


    public Label imageTitle;

    public void onbnWebCamClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DesktopApplication.class.getResource("webcam-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 900);
        Stage stage=new Stage();
        stage.setTitle("Bild von Webcam holen");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onbnScannerClick() {
    }

    public void onbnUploadClick() {
    }
}