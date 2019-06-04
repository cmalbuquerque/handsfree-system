package desktopApp;


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
import speechToText.MainSpeech;
import sun.security.krb5.internal.crypto.Des;

public class DesktopMain extends Application {


    WebDriver driver;
    public static String currentUrl;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        //openBrowser
        openChrome();

        //start speech recognition
        MainSpeech mainSpeech = new MainSpeech();
        mainSpeech.start();

        //check if UrlChanged
        UrlUpdater urlUpdater = new UrlUpdater(driver);
        urlUpdater.start();


        //setting the position of the text
        Text appName = new Text();
        appName.setText(currentUrl);
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


    }



    public static void updateMenu(){


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


class UrlUpdater extends Thread {
    WebDriver driver;

    UrlUpdater(WebDriver driver) {
        this.driver = driver;
    }

    public void run() {
        try {

            while (true) {
                String pastUrl = driver.getCurrentUrl();
                Thread.sleep(500);
                String curUrl = driver.getCurrentUrl();

                if (!pastUrl.equals(curUrl)) {
                    System.out.println(curUrl);
                    DesktopMain.currentUrl=curUrl;

                }
            }


        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Not able to access webPage");
        }
    }
}