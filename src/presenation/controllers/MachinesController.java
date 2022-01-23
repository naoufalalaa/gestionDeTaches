package presenation.controllers;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.*;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MachinesController implements Initializable{
    private IMetier metier;

    @FXML
    private Text username;

    @FXML
    private JFXButton addmachinebutton;

    @FXML
    private TextField search_field;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane machinesContainer;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(AuthenticatedUser.getAuthenticateduser().getNOM().toUpperCase());

        /*based on the role of authenticated user we can hide the button for adding machine
        String role = "Res";

        if (role.equals("Intervenant")) {
            addmachinebutton.setVisible(false);
        }
        */
        metier=new MetierImp();

        //add button
        addmachinebutton.addEventHandler(ActionEvent.ACTION, event ->{
            //get the current stage (main window)
            Stage mainwindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainwindow.hide();

            //load the add window
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/machines/add.fxml"));
            loader.setController("Controllers.MachinesController");
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                System.out.println();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                System.out.println("exception on add machine  function");
            }


            //get all fields
            JFXTextField nom = (JFXTextField) scene.lookup("#nom");
            JFXTextField reference = (JFXTextField) scene.lookup("#reference");
            JFXTextField modele = (JFXTextField) scene.lookup("#modele");
            JFXTextField titre = (JFXTextField) scene.lookup("#titre");
            JFXTextField description = (JFXTextField) scene.lookup("#description");
            JFXDatePicker start_date = (JFXDatePicker) scene.lookup("#start_date");
            JFXDatePicker end_date = (JFXDatePicker) scene.lookup("#end_date");



            //get the add and cancel button
            JFXButton add = (JFXButton) scene.lookup("#add");
            JFXButton cancel = (JFXButton) scene.lookup("#cancel");


            add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println("dg");
                String date_rec = end_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
                String date_rec2 = start_date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
                Machine machine = new Machine(reference.getText(), nom.getText(),Integer.parseInt(modele.getText()));
                Panne panne = new Panne(titre.getText(), description.getText(), date_rec2, date_rec);
                metier.addMachine(machine,panne);
                loadmachines();

                // do what you have to do
                stage.close();

            /*
            Code here
             */

                //close the add window and show the previous window
                stage.close();
                mainwindow.show();
            });


            cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                //close the add window
                stage.close();
                //show the main window
                mainwindow.show();
            });
        });


        //search field
        search_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(search_field.getText());
            }
        });

        //load machiens
        loadmachines();








    }

    //function for loading machines
    private void loadmachines() {

        int column = 0;
        int row = 1;
        try {
            for (Machine machine : metier.getAllMachines()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/machines/machine.fxml"));
                Pane pane = loader.load();
                MachineController machineController = loader.getController();
                machineController.setData(machine);

                if (column == 3) {
                    column = 0;
                    ++row;
                }
                machinesContainer.add(pane, ++column, row);
                GridPane.setMargin(pane, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    }
