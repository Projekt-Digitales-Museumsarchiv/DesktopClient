package de.sfnbg.archivdesktopclient;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class LookupCard extends Card {

    public LookupCard() {
        Label title = new Label("Daten suchen");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        Label subtitle = new Label("Über ISBN oder Volltext");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        TableView tableView = new TableView();
        this.setBody(tableView);

        Button bnSearchIsbn = new Button("ISBN");
        bnSearchIsbn.setMinWidth(100);

        Button bnSearchFulltext = new Button("Volltext");
        bnSearchFulltext.setMinWidth(100);

        Button bnApply = new Button("Übernehmen");
        bnApply.setMinWidth(100);

        HBox footer = new HBox(bnSearchIsbn, bnSearchFulltext, bnApply);

        this.setFooter(footer);
    }
}
