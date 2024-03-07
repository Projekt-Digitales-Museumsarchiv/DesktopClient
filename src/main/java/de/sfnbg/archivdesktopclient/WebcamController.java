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
import org.bytedeco.javacv.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

public class WebcamController implements Initializable {

    public Thread videoprocessor;
    @FXML
    public Button startButton;

    @FXML
    Canvas mycanvas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void bnStartClicked() {
        try {
            OpenCVFrameGrabber capture = OpenCVFrameGrabber.createDefault(0);
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

        } catch (FrameGrabber.Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Keine Kamera vorhanden");
            alert.show();
            e.printStackTrace();
        }
    }

    public void bnStopClicked() {
        this.videoprocessor.interrupt();
    }

    public void bnFertigClicked() {
        this.videoprocessor.interrupt();
        WritableImage writableImage = new WritableImage((int) mycanvas.getWidth(), (int) mycanvas.getHeight());
        mycanvas.snapshot(null, writableImage);
        DesktopController.writableImage = writableImage;
        Stage stage = (Stage) mycanvas.getScene().getWindow();
        stage.close();
    }
}
