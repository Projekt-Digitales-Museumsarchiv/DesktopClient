module de.sfnbg.archivdesktopclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.sfnbg.archivdesktopclient to javafx.fxml;
    exports de.sfnbg.archivdesktopclient;
}