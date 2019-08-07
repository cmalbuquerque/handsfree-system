/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import Emulator.GesturesVoiceEmulator;
import appBackend.ChromeController;
import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
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

    int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApps(HashMap<String, String> hashApps, ChromeController chromeController, ArrayList<String[]> profileList, GesturesVoiceEmulator emulator) {
        GridPane gridPane = new GridPane();
        gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.setPrefSize(Double.MAX_VALUE, Double.MIN_VALUE);
//        gridPane.setMinSize(Double.MAX_VALUE,Double.MAX_VALUE);
        gridPane.setBackground(Background.EMPTY);
        stackPane.getChildren().add(gridPane);
        Separator separator = new Separator();

        int size = hashApps.size();
        double nrOfColumns = Math.ceil((double) (size + 2) / 2);
        System.out.println("Size -> " + size + " Columns -> " + nrOfColumns);
        for (Map.Entry<String, String> entry : hashApps.entrySet()) {
            counter++;
            String appName = entry.getKey();
            String appURL = entry.getValue();
            Button button = new Button(counter + "\n" + appName);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setId("App" + Integer.toString(counter));
            button.setOnAction(e -> {
                System.out.println("Id " + button.getId());
                ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
                chromeController.changeURL(appURL);
            });

            if (counter <= nrOfColumns) {
                gridPane.addRow(0, button);
            } else {
                gridPane.addRow(1, button);
            }

        }
        counter++;
        Button addBtn = new Button(counter + "\n" + "Adicionar Aplicação");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setOnAction(e -> {
            System.out.println("Id " + addBtn.getId());
            ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
            setProf(profileList, emulator, gridPane, nrOfColumns);

        });
        counter++;
        Button exitBtn = new Button(counter + "\n" + "Sair");
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setOnAction(e -> {
            System.out.println("Id " + exitBtn.getId());
            ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
        });

        gridPane.addRow(1, addBtn);
        gridPane.addRow(1, exitBtn);

//        separator.setMaxWidth(40);
//        //separator.setAlignment(Pos.CENTER_LEFT);
//        gridPane.getChildren().add(2, separator);
    }

    public void setProf(ArrayList<String[]> profileList, GesturesVoiceEmulator emulator, GridPane gridPane, double nrOfColumns) {
        for (String[] profileInfo : profileList) {
            int i = counter;
            String appId = profileInfo[0];
            String profileId = profileInfo[1];
            String profileName = profileInfo[2];

            Button button = new Button(i + "\n" + profileName);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setId("Prf" + Integer.toString(i));
            button.setOnAction(e -> {
                System.out.println("Id " + button.getId());
                ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
                //emulator.fetchFromDatabase(Integer.parseInt(appId));
            });

            if (i <= nrOfColumns * 2) {
                gridPane.addRow(3, button);
            } else {
                gridPane.addRow(4, button);
            }

        }
    }
}
