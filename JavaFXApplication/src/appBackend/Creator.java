package appBackend;

import Speech2Text.MainSpeech;
import Emulator.GesturesEmulator;
import machineLearning.MListener;
import com.leapmotion.leap.Controller;
import javafxapplication.JavaFXApplication;

public class Creator {

    public static void main(String[] args) {

        GesturesEmulator emulator = new GesturesEmulator();
        MListener listener = new MListener(emulator);

        Controller controller = new Controller();
        controller.addListener(listener);

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
