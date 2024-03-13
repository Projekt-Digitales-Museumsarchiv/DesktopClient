package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import de.sfnbg.archivdesktopclient.data.TransferRecord;
import de.sfnbg.archivdesktopclient.util.Helper;
import de.sfnbg.archivdesktopclient.util.TempType;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebcamWindow {
    Thread videoProcessor;
    ImageView imageView;
    BufferedImage localImage;

    static final Integer BUTTON_MIN_WIDTH = 50;

    public Scene getScene() {
        try {
            Card card = new Card();

            imageView = new ImageView();
            imageView.setFitWidth(500);
            imageView.setFitHeight(400);
            card.setBody(imageView);

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
            TransferRecord.setImage(localImage);
        }

        File outFile;
        try {
            outFile = new File(Helper.getTempFileName(TempType.CAM));
            ImageIO.write(localImage, "jpg", outFile);
            TransferRecord.setFileName(outFile.getPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage) imageView.getScene().getWindow();
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
                            localImage = javaConverter.getBufferedImage(frame, 1.0, false, null);
                            imageView.setImage(SwingFXUtils.toFXImage(localImage, null));
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
