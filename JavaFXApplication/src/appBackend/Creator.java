package appBackend;

import Speech2Text.MainSpeech;
import machineLearning.MListener;
import com.leapmotion.leap.Controller;
import javafxapplication.JavaFXApplication;

public class Creator {

    public static void main(String[] args) {

//        MListener listener = new MListener();
//        Controller controller = new Controller();
//        System.out.println("adding listener!!!!!!!!!!!!!!!!!!!!!");
//        controller.addListener(listener);
//        System.out.println("done ma firend");
        //start chromeController Thread
        ChromeController chrome = new ChromeController();
        chrome.start();

        //start speech recognition
        MainSpeech mainSpeech = new MainSpeech();
        mainSpeech.start();
        
        //start UI Thread
        JavaFXApplication.startUI(args);
    }

}
