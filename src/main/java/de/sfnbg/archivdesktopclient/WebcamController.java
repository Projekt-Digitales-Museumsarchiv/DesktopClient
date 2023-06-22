package de.sfnbg.archivdesktopclient;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class WebcamController implements Initializable {

    public Thread videoprocessor;
    @FXML
    public Button startButton;

    @FXML
    Canvas mycanvas;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void bnStartClicked() throws FrameGrabber.Exception {
        //Handler für Schliessen nachträglich eingefügt, sonst wirds zu umständlich
        startButton.getScene().getWindow().setOnCloseRequest(e -> this.onCloseRequest());
        OpenCVFrameGrabber capture= OpenCVFrameGrabber.createDefault(0);
        capture.start();

        Java2DFrameConverter javaConverter = new Java2DFrameConverter();

        this.videoprocessor = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Frame frame = capture.grab();
                    Platform.runLater(() -> {
                        BufferedImage image = javaConverter.getBufferedImage(frame, 1.0, false, null);
                        mycanvas.getGraphicsContext2D().drawImage(SwingFXUtils.toFXImage(image, null), 0, 0);
                    });
                }
                capture.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        this.videoprocessor.start();

    }

    public void onCloseRequest() {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.show();
        this.videoprocessor.interrupt();
    }
    public void bnStopClicked() {
        this.videoprocessor.interrupt();
    }

    public void bnFertigClicked() {
        WritableImage writableImage=new WritableImage((int) mycanvas.getWidth(), (int) mycanvas.getHeight());
        mycanvas.snapshot(null,writableImage);
        DesktopController.writableImage=writableImage;
        Stage stage = (Stage) mycanvas.getScene().getWindow();
        stage.close();


    }
}
