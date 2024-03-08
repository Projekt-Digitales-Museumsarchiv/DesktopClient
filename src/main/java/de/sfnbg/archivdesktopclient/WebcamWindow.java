package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.awt.image.BufferedImage;

public class WebcamWindow {
    public Thread videoProcessor;
    public Canvas canvas;

    @Getter
    public WritableImage writableImage;

    public Scene getScene() {
        try {
            Card card = new Card();
            canvas = new Canvas(400, 400);
            card.setBody(canvas);
            Button bnStart = new Button("Start");
            bnStart.setOnAction(e -> bnStartClicked());
            Button bnStop = new Button("Stop");
            bnStop.setOnAction(e -> bnStopClicked());
            Button bnApply = new Button("Übernehmen");
            bnApply.setOnAction(e -> bnApplyClicked());
            HBox buttons = new HBox(bnStart, bnStop, bnApply);
            card.setFooter(buttons);
            return new Scene(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bnApplyClicked() {
        this.videoProcessor.interrupt();
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);
        this.writableImage = writableImage;
        Stage stage = (Stage) canvas.getScene().getWindow();
        stage.close();
    }

    private void bnStartClicked() {
        try {
            OpenCVFrameGrabber capture = OpenCVFrameGrabber.createDefault(0);
            capture.start();

            Java2DFrameConverter javaConverter = new Java2DFrameConverter();

            this.videoProcessor = new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Frame frame = capture.grab();
                        Platform.runLater(() -> {
                            BufferedImage image = javaConverter.getBufferedImage(frame, 1.0, false, null);
                            canvas.getGraphicsContext2D().drawImage(SwingFXUtils.toFXImage(image, null), 0, 0);
                        });
                    }
                    capture.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            this.videoProcessor.start();

        } catch (FrameGrabber.Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Keine Kamera vorhanden");
            alert.show();
            e.printStackTrace();
        }
    }

    public void bnStopClicked() {
        this.videoProcessor.interrupt();
    }
}
