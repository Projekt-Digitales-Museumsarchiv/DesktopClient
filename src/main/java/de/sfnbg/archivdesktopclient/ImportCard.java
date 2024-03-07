package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ImportCard extends Card {
    public ImportCard() {

        var title = new Label("Bild importieren");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        var subtitle = new Label("Von der Webcam, vom Scanner oder aus einer Datei");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        var canvas = new Canvas(400, 500);
        Pane pane=new Pane();
        pane.getChildren().add(canvas);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,null,null)));
        this.setBody(pane);

        Button bnWebCam = new Button("Webcam");
        bnWebCam.setMinWidth(100);
        bnWebCam.setOnAction(e -> bnWebCamClicked());

        Button bnScan = new Button("Scan");
        bnScan.setMinWidth(100);

        Button bnLoad = new Button("Datei");
        bnLoad.setMinWidth(100);

        Button bnSave = new Button("Speichern");
        bnSave.setMinWidth(100);

        HBox footer = new HBox(bnWebCam, bnScan, bnLoad, bnSave);

        this.setFooter(footer);
    }

    private void bnWebCamClicked() {
        System.out.println("Klick Klick!");
    }
}
