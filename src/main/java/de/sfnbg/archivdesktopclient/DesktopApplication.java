package de.sfnbg.archivdesktopclient;

import atlantafx.base.theme.NordLight;
import de.sfnbg.archivdesktopclient.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        MainWindow mainWindow = new MainWindow();
        stage.setTitle("Archiv Desktop-Client");
        Scene scene = mainWindow.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}