package de.sfnbg.archivdesktopclient.data;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

public class TransferRecord {
    @Getter
    @Setter
    static Image image;
    @Getter
    @Setter
    static String fileName;
    @Getter
    @Setter
    static String fullText;
    @Getter
    @Setter
    static String isbn;
}
