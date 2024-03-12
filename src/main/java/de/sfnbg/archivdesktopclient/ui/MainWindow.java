package de.sfnbg.archivdesktopclient.ui;

import de.sfnbg.archivdesktopclient.data.MainRecord;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MainWindow {

    public static MainRecord mainRecord;

    public Scene getScene() {
        HBox allCards = new HBox(new ImportCard(mainRecord), new InterpreterCard(mainRecord), new LookupCard(mainRecord));
        return new Scene(allCards);
    }


}
