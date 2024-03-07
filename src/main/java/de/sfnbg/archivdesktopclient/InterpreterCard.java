package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class InterpreterCard extends Card {
    public InterpreterCard() {
        var title = new Label("Bild interpretieren");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        var subtitle = new Label("Barcode, ISBN im Text oder Volltext");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        TextArea ta = new TextArea();
        ta.setWrapText(true);
        ta.setMaxWidth(400);
        this.setBody(ta);

        Button bnBarcode = new Button("Barcode");
        bnBarcode.setMinWidth(100);

        Button bnIsbn = new Button("ISBN");
        bnIsbn.setMinWidth(100);

        Button bnFulltext = new Button("Volltext");
        bnFulltext.setMinWidth(100);

        Button bnClipboard = new Button("Kopieren");
        bnClipboard.setMinWidth(100);

        HBox footer = new HBox(bnBarcode, bnIsbn, bnFulltext, bnClipboard);

        this.setFooter(footer);
    }
}
