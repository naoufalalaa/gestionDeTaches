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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsersController implements Initializable{
    @FXML
    private TableColumn<Intervenant, Integer> col_id;

    @FXML
    private TableColumn<Intervenant, String> col_nom;

    @FXML
    private TableColumn<Intervenant, String> col_prenom;

    @FXML
    private TableColumn<Intervenant, String> col_email;

    @FXML
    private TableColumn<Intervenant, String> col_telephone;

    @FXML
    private TableColumn<Intervenant, String>col_login;

    @FXML
    private TableView<Intervenant> UsersTable;
    private IMetier metier;
    ObservableList<Intervenant> liste= FXCollections.observableArrayList();

    @FXML
    private TextField search_field;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("ID_USER"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("NOM"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("PRENOM"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<>("TELEPHONE"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("LOGIN"));
        metier=new MetierImp();
        liste.addAll(metier.getAllIntervenant());
        UsersTable.setItems(liste);

        search_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                liste.setAll(metier.findIntervenantByMC(search_field.getText())) ;
            }
        });
    }

    public void delete(ActionEvent event) {

            String mc=search_field.getText();
        System.out.println(mc);
            if(metier.findIntervenantByMC(mc).size()==0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez sélectionner un élément ");
                alert.show();
            }
            else {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("vous etes sur");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    List<Intervenant> intervenants=metier.findIntervenantByMC(mc);
                    int indice=intervenants.get(0).getID_USER();
                    if(indice>=0) {
                        System.out.println(indice);
                        liste.clear();
                        metier.deleteIntervenant(metier.findIntervenantByID(indice));
                        liste.addAll(metier.getAllIntervenant());
                    }
                }
                else if (option.get() == ButtonType.CANCEL) {
                }

            }
        }


    public void edit(ActionEvent event) {

        //get the selected model
        //example
        // User user= UsersTable.getSelectionModel().getSelectedItem();
        System.out.println("df");

        //get the current stage (main window) and hide it
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();
        System.out.println("hgh");


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


        /*
        * set old value
        * example
        * nom.setText(user.getNom())
         * */
        String mc=search_field.getText();
        System.out.println(mc);
        if(metier.findIntervenantByMC(mc).size()==0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
        else {
            nom.setText(metier.findIntervenantByMC(mc).get(0).getNOM());
            prenom.setText(metier.findIntervenantByMC(mc).get(0).getPRENOM());
            login.setText(metier.findIntervenantByMC(mc).get(0).getLOGIN());
            telephone.setText(metier.findIntervenantByMC(mc).get(0).getTELEPHONE());
            email.setText(metier.findIntervenantByMC(mc).get(0).getEMAIL());
            password.setText(metier.findIntervenantByMC(mc).get(0).getPASSWORD());
        }




        //get the add and cancel button
        JFXButton update = (JFXButton) scene.lookup("#update");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        update.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            /*
            * get values and update the model
            * example :
            * Professeur.updateProf(professeur.getId_prof(), nom_field.getText(), prenom.getText(), cin.getText(), adresse.getText());
            * */

           // System.out.println(nom.getText()+prenom.getText()+password.getText());

            Intervenant p=new Intervenant(metier.findIntervenantByMC(mc).get(0).getID_USER(),nom.getText(),prenom.getText(),telephone.getText(),email.getText(),login.getText(),password.getText());
            metier.updateIntervenant(p);




            liste.clear();
            liste.addAll(metier.getAllIntervenant());

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




        //get the add and cancel button
        JFXButton add = (JFXButton) scene.lookup("#add");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //example of get values from fields

            Intervenant intervenant = new Intervenant(nom.getText(), prenom.getText(), telephone.getText(), email.getText(), login.getText(), password.getText());
            System.out.println(intervenant.getNOM()+intervenant.getLOGIN()+intervenant.getPASSWORD());
            metier.addIntervenant(intervenant);
            liste.clear();
            liste.addAll(metier.getAllIntervenant());



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



}
