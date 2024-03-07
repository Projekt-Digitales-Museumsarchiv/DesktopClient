module de.sfnbg.archivdesktopclient {
    requires javafx.controls;
    requires javafx.fxml;
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

    opens de.sfnbg.archivdesktopclient to javafx.fxml;
    exports de.sfnbg.archivdesktopclient;
}