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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.AuthenticatedUser;
import metier.SignletonConnectionDB;
import metier.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton submit;

    @FXML
    private Text loginstatus;


    public void submit(ActionEvent event) {
        //login code here


        try {
            Connection connection = SignletonConnectionDB.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE LOGIN=? and PASSWORD=?");
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User authenticateduser = new User(Integer.parseInt(rs.getString("ID_USER")), rs.getString("NOM"), rs.getString("ROLE"));
                AuthenticatedUser.setAuthenticateduser(authenticateduser);
                Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginWindow.close();
                loadMainWindow();
            } else {
                username.setText("");
                password.setText("");
                loginstatus.setText("Incorrect Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void loadMainWindow(){
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
