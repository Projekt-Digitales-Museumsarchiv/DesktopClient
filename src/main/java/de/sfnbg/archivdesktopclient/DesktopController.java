package de.sfnbg.archivdesktopclient;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.IOException;


public class DesktopController {

    public static WritableImage writableImage;
    public Canvas inputCanvas;

    public void onBnWebCamClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DesktopApplication.class.getResource("webcam-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 900);
        Stage stage=new Stage();
        stage.setTitle("Bild von Webcam holen");
        stage.setScene(scene);
        stage.showAndWait();
        if ( writableImage!=null) {
            inputCanvas.getGraphicsContext2D().drawImage(writableImage,0,0);
        }
    }

    public void onBnScannerClick() {

    }

    public void onBnUploadClick() {
    }
}