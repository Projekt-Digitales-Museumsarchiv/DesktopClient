package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import de.sfnbg.archivdesktopclient.data.MainRecord;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;

public class InterpreterCard extends Card {
    static final Integer BUTTON_MIN_WIDTH = 50;

    MainRecord mainRecord;

    public InterpreterCard(MainRecord mainRecord) {
        super();
        this.mainRecord = mainRecord;
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

        Button bnBarcode = new Button("Barcode", new FontIcon(Material2AL.BARCODE));
        bnBarcode.setMinWidth(BUTTON_MIN_WIDTH);

        Button bnIsbn = new Button("ISBN", new FontIcon(Material2MZ.SHORT_TEXT));
        bnIsbn.setMinWidth(BUTTON_MIN_WIDTH);

        Button bnFulltext = new Button("Volltext", new FontIcon(Material2MZ.TEXT_FIELDS));
        bnFulltext.setMinWidth(BUTTON_MIN_WIDTH);
        bnFulltext.setOnAction(e -> bnFulltextClicked());

        Button bnClipboard = new Button("Kopieren", new FontIcon(Feather.COPY));
        bnClipboard.setMinWidth(BUTTON_MIN_WIDTH);

        ToolBar footer = new ToolBar(bnBarcode, bnIsbn, bnFulltext, bnClipboard);

        this.setFooter(footer);
    }

    private void bnFulltextClicked() {
    }
}
