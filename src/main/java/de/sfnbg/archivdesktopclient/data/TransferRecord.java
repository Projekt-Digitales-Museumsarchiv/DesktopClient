package de.sfnbg.archivdesktopclient.data;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class TransferRecord {
    @Getter
    @Setter
    static BufferedImage image;
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
