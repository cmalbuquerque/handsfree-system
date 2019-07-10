/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import appBackend.ChromeController;
import java.awt.TextField;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DiogoFerreira
 */
public class PrimaryMenuController implements Initializable {

    @FXML
    private GridPane mainGrid;

    @FXML
    private StackPane stackPane;

    @FXML
    private void logInBtnPressed(ActionEvent event) {
        //label.setText("Hello World!");        
        final Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApps(HashMap<Integer, String> hashMap, ChromeController chromeController) {
        GridPane gridPane = new GridPane();
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.setPrefSize(Double.MAX_VALUE, Double.MIN_VALUE);
//        gridPane.setMinSize(Double.MAX_VALUE,Double.MAX_VALUE);
        gridPane.setBackground(Background.EMPTY);
        stackPane.getChildren().add(gridPane);

        int size = hashMap.size();
        double nrOfColumns = Math.ceil((double) (size + 2) / 2);
        System.out.println("Size -> " + size + " Columns -> " + nrOfColumns);
        int i = 0;
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            i++;
            String appId = entry.getKey().toString();
            String appURL = entry.getValue();
            Button button = new Button(i + "\n" + appURL);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setId(Integer.toString(i));
            button.setOnAction(e -> {
                System.out.println("Id " + button.getId());
                ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
                chromeController.changeURL(appURL);
            });

            if (i <= nrOfColumns) {
                gridPane.addRow(0, button);
            } else {
                gridPane.addRow(1, button);
            }

        }
        i++;
        Button addBtn = new Button(i++ + "\n" + "Adicionar Aplicação");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setOnAction(e -> {
            System.out.println("Id " + addBtn.getId());
            ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);

        });

        Button exitBtn = new Button(i++ + "\n" + "Sair");
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setOnAction(e -> {
            System.out.println("Id " + exitBtn.getId());
            ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
        });

        gridPane.addRow(1, addBtn);
        gridPane.addRow(1, exitBtn);

    }

}
