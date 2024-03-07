package de.sfnbg.archivdesktopclient;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        MainWindow mainWindow=new MainWindow();
        Scene scene=mainWindow.getScene();
        stage.setScene(scene);
        stage.show();
        //FXMLLoader fxmlLoader = new FXMLLoader(DesktopApplication.class.getResource("main-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 420, 520);
        //stage.setTitle("Archiv Desktop-Client");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}