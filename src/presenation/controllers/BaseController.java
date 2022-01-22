package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable{
    @FXML
    private BorderPane Window;

    @FXML
    private JFXButton usersButton;

    public void loadMachinesPane(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/machines/main.fxml"));
            Pane pane = loader.load();
            Window.setCenter(pane);
        } catch (Exception e) {
            System.out.println("exeception on start method in loadMachinesPane ");
            e.printStackTrace();
        }
    }

    public void loadUsersPane(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/users/main.fxml"));
            Pane pane = loader.load();
            Window.setCenter(pane);
        } catch (Exception e) {
            System.out.println("exception on start method in loadUsersPane ");
        }
    }

    public void loadTachesPane(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/taches/main.fxml"));
            Pane pane = loader.load();
            Window.setCenter(pane);
        } catch (Exception e) {
            System.out.println("exception on start method in loadUsersPane ");
        }
    }

    public void close(ActionEvent event) {
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentWindow.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMachinesPane(new ActionEvent());


        //based on the role of authenticated user we can hide the button for adding machine
        String role = "Res";

        if (role.equals("Intervenant")) {
            usersButton.setVisible(false);
        }
    }



}
