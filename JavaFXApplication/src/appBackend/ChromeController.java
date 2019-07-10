/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appBackend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author DiogoFerreira
 */
public class ChromeController {

    WebDriver driver;
    public static String currentUrl;
    public static String setUrl;

    public ChromeController() {
        openChrome();
    }


    
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-data-dir=C:\\Users\\Andreia Patrocínio\\AppData\\Roaming\\Google\\Chrome\\User Data");
        options.addArguments("user-data-dir=C:/Users/DiogoFerreira/AppData/Local/Google/Chrome/User Data");

        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        driver.get("http://demo.dicoogle.com/tmg/dwsp/?seriesuid=2.25.40455800108579103101520589961762595191&studyuid=2.25.190719096823403736819300823791323488560&fbclid=IwAR0l77370gBkowE8iEuMfXP2hVXtekJ8Ogwdzu_KkrYcweXPBx5P8uEUjDU");

        //check if UrlChanged
//        UrlUpdater urlUpdater = new UrlUpdater(driver);
//        urlUpdater.start();
    }

    public void changeURL(String url) {
        driver.get(url);
    }
}

//class UrlUpdater extends Thread {
//
//    WebDriver driver;
//
//    UrlUpdater(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public void run() {
//        try {
//
//            while (true) {
//                String pastUrl = driver.getCurrentUrl();
//                Thread.sleep(500);
//                String curUrl = driver.getCurrentUrl();
//
//                if (!pastUrl.equals(curUrl)) {
//                    System.out.println("URL->" + curUrl);
//                    ChromeController.currentUrl = curUrl;
//
//                }
//            }
//
//        } catch (Exception e) {
//            // Throwing an exception
//            System.out.println("Not able to access webPage");
//        }
//    }
//}
