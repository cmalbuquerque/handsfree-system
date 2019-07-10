package appBackend;

import databaseDB.DatabaseConnection;
import Speech2Text.MainSpeech;
import Emulator.GesturesEmulator;
import machineLearning.MListener;
import com.leapmotion.leap.Controller;
import java.util.Scanner;
import userInterface.JavaFXApplication;

public class Creator {

    public ChromeController controller;

    public static void main(String[] args) {

        //create database
        //DatabaseConnection databaseConnection = new DatabaseConnection();
        //leap motion stuff
//        GesturesEmulator emulator = new GesturesEmulator();
//        MListener listener = new MListener(emulator);
//        Controller controller = new Controller();
//        controller.addListener(listener);
        //start chromeController Thread
        ChromeController chrome = new ChromeController();
         
                
                
                Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("GOGOGOGOGOGOGO");
        System.out.println(chrome);
        //start speech recognition
//        MainSpeech mainSpeech = new MainSpeech();
//        mainSpeech.start();

        //start UI Thread
        JavaFXApplication ui = new JavaFXApplication();
        ui.setChromeControler(chrome);
        ui.startUI(args);

        System.out.println(ui.getChromeController());

    }

    public void something() {

    }

    public void setController() {

    }

}
