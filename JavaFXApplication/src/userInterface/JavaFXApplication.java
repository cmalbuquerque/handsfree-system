/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import Emulator.GesturesVoiceEmulator;
import Speech2Text.MainSpeech;
import appBackend.ChromeController;
import com.leapmotion.leap.Controller;
import databaseDB.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import machineLearning.MListener;
import sun.awt.PlatformFont;

/**
 *
 * @author DiogoFerreira
 */
public class JavaFXApplication extends Application {

    HashMap<String, String> aplicationHash;
    public ChromeController chromeController;
    Stage primaryStage;
    GesturesVoiceEmulator emulator;
    public static Boolean loggedIn = false;

    public JavaFXApplication(ChromeController chromeController) {
        this.chromeController = chromeController;
    }

    public JavaFXApplication() {
    }

    public static void startUI(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        //create database
        DatabaseQueries queries = new DatabaseQueries();

        //chromeController
        chromeController = new ChromeController();

        //leap motion stuff
        emulator = new GesturesVoiceEmulator(chromeController, primaryStage);
        MListener listener = new MListener(emulator);
        Controller controller = new Controller();
        controller.addListener(listener);

        HashMap<String, String> userApps = queries.dbGetUserApps();

        ArrayList<String[]> appProfies = queries.dbGetUserProfile();

        System.out.println("----------------------------------------------------");
        for (String[] profileInfo : appProfies) {
            String appId = profileInfo[0];
            String profileId = profileInfo[1];
            String profileName = profileInfo[2];
            System.out.println("AppId: " + appId + ", profileId: " + profileId + "profileName: " + profileName);
        }
        
            System.out.println("----------------------------------------------------");

            //start speech recognition
            MainSpeech mainSpeech = new MainSpeech(emulator);
            mainSpeech.start();

            //-------------------------------------------------------------User Interface---------------------------------------------------------
            if (!checklogedIn()) {
                logInWindow();
            }

            mainMenuWindow(primaryStage, userApps, appProfies);

        }

    

    

    

    public static void main(String[] args) {
        Application.launch(args);
    }

    private boolean checklogedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(Boolean loggedIn) {
        JavaFXApplication.loggedIn = loggedIn;
    }

    public ChromeController getChromeController() {
        return chromeController;
    }

    private void logInWindow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Stage logInStage = new Stage();
        Scene scene = new Scene(root);
        logInStage.setScene(scene);
        logInStage.showAndWait();
    }

    private void mainMenuWindow(Stage primaryStage, HashMap<String, String> userApps, ArrayList<String[]> appProfiles) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("PrimaryMenu.fxml"));
        Parent root = fXMLLoader.load();
        PrimaryMenuController primaryMenuController = fXMLLoader.<PrimaryMenuController>getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryMenuController.setApps(userApps, chromeController, appProfiles, emulator);
        primaryStage.show();
        chromeController.openChrome();
    }

    public void showSecondaryMenu() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showMainMenu(JavaFXApplication fxApp) {
        //fxApp.primaryStage.setIconified(false);
        //this.primaryStage.setIconified(false);
        primaryStage.setFullScreen(true);
    }

}
