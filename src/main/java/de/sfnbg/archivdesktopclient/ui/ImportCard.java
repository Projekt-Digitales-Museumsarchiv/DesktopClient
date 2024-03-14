package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import atlantafx.base.theme.Styles;
import de.sfnbg.archivdesktopclient.data.TransferRecord;
import de.sfnbg.archivdesktopclient.util.Helper;
import de.sfnbg.archivdesktopclient.util.TempType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImportCard extends Card {
    ImageView imageView;
    static final Integer BUTTON_MIN_WIDTH = 50;

    public ImportCard() {

        Label title = new Label("Bild importieren");
        title.getStyleClass().add((Styles.TITLE_3));
        this.setHeader(title);

        Label subtitle = new Label("Von der Webcam, vom Scanner oder aus einer Datei");
        subtitle.getStyleClass().add((Styles.TEXT_SMALL));
        this.setSubHeader(subtitle);

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPickOnBounds(true);
        imageView.setOnDragDropped(this::Dropped);
        imageView.setOnDragOver(this::DragOver);

        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        this.setBody(pane);

        this.setFooter(getToolBar());
    }

    private ToolBar getToolBar() {
        Button bnWebCam = new Button("Webcam", new FontIcon(Feather.CAMERA));
        bnWebCam.setMinWidth(100);
        bnWebCam.setOnAction(e -> bnWebCamClicked());
        bnWebCam.setMinWidth(BUTTON_MIN_WIDTH);


        Button bnScan = new Button("Scan", new FontIcon(Feather.PAPERCLIP));
        bnScan.setMinWidth(100);
        bnScan.setMinWidth(BUTTON_MIN_WIDTH);
        bnScan.setOnAction(e -> bnScanClicked());

        Button bnLoad = new Button("Datei", new FontIcon(Feather.FILE));
        bnLoad.setMinWidth(100);
        bnLoad.setMinWidth(BUTTON_MIN_WIDTH);
        bnLoad.setOnAction(e -> bnLoadClicked());

        Button bnSave = new Button("Speichern", new FontIcon(Feather.SAVE));
        bnSave.setMinWidth(100);
        bnSave.setMinWidth(BUTTON_MIN_WIDTH);
        bnSave.setOnAction(e -> bnSaveClicked());

        return new ToolBar(bnWebCam, bnScan, bnLoad, bnSave);
    }

    private void bnScanClicked() {
        ScanWindow scanWindow = new ScanWindow();
        Scene scene = scanWindow.getScene();
        Stage stage = new Stage();
        stage.setTitle("Bild von Scanner holen");
        stage.setScene(scene);
        stage.showAndWait();
        if (scanWindow.getImageView() != null)
            if (scanWindow.getImageView().getImage() != null)
                imageView.setImage(scanWindow.getImageView().getImage());
    }

    private void bnSaveClicked() {
        if (imageView.getImage() != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG Dateien", "*.jpg"), new FileChooser.ExtensionFilter("Alle Dateien", "*.*"));

            File selectedFile = fileChooser.showSaveDialog(getScene().getWindow());
            if (selectedFile != null) {
                saveFile(selectedFile);

            }

        }
    }

    private void saveFile(File selectedFile) {
        String fileName = selectedFile.getName();
        //String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            ImageIO.write(TransferRecord.getImage(), "JPG", selectedFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void bnLoadClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG Dateien", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (selectedFile != null) {
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                imageView.setImage(SwingFXUtils.toFXImage(image, null));
                TransferRecord.setImage(image);
                File outFile = new File(Helper.getTempFileName(TempType.IMPORT));
                TransferRecord.setFileName(outFile.getPath());
                saveFile(outFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void DragOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasImage() || dragboard.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void Dropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles() || dragboard.hasImage()) {
            try {
                //Image image = new Image(new FileInputStream(dragboard.getFiles().get(0)));
                //BufferedImage image = new BufferedImage(new FileInputStream(dragboard.getFiles().get(0)));
                File file = dragboard.getFiles().get(0);
                BufferedImage image = ImageIO.read(file);
                imageView.setImage(SwingFXUtils.toFXImage(image, null));
                TransferRecord.setImage(image);
                File outFile = new File(Helper.getTempFileName(TempType.DROP));
                saveFile(outFile);
                TransferRecord.setFileName(outFile.getPath());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void bnWebCamClicked() {
        WebcamWindow webcamWindow = new WebcamWindow();
        Scene scene = webcamWindow.getScene();
        Stage stage = new Stage();
        stage.setTitle("Bild von Webcam holen");
        stage.setScene(scene);
        stage.showAndWait();
        if (TransferRecord.getImage() != null) {
            imageView.setImage(SwingFXUtils.toFXImage(TransferRecord.getImage(), null));
        }
    }
}
