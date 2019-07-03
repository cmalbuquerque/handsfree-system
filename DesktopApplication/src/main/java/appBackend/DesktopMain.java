package appBackend;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import Speech2Text.MainSpeechAppProj;
import sun.security.krb5.internal.crypto.Des;

public class DesktopMain extends Application {

    public static void startUI(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        //openBrowser
        //start speech recognition
        MainSpeechAppProj mainSpeech = new MainSpeechAppProj();
//        MainSpeech mainSpeech = new MainSpeech();
        mainSpeech.start();



        //setting the position of the text
        Text appName = new Text();
        //appName.setText(currentUrl);
        appName.setX(50);
        appName.setY(50);

        //Creating a Group object
        Group root = new Group(appName);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Sample Application");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

        //        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        double screenRightEdge = primScreenBounds.getMaxX() ;
//        stage.setX(screenRightEdge);
//        System.out.println(primScreenBounds.getWidth());
//        stage.setY(primScreenBounds.getMinY());
//        stage.setWidth(0);
//        stage.setHeight(primScreenBounds.getHeight());
//
//        Timeline timeline = new Timeline();
//
//
//        WritableValue<Double> writableWidth = new WritableValue<Double>() {
//            @Override
//            public Double getValue() {
//                return stage.getWidth();
//            }
//
//            @Override
//            public void setValue(Double value) {
//                stage.setX(screenRightEdge - value);
//                stage.setWidth(value);
//            }
//        };
//
//
//        KeyValue kv = new KeyValue(writableWidth, 200d);
//        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
//        timeline.getKeyFrames().addAll(kf);
//        timeline.play();
//        stage.setOnCloseRequest(event -> {
//            Timeline timeline1 = new Timeline();
//            KeyFrame endFrame = new KeyFrame(Duration.millis(1000), new KeyValue(writableWidth, 0.0));
//            timeline1.getKeyFrames().add(endFrame);
//            timeline1.setOnFinished(e -> Platform.runLater(() -> stage.hide()));
//            timeline1.play();
//            event.consume();
//        });
    }

    public static void updateMenu() {

    }

}
