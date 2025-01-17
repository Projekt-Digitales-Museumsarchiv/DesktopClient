package de.sfnbg.archivdesktopclient.util;

import de.sfnbg.archivdesktopclient.data.TransferRecord;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


@Data
public class Helper {

    public static String useTesseract() {
        Runtime r = Runtime.getRuntime();
        String outFile = getTempFileName(TempType.OCR);
        String tessFile = outFile.replaceAll(".txt", "");
        try {
            String[] params = new String[]{
                    "tesseract"
                    , TransferRecord.getFileName()
                    , tessFile
                    , "-l"
                    , "deu"};
            r.exec(params).waitFor();
            Path path = Path.of(outFile);
            if (Files.exists(path)) {
                TransferRecord.setFullText(Files.readString(path));
                return TransferRecord.getFullText();
            } else return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String useZBar() {
        try {
            Runtime r = Runtime.getRuntime();
            String[] params = new String[]{
                    "zbarimg"
                    , TransferRecord.getFileName()};

            Process process = r.exec(params);
            process.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s;
            String outText = "";
            while ((s = stdInput.readLine()) != null) {
                if (!s.isEmpty())
                    outText += s.replaceAll("EAN-13:", "");
            }
            TransferRecord.setFullText(outText);
            TransferRecord.setIsbn(outText);
            return outText;
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void useCommandCam(String finalFileName) {
        String tempFileName = getTempFileName(TempType.GRAB);
        try {
            Runtime r = Runtime.getRuntime();
            String[] params = new String[]{
                    "commandcam"
                    , "/filename"
                    , tempFileName};

            Process process = r.exec(params);
            process.waitFor();
            BufferedImage image = ImageIO.read(new File(tempFileName));
            ImageIO.write(image, "JPG", new File(finalFileName));
            TransferRecord.setFileName(finalFileName);
            TransferRecord.setImage(image);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String getTempFileName(TempType tempType) {
        String tempFileName = "ArchDC_";
        String extension = ".jpg";
        switch (tempType) {
            case OCR -> {
                extension = ".txt";
                tempFileName += "OCR_";
            }
            case GRAB -> {
                extension = ".bmp";
                tempFileName += "GRAB_";
            }
            case DROP -> tempFileName += "DROP_";
            case SCAN -> tempFileName += "SCAN_";
            case IMPORT -> tempFileName += "IMPORT_";
            case CAM -> tempFileName += "CAM_";
        }
        tempFileName += UUID.randomUUID();
        tempFileName += extension;
        String tempDir = System.getProperty("java.io.tmpdir");

        File file = new File(tempDir, tempFileName);
        return file.getPath();
    }
}