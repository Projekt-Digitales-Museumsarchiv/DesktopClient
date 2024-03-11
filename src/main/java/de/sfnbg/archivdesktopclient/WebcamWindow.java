package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import lombok.Getter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.image.BufferedImage;

public class WebcamWindow {
    public Thread videoProcessor;
    public Canvas canvas;

    static final Integer BUTTON_MIN_WIDTH = 50;

    @Getter
    public WritableImage writableImage;

    public Scene getScene() {
        try {
            Card card = new Card();
            canvas = new Canvas(400, 400);
            card.setBody(canvas);
            Button bnStart = new Button("Start", new FontIcon(Feather.PLAY));
            bnStart.setOnAction(e -> bnStartClicked());
            bnStart.setMinWidth(BUTTON_MIN_WIDTH);
            Button bnStop = new Button("Stop", new FontIcon(Feather.PAUSE));
            bnStop.setOnAction(e -> bnStopClicked());
            bnStop.setMinWidth(BUTTON_MIN_WIDTH);
            Button bnApply = new Button("Übernehmen", new FontIcon(Feather.CHECK));
            bnApply.setOnAction(e -> bnApplyClicked());
            bnApply.setMinWidth(BUTTON_MIN_WIDTH);
            ToolBar buttons = new ToolBar(bnStart, bnStop, bnApply);
            card.setFooter(buttons);
            return new Scene(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bnApplyClicked() {
        if (this.videoProcessor != null) {
            this.videoProcessor.interrupt();
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage);
            this.writableImage = writableImage;
        }
        Stage stage = (Stage) canvas.getScene().getWindow();
        stage.close();
    }

    private void bnStartClicked() {
        try (Java2DFrameConverter javaConverter = new Java2DFrameConverter()) {
            OpenCVFrameGrabber capture = OpenCVFrameGrabber.createDefault(0);
            capture.start();

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
                    System.out.println(e.getMessage());
                }
            });

            this.videoProcessor.start();

        } catch (FrameGrabber.Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Keine Kamera vorhanden");
            alert.show();
            System.out.println(e.getMessage());
        }
    }

    public void bnStopClicked() {

        if (this.videoProcessor != null)
            this.videoProcessor.interrupt();
    }
}
