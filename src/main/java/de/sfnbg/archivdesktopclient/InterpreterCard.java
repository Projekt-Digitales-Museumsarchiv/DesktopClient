package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class InterpreterCard extends Card {
    static final Integer BUTTON_MIN_WIDTH = 50;

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

        Button bnBarcode = new Button("Barcode",new FontIcon(Feather.LINK_2));
        bnBarcode.setMinWidth(BUTTON_MIN_WIDTH);

        Button bnIsbn = new Button("ISBN",new FontIcon(Feather.SEARCH));
        bnIsbn.setMinWidth(BUTTON_MIN_WIDTH);

        Button bnFulltext = new Button("Volltext",new FontIcon(Feather.SEARCH));
        bnFulltext.setMinWidth(BUTTON_MIN_WIDTH);

        Button bnClipboard = new Button("Kopieren",new FontIcon(Feather.COPY));
        bnClipboard.setMinWidth(BUTTON_MIN_WIDTH);

        ToolBar footer = new ToolBar(bnBarcode, bnIsbn, bnFulltext, bnClipboard);

        this.setFooter(footer);
    }
}
