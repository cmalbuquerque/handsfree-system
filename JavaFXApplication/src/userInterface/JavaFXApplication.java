/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import appBackend.ChromeController;
import java.io.IOException;
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
        aplicationHash = new HashMap<>();
        System.out.println("Srsl: " + getChromeController());
        //go get data from data base
        aplicationHash.put("http://9gag.com", "9GAG");
        aplicationHash.put("http://doctors0.com", "I'm a doctor");
        aplicationHash.put("http://doctors1.com", "You'r a doctor");
        aplicationHash.put("http://doctors2.com", "He's a doctor");
        aplicationHash.put("http://doctors3.com", "We'r doctors");
        aplicationHash.put("http://doctors4.com", "They'r doctors");
        aplicationHash.put("http://doctors5.com", "Fuck doctors doctors");
        System.out.println("--------------------------------" + chromeController + "--------------------------------");
        System.out.println("WTAF - " + getChromeController());

        if (!checklogedIn()) {
            Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
            Stage logInStage = new Stage();
            Scene scene = new Scene(root);
            logInStage.setScene(scene);
            logInStage.showAndWait();
        }

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = fXMLLoader.load();
        PrimaryMenuController primaryMenuController = fXMLLoader.<PrimaryMenuController>getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryMenuController.setApps(aplicationHash, this);
        primaryStage.show();

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

    public void setChromeControler(ChromeController controller) {
        System.out.println("Chrome controller set: " + controller);
        chromeController = controller;
        System.out.println("Chrome controller seted: " + chromeController);

    }

    public void changeURL(String url) {

        System.out.println("CC - " + getChromeController());
        getChromeController().changeURL("https://9gag.com/");

    }

    public ChromeController getChromeController() {
        return chromeController;
    }

}
