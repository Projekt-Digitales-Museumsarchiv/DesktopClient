package de.sfnbg.archivdesktopclient;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class MainWindow {

    public Scene getScene() {
        HBox allCards = new HBox(new ImportCard(), new InterpreterCard(), new LookupCard());
        return new Scene(allCards);
    }


}
