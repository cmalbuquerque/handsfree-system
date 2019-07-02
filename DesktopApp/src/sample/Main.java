package sample;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Main extends Application {


    WebDriver driver;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws InterruptedException {


        openChrome();


        Text text = new Text();


        Thread.sleep(5000);
        String currentUrl = driver.getCurrentUrl();

        text.setText(currentUrl);


        //setting the position of the text
        text.setX(50);
        text.setY(50);

        //Creating a Group object
        Group root = new Group(text);

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

    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", "D:\\UA\\Ano4\\PI\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/DiogoFerreira/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://demo.dicoogle.com/tmg/dwsp/?seriesuid=2.25.40455800108579103101520589961762595191&studyuid=2.25.190719096823403736819300823791323488560&fbclid=IwAR0l77370gBkowE8iEuMfXP2hVXtekJ8Ogwdzu_KkrYcweXPBx5P8uEUjDU");

    }

}