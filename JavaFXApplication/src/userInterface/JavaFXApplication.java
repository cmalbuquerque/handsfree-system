/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DiogoFerreira
 */
public class JavaFXApplication extends Application {

    public static void startUI(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/Scene.fxml"));
//        Scene scene = new Scene(root);


        if (!checklogedIn()){
            //log in page
            LogInWindow.display();
        }

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello Worlddd!");
            }
        });

        btn.setOnAction(e -> {
            System.out.println("Olá");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);
        //scene.getStylesheets().add("D:\\UA\\Ano4\\PI\\HandsfreeSystem\\JavaFXApplication\\resources\\styles\\Styles.css");

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

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
        return true;
    }


}
