package appBackend;

import databaseDB.DatabaseConnection;
import Speech2Text.MainSpeech;
import Emulator.GesturesEmulator;
import machineLearning.MListener;
import com.leapmotion.leap.Controller;
import java.util.Scanner;
import userInterface.JavaFXApplication;

public class Creator {

    public ChromeController chromeController;
    private String[] args;

    public Creator(String[] args) {
        this.args = args;
    }

    public void startProgram() {      
        
        JavaFXApplication fxApp = new JavaFXApplication();
        fxApp.startUI(args);
        //chromeController = getChromeController(fxApp);
        System.out.println(chromeController);
        

    }

    public static void main(String[] args) {
        Creator creator = new Creator(args);
        creator.startProgram();
    }

}
