package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton submit;


    public void submit(ActionEvent event) {
        //login code here


        //login successful
        //close login window
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.hide();

        //open DashBoard Window
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/base.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage();
            window.setScene(scene);
            window.initStyle(StageStyle.TRANSPARENT);
            window.show();
        } catch (Exception e) {
            System.out.println("exception load main window in login controller ");
        }

    }
}
