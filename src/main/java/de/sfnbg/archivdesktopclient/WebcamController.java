package de.sfnbg.archivdesktopclient;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class WebcamController implements Initializable {

    public Thread videoprocessor;
    public ComboBox cameras;
    public Button startButton;
    public Button stopButton;

    @FXML
    Canvas mycanvas;

    @FXML
    BorderPane frmWebcam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void bnStartClicked(MouseEvent mouseEvent) throws FrameGrabber.Exception {
        //String[] cameras=OpenCVFrameGrabber.getDeviceDescriptions();
        OpenCVFrameGrabber capture= OpenCVFrameGrabber.createDefault(0);
        capture.start();

//        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter javaConverter = new Java2DFrameConverter();

//        canvas.setHeight(capture.getImageHeight());
//        canvas.setWidth(capture.getImageWidth());
//        Scene scene = new Scene(root, capture.getImageWidth(), capture.getImageHeight());


        this.videoprocessor = new Thread(() -> {
//            File output = new File("C:/Work/test.avi");
//            OpenCVFrameRecorder recorder = new OpenCVFrameRecorder(output, capture.getImageWidth(), capture.getImageHeight());
            try {
//                recorder.setVideoCodec(VideoWriter.fourcc(( 'M', 'J', 'P', 'G'));
//                recorder.start();

                while (true) {
                    if(Thread.currentThread().isInterrupted()){
                        break;
                    }
                    Frame frame = capture.grab();
                    Platform.runLater(()->{
                        BufferedImage image = javaConverter.getBufferedImage(frame, 1.0, false, null);
                        mycanvas.getGraphicsContext2D().drawImage(SwingFXUtils.toFXImage(image, null),0,0);
                    });
//                    recorder.record(frame);
                }
//                recorder.stop();
                capture.release();
//                recorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        //frmWebcam.getParent().getScene().getWindow().setOnCloseRequest(e->this.videoprocessor.interrupt());
        this.videoprocessor.start();

    }

    public void bnStopClicked(MouseEvent mouseEvent) {
        this.videoprocessor.interrupt();
    }
}
