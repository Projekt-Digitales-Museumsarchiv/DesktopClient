package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class StorageCard extends Card {
    private static final Integer BUTTON_MIN_WIDTH = 50;
    private static final Integer CMB_PREF_WIDTH = 250;

    ComboBox<String> cmbCategory0;
    ComboBox<String> cmbCategory1;
    ComboBox<String> cmbCategory2;
    ComboBox<String> cmbCategory3;
    ComboBox<String> cmbRoom;
    ComboBox<String> cmbLocker;
    ComboBox<String> cmbShelf;

    public StorageCard() {
        Label title = new Label("Daten abspeichern");
        title.getStyleClass().add(Styles.TITLE_3);
        this.setHeader(title);

        Label subTitle = new Label("mit Vorwahl der Kategorien und des Ortes");
        subTitle.getStyleClass().add(Styles.TEXT_SMALL);
        this.setSubHeader(subTitle);

        cmbCategory0 = new ComboBox<>();
        cmbCategory0.setPrefWidth(CMB_PREF_WIDTH);
        cmbCategory0.setPlaceholder(new Label("Hauptkategorie..."));
        cmbCategory1 = new ComboBox<>();
        cmbCategory1.setPrefWidth(CMB_PREF_WIDTH);
        cmbCategory1.setPlaceholder(new Label("Unterkategorie 1..."));
        cmbCategory2 = new ComboBox<>();
        cmbCategory2.setPrefWidth(CMB_PREF_WIDTH);
        cmbCategory2.setPlaceholder(new Label("Unterkategorie 2..."));
        cmbCategory3 = new ComboBox<>();
        cmbCategory3.setPrefWidth(CMB_PREF_WIDTH);
        cmbCategory3.setPlaceholder(new Label("Unterkategorie 3..."));
        VBox categories = new VBox(cmbCategory0, cmbCategory1, cmbCategory2, cmbCategory3);

        cmbRoom = new ComboBox<>();
        cmbRoom.setPrefWidth(CMB_PREF_WIDTH);
        cmbRoom.setPlaceholder(new Label("Raum..."));
        cmbLocker = new ComboBox<>();
        cmbLocker.setPrefWidth(CMB_PREF_WIDTH);
        cmbLocker.setPlaceholder(new Label("Schrank..."));
        cmbShelf = new ComboBox<>();
        cmbShelf.setPrefWidth(CMB_PREF_WIDTH);
        cmbShelf.setPlaceholder(new Label("Fach..."));

        VBox place = new VBox(cmbRoom, cmbLocker, cmbShelf);
        HBox presets = new HBox(categories, new Separator(Orientation.VERTICAL), place);

        VBox body = new VBox(presets, new Separator(Orientation.HORIZONTAL));
        this.setBody(body);

        Button bnSave = new Button("Speichern", new FontIcon(Feather.CHECK));
        bnSave.setMinWidth(BUTTON_MIN_WIDTH);
        Button bnClear = new Button("Leeren", new FontIcon(Feather.DELETE));
        bnClear.setMinWidth(BUTTON_MIN_WIDTH);
        ToolBar toolBar = new ToolBar(bnSave, bnClear);
        this.setFooter(toolBar);

    }
}
