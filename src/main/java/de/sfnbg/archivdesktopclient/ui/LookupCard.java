package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import de.sfnbg.archivdesktopclient.data.TransferRecord;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import kong.unirest.core.Unirest;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class LookupCard extends Card {
    TableView tableView;

    public LookupCard() {
        Label title = new Label("Daten suchen");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        Label subtitle = new Label("Über ISBN oder Volltext");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        tableView = new TableView();
        this.setBody(tableView);

        Button bnSearchIsbn = new Button("ISBN", new FontIcon(Feather.SEARCH));
        bnSearchIsbn.setMinWidth(50);
        bnSearchIsbn.setOnAction(e -> onBnSearchIsbnClicked());

        Button bnSearchFulltext = new Button("Volltext", new FontIcon(Feather.SEARCH));
        bnSearchFulltext.setMinWidth(50);
        bnSearchFulltext.setOnAction(e -> onBnSearchFulltextClicked());

        Button bnApply = new Button("Übernehmen", new FontIcon(Feather.CHECK));
        bnApply.setMinWidth(50);

        ToolBar footer = new ToolBar(bnSearchIsbn, bnSearchFulltext, bnApply);

        this.setFooter(footer);
    }

    private void onBnSearchFulltextClicked() {
        getGoogleBooks(TransferRecord.getFullText(), "");
    }

    private void getGoogleBooks(String searchText, String prefix) {
//        GResponse gResponse = Unirest.get("https://www.googleapis.com/books/v1/volumes")
//                .queryString("q", prefix + searchText)
//                .asObject(GResponse.class)
//                .getBody();
        String s = Unirest.get("https://www.googleapis.com/books/v1/volumes")
                .queryString("q", prefix + searchText)
                .asString()
                .ifFailure(Error.class, r -> {
                    Error e = r.getBody();
                    System.out.println(e.getMessage());

                })
                .getBody();
        System.out.println(s);

        //        ObservableList<GoogleRecord> googleRecords = null;
//        String url = "https://www.googleapis.com/books/v1/volumes?q=" + prefix + searchText;
//        try {
//            HttpResponse<JsonNode> apiResponse = Unirest.get(url).asJson();
//            String responseJsonAsString = apiResponse.getBody().toString();
//            System.out.println(responseJsonAsString);
//
//            tableView.setItems(googleRecords);
//        } catch (UnirestException e) {
//            throw new RuntimeException(e);
//        }

    }

    private void onBnSearchIsbnClicked() {
        getGoogleBooks(TransferRecord.getFullText(), "isbn:");

    }
}
