package presenation.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.Machine;
import metier.Panne;
import metier.User;

import metier.IMetier;
import metier.MetierImp;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TachesController implements Initializable {

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane pannesContainer;


    @FXML
    private TextField search_field;
    private IMetier metier;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        metier=new MetierImp();

        loadPannes();


    }
    public void add(ActionEvent event) {
        //get the current stage (main window)
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();

        //load the add window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/taches/add.fxml"));
        loader.setController("TachesController");
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception on add function");
        }


        //get all fields
        JFXTextField Titre = (JFXTextField) scene.lookup("#Titre");
        JFXTextField Description = (JFXTextField) scene.lookup("#Description");
        JFXTextField Materiels = (JFXTextField) scene.lookup("#Materiels");
        JFXDatePicker start_date = (JFXDatePicker) scene.lookup("#start_date");
        JFXDatePicker end_date = (JFXDatePicker) scene.lookup( "#end_date");
        JFXComboBox<User> intervenant = (JFXComboBox) scene.lookup("#intervenant");
        JFXComboBox<Panne> Panne = (JFXComboBox) scene.lookup("#Panne");



        //set users in combobox
        /*
        code
        * */


        //set Pannes in comboBox

        /*
        * code
        * */



        //get the add and cancel button
        JFXButton add = (JFXButton) scene.lookup("#add");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //get all values
            /*
            * le code pour ajouter une panne
            * */
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






    public List<Panne> getPannesList() {
        List<Panne> pannes= new ArrayList<>();
        Panne panne1 = new Panne();
        panne1.setTITRE("disque dur fragmenté,");
        pannes.add(panne1);

        Panne panne2 = new Panne();
        panne2.setTITRE("manque d'espace disque");
        pannes.add(panne2);




        return pannes;


    }


    //function for loading machines
    private void loadPannes() {
        List<Panne> pannes= new ArrayList<>(metier.getAllPannes());

        int column = 0;
        int row = 1;
        try {
            for (Panne panne: pannes) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/pannes/panne.fxml"));
                Pane pane = loader.load();
                PaneController paneController= loader.getController();
                paneController.setData(panne);
                if (column == 2) {
                    column = 0;
                    ++row;
                }
                pannesContainer.add(pane, ++column, row);
                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
