package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.Panne;
import metier.Tache;
import metier.User;

import java.sql.Date;
import java.time.LocalDate;

public class ListTachesController {

    @FXML
    private TableView<Tache> TachesTable;

    @FXML
    private TableColumn<Tache, String> col_title;

    @FXML
    private TableColumn<Tache, Date> col_startDate;

    @FXML
    private TableColumn<Tache, Date> col_endDate;

    @FXML
    private TableColumn<Tache, String> col_status;

    @FXML
    private TextField search_field;

    @FXML
    private Text panne_title;

    @FXML
    void add(ActionEvent event) {
        //get the current stage (main window)
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();

        //load the add window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/taches/add.fxml"));
        Scene scene = null;
        try {
            System.out.println();
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
            System.out.println(Titre.getText()+" "+ Date.valueOf(start_date.getValue()));

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

    @FXML
    void delete(ActionEvent event) {
    }

    @FXML
    void edit(ActionEvent event) {
        //get the current stage (main window)
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();

        Tache old_tache = TachesTable.getSelectionModel().getSelectedItem();


        //load the add window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/taches/update.fxml"));
        Scene scene = null;
        try {
            System.out.println("");
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



        /*
        * set old values*/
        Titre.setText(old_tache.getTITRE());
        Description.setText(old_tache.getDESCRIPTION());
        Materiels.setText(old_tache.getMATERIELS());
        start_date.setValue(LocalDate.parse(old_tache.getSTART_DATE()));
        end_date.setValue(LocalDate.parse(old_tache.getEND_DATE()));

        //set old intervenant
        /*
        code
        * */

        //set old panne




        /*
         * code
         * */



        //get the add and cancel button
        //add here is the button for update
        JFXButton add = (JFXButton) scene.lookup("#add");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //get all values
            System.out.println(Titre.getText()+" "+ Date.valueOf(start_date.getValue()));

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
}
