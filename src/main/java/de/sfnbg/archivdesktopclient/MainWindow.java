package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class MainWindow {

    public Scene getScene() {
        HBox allCards=new HBox(createImportCard(),createInterpreterCard(),createLookupCard());
        return new Scene(allCards);
    }

    private Node createLookupCard() {
        var lookupCard = new Card();

        var title = new Label("Daten suchen");
        title.getStyleClass().add((Styles.TITLE_4));
        lookupCard.setHeader(title);

        var subtitle = new Label("Über ISBN oder Volltext");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        lookupCard.setSubHeader(subtitle);

        TableView tableView=new TableView();
        lookupCard.setBody(tableView);

        Button bnSearchIsbn = new Button("ISBN");
        bnSearchIsbn.setMinWidth(100);

        Button bnSearchFulltext = new Button("Volltext");
        bnSearchFulltext.setMinWidth(100);

        Button bnApply=new Button("Übernehmen");
        bnApply.setMinWidth(100);

        HBox footer=new HBox(bnSearchIsbn,bnSearchFulltext,bnApply);

        lookupCard.setFooter(footer);

        return lookupCard;
    }

    private Card createImportCard() {
        var importCard = new Card();

        var title = new Label("Bild importieren");
        title.getStyleClass().add((Styles.TITLE_4));
        importCard.setHeader(title);

        var subtitle = new Label("Von der Webcam, vom Scanner oder aus einer Datei");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        importCard.setSubHeader(subtitle);

        var canvas = new Canvas(400, 500);
        importCard.setBody(canvas);

        Button bnWebCam = new Button("Webcam");
        bnWebCam.setMinWidth(100);

        Button bnScan = new Button("Scan");
        bnScan.setMinWidth(100);

        Button bnLoad=new Button("Datei");
        bnLoad.setMinWidth(100);

        Button bnSave=new Button("Speichern");
        bnSave.setMinWidth(100);

        HBox footer=new HBox(bnWebCam,bnScan,bnLoad,bnSave);

        importCard.setFooter(footer);

        return importCard;
    }

    private Card createInterpreterCard() {
        var interpreterCard = new Card();

        var title = new Label("Bild interpretieren");
        title.getStyleClass().add((Styles.TITLE_4));
        interpreterCard.setHeader(title);

        var subtitle = new Label("Barcode, ISBN im Text oder Volltext");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        interpreterCard.setSubHeader(subtitle);

        TextArea ta=new TextArea();
        ta.setWrapText(true);
        ta.setMaxWidth(400);
        interpreterCard.setBody(ta);

        Button bnBarcode = new Button("Barcode");
        bnBarcode.setMinWidth(100);

        Button bnIsbn = new Button("ISBN");
        bnIsbn.setMinWidth(100);

        Button bnFulltext=new Button("Volltext");
        bnFulltext.setMinWidth(100);

        Button bnClipboard=new Button("Kopieren");
        bnClipboard.setMinWidth(100);

        HBox footer=new HBox(bnBarcode,bnIsbn,bnFulltext,bnClipboard);

        interpreterCard.setFooter(footer);

        return interpreterCard;
    }

}
