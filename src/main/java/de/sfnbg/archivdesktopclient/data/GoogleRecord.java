package de.sfnbg.archivdesktopclient.data;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class GoogleRecord {
    String isbn10;
    String isbn13;
    String titel;
    String autor;
    String verlag;
    String jahr;
    BufferedImage cover;
}
