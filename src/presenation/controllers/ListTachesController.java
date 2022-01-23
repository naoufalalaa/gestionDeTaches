package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListTachesController implements Initializable {
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }


    @FXML
    private TableView<Tache> TachesTable;

    @FXML
    private TableColumn<Tache, String> col_title;

    @FXML
    private TableColumn<Tache, String> col_startDate;

    @FXML
    private TableColumn<Tache, String> col_endDate;

    @FXML
    private TableColumn<Tache, String> col_status;

    @FXML
    private TextField search_field;

    @FXML
    private Text panne_title;
    private IMetier metier;
    ObservableList<Tache> liste= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        metier=new MetierImp();
        liste.addAll(metier.getAllTaches());
        TachesTable.setItems(liste);
        search_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                liste.setAll(metier.findTacheParMC(search_field.getText())) ;
            }
        });
    }

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
        JFXComboBox<Intervenant> intervenant = (JFXComboBox) scene.lookup("#intervenant");
        JFXComboBox<Panne> panne = (JFXComboBox) scene.lookup("#Panne");



        //set users in combobox
        /*
        code
        * */
        ObservableList<Intervenant> listeIntevenant= FXCollections.observableArrayList();
        listeIntevenant.addAll(metier.getAllIntervenant());
        intervenant.getItems().setAll(metier.getAllIntervenant());



        //set Pannes in comboBox

        /*
         * code
         * */
        ObservableList<Panne> listePanne= FXCollections.observableArrayList();
        listePanne.addAll(metier.getAllPannes());
        panne.getItems().setAll(metier.getAllPannes());


        //get the add and cancel button
        JFXButton add = (JFXButton) scene.lookup("#add");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //get all values
            System.out.println(Titre.getText()+" "+ Date.valueOf(start_date.getValue()));

            /*
             * le code pour ajouter une panne
             * */
            String date_rec = end_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            String date_rec2 = start_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            Tache tache = new Tache(Titre.getText(),Description.getText(),Materiels.getText(),date_rec2,date_rec,"pending",panne.getSelectionModel().getSelectedItem(), intervenant.getSelectionModel().getSelectedItem());
          //  System.out.println(intervenant.getNOM()+intervenant.getLOGIN()+intervenant.getPASSWORD());
            metier.addTache(tache);
            liste.clear();
            liste.addAll(metier.getAllTaches());


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
    public void delete(ActionEvent event) {

        String mc=search_field.getText();
        System.out.println(mc);
        if(metier.findTacheParMC(mc).size()==0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
        else {

            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("vous etes sur");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                List<Tache> taches=metier.findTacheParMC(mc);
                int indice=taches.get(0).getID_TACHE();
                if(indice>=0) {
                    System.out.println(indice);
                    liste.clear();
                    metier.deleteTache(metier.findTacheParID(indice));
                    liste.addAll(metier.getAllTaches());
                }
            }
            else if (option.get() == ButtonType.CANCEL) {
            }

        }
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
        JFXComboBox<Intervenant> intervenant = (JFXComboBox) scene.lookup("#intervenant");
        JFXComboBox<Panne> panne = (JFXComboBox) scene.lookup("#Panne");


        ObservableList<Intervenant> listeIntevenant= FXCollections.observableArrayList();
        listeIntevenant.addAll(metier.getAllIntervenant());
        intervenant.getItems().setAll(metier.getAllIntervenant());
        /*
        * set old values*/
        ObservableList<Panne> listePanne= FXCollections.observableArrayList();
        listePanne.addAll(metier.getAllPannes());
        panne.getItems().setAll(metier.getAllPannes());

        String mc=search_field.getText();
        System.out.println(mc);
        if(metier.findTacheParMC(mc).size()==0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un élément ");
            alert.show();
        }
        else {
            try {
                start_date.setValue(LOCAL_DATE(metier.findTacheParMC(mc).get(0).getSTART_DATE()));
                end_date.setValue(LOCAL_DATE(metier.findTacheParMC(mc).get(0).getEND_DATE()));
            } catch (NullPointerException e) {}
            Titre.setText(metier.findTacheParMC(mc).get(0).getTITRE());
            Description.setText(metier.findTacheParMC(mc).get(0).getDESCRIPTION());
            Materiels.setText(metier.findTacheParMC(mc).get(0).getMATERIELS());

        }


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
        JFXButton update = (JFXButton) scene.lookup("#update");
        JFXButton cancel = (JFXButton) scene.lookup("#cancel");


        update.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //get all values
            System.out.println(Description.getText()+" "+ Date.valueOf(start_date.getValue()));
            String date_rec = end_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            String date_rec2 = start_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            Tache tache = new Tache(metier.findTacheParMC(mc).get(0).getID_TACHE(),Titre.getText(),Description.getText(),Materiels.getText(),date_rec2,date_rec,"pending",panne.getSelectionModel().getSelectedItem(), intervenant.getSelectionModel().getSelectedItem());
            //  System.out.println(intervenant.getNOM()+intervenant.getLOGIN()+intervenant.getPASSWORD());
            metier.updateTache(tache);
            liste.clear();
            liste.addAll(metier.getAllTaches());



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


    public void displayTaches(int id_panne) {
        System.out.println("heeh " + id_panne);

        IMetier metier = new MetierImp();
        ObservableList<Tache> tacheObservableList = FXCollections.observableArrayList();


        col_title.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        col_startDate.setCellValueFactory(new PropertyValueFactory<>("START_DATE"));
        col_endDate.setCellValueFactory(new PropertyValueFactory<>("END_DATE"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("STATUT"));

        tacheObservableList.addAll(metier.getAllTachesPanne(String.valueOf(id_panne)));
        for (Tache tache : tacheObservableList) {
            System.out.println(tache.getTITRE());

        }
        TachesTable.setItems(tacheObservableList);

    }
}
