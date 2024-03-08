package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class ImportCard extends Card {
    public static WritableImage writableImage;
    Canvas canvas;

    public ImportCard() {

        var title = new Label("Bild importieren");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        var subtitle = new Label("Von der Webcam, vom Scanner oder aus einer Datei");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        canvas = new Canvas(400, 500);
        Pane pane = new Pane();
        pane.getChildren().add(canvas);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        this.setBody(pane);

        Button bnWebCam = new Button("Webcam", new FontIcon(Feather.CAMERA));
        bnWebCam.setMinWidth(100);
        bnWebCam.setOnAction(e -> bnWebCamClicked());

        Button bnScan = new Button("Scan", new FontIcon(Feather.PAPERCLIP));
        bnScan.setMinWidth(100);

        Button bnLoad = new Button("Datei", new FontIcon(Feather.FILE));
        bnLoad.setMinWidth(100);

        Button bnSave = new Button("Speichern", new FontIcon(Feather.SAVE));
        bnSave.setMinWidth(100);

        HBox footer = new HBox(bnWebCam, bnScan, bnLoad, bnSave);

        this.setFooter(footer);
    }

    private void bnWebCamClicked() {
        WebcamWindow webcamWindow = new WebcamWindow();
        Scene scene = webcamWindow.getScene();
        Stage stage = new Stage();
        stage.setTitle("Bild von Webcam holen");
        stage.setScene(scene);
        stage.showAndWait();
        writableImage = webcamWindow.getWritableImage();
        if (writableImage != null) {
            canvas.getGraphicsContext2D().drawImage(writableImage, 0, 0);
        }
        System.out.println("Klick Klick!");
    }
}
