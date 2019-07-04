package appBackend;

import Emulator.GesturesEmulator;
import machineLearning.MListener;
import com.leapmotion.leap.Controller;

public class Creator {

    public static void main(String[] args) {
        GesturesEmulator emulator = new GesturesEmulator();
        MListener listener = new MListener(emulator);
        
        Controller controller = new Controller();
        System.out.println("adding listener!!!!!!!!");
        controller.addListener(listener);
        System.out.println("done ma firend");
        

        //start chromeController Thread
//        ChromeController chrome = new ChromeController();
//        chrome.start();
//
//        DesktopMain.startUI(args);

    }

}
