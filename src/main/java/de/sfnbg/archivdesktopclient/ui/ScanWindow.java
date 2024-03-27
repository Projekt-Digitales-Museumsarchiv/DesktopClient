package de.sfnbg.archivdesktopclient.ui;

import atlantafx.base.controls.Card;
import de.sfnbg.archivdesktopclient.data.TransferRecord;
import de.sfnbg.archivdesktopclient.util.Helper;
import de.sfnbg.archivdesktopclient.util.TempType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Getter;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2MZ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Getter
public class ScanWindow {
    static final Integer BUTTON_MIN_WIDTH = 20;

    private ImageView imageView;

    private ComboBox<String> cmbResolution;
    private ComboBox<String> cmbFormat;
    private ComboBox<String> cmbColor;
    private ComboBox<String> cmbOutput;
    private CheckBox cbAdf;

    public Scene getScene() {
        try {
            Card card = new Card();

            Button bnSelect = new Button("Scanner...");
            bnSelect.setOnAction(e -> bnSelectClicked());
            bnSelect.setMinWidth(BUTTON_MIN_WIDTH);

            cmbResolution = new ComboBox<>();
            cmbResolution.setMinWidth(BUTTON_MIN_WIDTH);
            cmbResolution.getItems().addAll("100dpi", "200dpi", "300dpi");
            cmbResolution.getSelectionModel().select(1);

            cmbFormat = new ComboBox<>();
            cmbFormat.setMinWidth(BUTTON_MIN_WIDTH);
            cmbFormat.getItems().addAll("A3", "A4", "A5", "A6");
            cmbFormat.getSelectionModel().select(1);

            cmbColor = new ComboBox<>();
            cmbColor.setMinWidth(BUTTON_MIN_WIDTH);
            cmbColor.getItems().addAll("Farbe", "Grau", "SW");
            cmbColor.getSelectionModel().select(1);

            cmbOutput = new ComboBox<>();
            cmbOutput.setMinWidth(BUTTON_MIN_WIDTH);
            cmbOutput.getItems().addAll("JPG-25", "JPG-50", "JPG-75", "JPG-100");
            cmbOutput.getSelectionModel().select(2);

            cbAdf = new CheckBox("Einzug");

            ToolBar header = new ToolBar(bnSelect, cmbResolution, cmbFormat, cmbColor, cmbOutput, cbAdf);

            card.setHeader(header);

            imageView = new ImageView();
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(500);
            imageView.setFitHeight(400);

            imageView.setPickOnBounds(true);
            card.setBody(imageView);

            Button bnScan = new Button("Scannen", new FontIcon(Material2MZ.SCANNER));
            bnScan.setOnAction(e -> bnScanClicked());
            bnScan.setMinWidth(BUTTON_MIN_WIDTH);
            Button bnApply = new Button("Übernehmen", new FontIcon(Feather.CHECK));
            bnApply.setOnAction(e -> bnApplyClicked());
            bnApply.setMinWidth(BUTTON_MIN_WIDTH);
            ToolBar buttons = new ToolBar(bnScan, bnApply);
            card.setFooter(buttons);
            return new Scene(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bnScanClicked() {
        try {
            Runtime r = Runtime.getRuntime();
            File outFile = new File(Helper.getTempFileName(TempType.SCAN));
            r.exec(new String[]{"CmdTwain"
                            , "-c"
                            , getInit()
                            , getOutput(cmbOutput.getValue())
                            , outFile.getPath()})
                    .waitFor();
            if (outFile.exists()) {
                try {
                    imageView.setImage(new Image(new FileInputStream(outFile)));
                    TransferRecord.setFileName(outFile.getPath());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getOutput(String value) {
        if (value.contains("JPG"))
            return value.substring(4);
        else {
            return value;
        }
    }

    private String getFormat(String value) {
        return "PAPER " + value;
    }

    private String getResolution(String value) {
        return "DPI " + value.substring(0, 3);
    }

    private String getInit() {
        return "\"" + getFormat(cmbFormat.getValue()) + " " + getResolution(cmbResolution.getValue()) + " " + getColor(cmbColor.getValue()) + " " + getAdf(cbAdf.isSelected()) + "\"";
    }

    private String getAdf(Boolean value) {
        if (value)
            return "ADF 1";
        else
            return "ADF 0";
    }

    private String getColor(String value) {
        return switch (value) {
            case "Farbe" -> "RGB";
            case "SW" -> "BW";
            default -> "GRAY";
        };
    }

    private void bnSelectClicked() {
        try {
            Runtime r = Runtime.getRuntime();
            r.exec("CmdTwain /SOURCE");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void bnApplyClicked() {
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }

}
