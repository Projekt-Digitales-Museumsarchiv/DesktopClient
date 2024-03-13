module de.sfnbg.archivdesktopclient {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.swing;
    requires org.bytedeco.javacv;
    requires org.bytedeco.opencv;

    requires org.bytedeco.javacpp;
    requires org.bytedeco.ffmpeg;
    requires org.bytedeco.videoinput;
    requires org.bytedeco.openblas;
    requires org.bytedeco.flycapture;

    requires org.bytedeco.javacv.platform;
    requires lombok;
    requires atlantafx.base;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires java.desktop;
    requires org.bytedeco.leptonica;
    requires org.bytedeco.leptonica.platform;
    requires org.bytedeco.leptonica.windows.x86_64;
    requires org.bytedeco.tesseract;
    requires org.bytedeco.tesseract.platform;
    requires org.bytedeco.tesseract.windows.x86_64;

    uses java.awt.image.BufferedImage;
    uses org.bytedeco.leptonica.PIX;
    //opens de.sfnbg.archivdesktopclient to javafx.fxml;
    exports de.sfnbg.archivdesktopclient;
    exports de.sfnbg.archivdesktopclient.ui;
    exports de.sfnbg.archivdesktopclient.data;
    exports de.sfnbg.archivdesktopclient.util;
}