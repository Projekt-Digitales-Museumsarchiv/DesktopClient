package de.sfnbg.archivdesktopclient.ui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MainWindow {

    public Scene getScene() {
        HBox allCards = new HBox(new ImportCard(), new InterpreterCard(), new LookupCard(), new StorageCard());
        return new Scene(allCards);
    }


}
