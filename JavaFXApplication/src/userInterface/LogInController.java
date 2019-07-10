/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author DiogoFerreira
 */
public class LogInController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    @FXML
    private void logInBtnPressed(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");        
        final Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


}
