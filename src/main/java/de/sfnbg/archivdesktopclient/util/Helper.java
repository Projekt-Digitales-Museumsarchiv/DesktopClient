package de.sfnbg.archivdesktopclient.util;

import de.sfnbg.archivdesktopclient.data.TransferRecord;
import lombok.Data;

import java.io.File;
import java.io.IOException;
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
                    , tessFile};
            System.out.println(params[0]);
            System.out.println(params[1]);
            System.out.println(params[2]);
            r.exec(params).waitFor();
            if (Files.exists(Path.of(outFile))) {
                TransferRecord.setFullText(Files.readString(Path.of(outFile)));
                System.out.println(TransferRecord.getFullText());
                return TransferRecord.getFullText();
            } else return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
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
            case DROP -> tempFileName += "DROP_";
            case SCAN -> tempFileName += "SCAN_";
            case IMPORT -> tempFileName += "IMPORT_";
            case CAM -> tempFileName += "CAM_";
        }
        tempFileName += UUID.randomUUID();
        tempFileName += extension;
        String tempDir = System.getProperty("java.io.tmpdir");

        File file = new File(tempDir, tempFileName);
        System.out.println(file.getPath());
        return file.getPath();
    }
}