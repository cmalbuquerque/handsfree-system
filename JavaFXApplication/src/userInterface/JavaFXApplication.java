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
import java.util.HashMap;
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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DiogoFerreira
 */
public class JavaFXApplication extends Application {

    HashMap<String, String> aplicationHash;
    public ChromeController chromeController;

    public JavaFXApplication(ChromeController chromeController) {
        this.chromeController = chromeController;
    }

    public JavaFXApplication() {
    }

    public static void startUI(String[] args) {
        Application.launch(args);
    }

    public static Boolean loggedIn = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //create database
        DatabaseConnection databaseConnection = new DatabaseConnection();

        //chromeController
        chromeController = new ChromeController();

        //leap motion stuff
        GesturesVoiceEmulator emulator = new GesturesVoiceEmulator(chromeController, this);
        MListener listener = new MListener(emulator);
        Controller controller = new Controller();
        controller.addListener(listener);

        HashMap<Integer, String> userApps = dbGetUserApps();

        //start speech recognition
        MainSpeech mainSpeech = new MainSpeech(emulator);
        mainSpeech.start();

        //-------------------------------------------------------------User Interface---------------------------------------------------------
        if (!checklogedIn()) {
            logInWindow();
        }

        mainMenuWindow(primaryStage, userApps);

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello Worlddd!");
//            }
//        });
//
//        btn.setOnAction(e -> {
//            System.out.println("Olá");
//        });
//
//        StackPane pane = new StackPane();
//        pane.getChildren().add(btn);
//
//        Scene scene = new Scene(pane, 300, 250);
//        //scene.getStylesheets().add("D:\\UA\\Ano4\\PI\\HandsfreeSystem\\JavaFXApplication\\resources\\styles\\Styles.css");
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
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

    private HashMap dbGetUserApps() {
        Connection con = null;
        HashMap<String, String> hash = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from usa_app where id_pessoa=1;");
            while (rs.next()) {
                list.add(rs.getInt(3));
            }
        } catch (Exception e) {
        }

        return dbGetAppURL(list);
    }

    private HashMap dbGetAppURL(ArrayList<Integer> list) {
        Connection con = null;
        HashMap<Integer, String> hash = new HashMap<>();
        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            for (Integer i : list) {
                ResultSet rs = statement.executeQuery("SELECT * FROM app where id_app=" + i + ";");
                while (rs.next()) {
                    hash.put(i, rs.getString(3));
                }
            }

        } catch (Exception e) {
        }
        return hash;
    }

    private void mainMenuWindow(Stage primaryStage, HashMap<Integer, String> userApps) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = fXMLLoader.load();
        PrimaryMenuController primaryMenuController = fXMLLoader.<PrimaryMenuController>getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryMenuController.setApps(userApps, chromeController);
        primaryStage.show();
    }

    public void showSecondaryMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showMainMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
