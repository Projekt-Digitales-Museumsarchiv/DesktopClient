package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

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

        Button bnSearchIsbn = new Button("ISBN", new FontIcon(Feather.SEARCH));
        bnSearchIsbn.setMinWidth(50);

        Button bnSearchFulltext = new Button("Volltext", new FontIcon(Feather.SEARCH));
        bnSearchFulltext.setMinWidth(50);

        Button bnApply = new Button("Übernehmen", new FontIcon(Feather.CHECK));
        bnApply.setMinWidth(50);

        ToolBar footer = new ToolBar(bnSearchIsbn, bnSearchFulltext, bnApply);

        this.setFooter(footer);
    }
}
