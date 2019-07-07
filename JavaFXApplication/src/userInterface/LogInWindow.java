/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.TextField;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author DiogoFerreira
 */
public class LogInWindow {

    public static void display() {

        TextField userName = new TextField();
        //userName.setPrompText("User Name");

        TextField password = new TextField();
        //userName.setPrompText("User Name");
        
        
        Button button = new Button("button");

        VBox vBox = new VBox();
        vBox.getChildren().add(userName, password, button);
        Stage stage = new Stage();
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

}
