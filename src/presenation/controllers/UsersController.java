package presenation.controllers;

import com.jfoenix.controls.*;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable{
    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_prenom;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_telephone;

    @FXML
    private TableColumn<?, ?> col_login;

    @FXML
    private TableView<?> UsersTable;

    @FXML
    private TextField search_field;


    public void delete(ActionEvent event) {
        /*
        * code here
        * */
    }

    public void edit(ActionEvent event) {

        //get the selected model
        //example
        // User user= UsersTable.getSelectionModel().getSelectedItem();


        //get the current stage (main window) and hide it
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();



        //load the update window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/users/update.fxml"));
        loader.setController("Controllers.UsersController");
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception on add function");
        }


        //get all fields
        JFXTextField nom = (JFXTextField) scene.lookup("#nom");
        JFXTextField prenom = (JFXTextField) scene.lookup("#prenom");
        JFXTextField email = (JFXTextField) scene.lookup("#email");
        JFXTextField telephone = (JFXTextField) scene.lookup("#telephone");
        JFXTextField login = (JFXTextField) scene.lookup("#login");
        JFXPasswordField password = (JFXPasswordField) scene.lookup("#password");
        JFXComboBox<String> role = (JFXComboBox) scene.lookup("#role");


        /*
        * set old value
        * example
        * nom.setText(user.getNom())
         * */

        nom.setText("zakaria");



        //get the add and cancel button
        JFXButton update = (JFXButton) scene.lookup("#update");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        update.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            /*
            * get values and update the model
            * example :
            * Professeur.updateProf(professeur.getId_prof(), nom_field.getText(), prenom.getText(), cin.getText(), adresse.getText());
            * */

            System.out.println(nom.getText() );

            /*
            Code here
             */

            //close the add window and show the previous window
            stage.close();
            previousStage.show();
        });


        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            //nothing to do here
            //close the add window
            stage.close();
            //show the main window
            previousStage.show();
        });


    }

    public void add(ActionEvent event) {
        //get the current stage (main window)
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();

        //load the add window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/users/add.fxml"));
        loader.setController("Controllers.UsersController");
        Scene scene = null;
        try {
            System.out.println();
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            System.out.println("exception on add function");
        }


        //get all fields
        JFXTextField nom = (JFXTextField) scene.lookup("#nom");
        JFXTextField prenom = (JFXTextField) scene.lookup("#prenom");
        JFXTextField email = (JFXTextField) scene.lookup("#email");
        JFXTextField telephone = (JFXTextField) scene.lookup("#telephone");
        JFXTextField login = (JFXTextField) scene.lookup("#login");
        JFXPasswordField password = (JFXPasswordField) scene.lookup("#password");
        JFXComboBox<String> role = (JFXComboBox) scene.lookup("#role");


        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Intervenant");
        roles.add("Responsable");

        role.setItems(roles);

        //get the add and cancel button
        JFXButton add = (JFXButton) scene.lookup("#add");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //example of get values from fields
            System.out.println(nom.getText() + " " + prenom.getText() + " " + email.getText());

            /*
            Code here
             */

            //close the add window and show the previous window
            stage.close();
            previousStage.show();
        });


        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            //close the add window
            stage.close();
            //show the main window
            previousStage.show();
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        search_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(search_field.getText());
            }
        });
    }
}
