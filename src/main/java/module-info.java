module de.sfnbg.archivdesktopclient {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.swing;
    requires lombok;
    requires atlantafx.base;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires unirest.java.core;
    requires unirest.modules.gson;
    requires com.google.gson;

    uses java.awt.image.BufferedImage;

    exports de.sfnbg.archivdesktopclient;
    exports de.sfnbg.archivdesktopclient.ui;
    exports de.sfnbg.archivdesktopclient.data;
    exports de.sfnbg.archivdesktopclient.util;
}