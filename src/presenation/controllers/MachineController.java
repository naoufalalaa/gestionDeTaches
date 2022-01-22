package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.Machine;
import metier.IMetier;
import metier.MetierImp;
import metier.Panne;

import java.net.URL;
import java.util.ResourceBundle;

public class MachineController implements Initializable
{
    @FXML
    private Pane box;

    @FXML
    private Text nom;

    @FXML
    private Text reference;

    @FXML
    private Text modele;

    private Machine machine;
    private IMetier metier;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        metier=new MetierImp();

    }


    private String[] colors = {"D677B9", "FF6F91", "FA9B7A","FBC869"};

    public void setData(Machine machine) {
            nom.setText(machine.getNOM());
            reference.setText(machine.getREFERENCE());
            modele.setText(String.valueOf(machine.getMODEL()));
            box.setStyle("-fx-background-color: #"+colors[(int)(Math.random()*colors.length)]+";"+
                    "-fx-background-radius: 15;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1),10,0,0,10);");
        this.machine = machine;
    }



    @FXML
    public void click(MouseEvent event) {
        //get the current stage (main window) and hide it
        Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.hide();


        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/machines/details.fxml"));
            Scene scene = new Scene(loader.load());


            Text nom_machine= (Text) scene.lookup("#nom_machine");
            Text titre_pane= (Text) scene.lookup("#titre_pane");
            Text description_pane= (Text) scene.lookup("#description_pane");
            Text start_date_pane= (Text) scene.lookup("#start_date_pane");
            Text end_date_pane= (Text) scene.lookup("#end_date_pane");


            nom_machine.setText(machine.getNOM());
            //we want to get pane by machine and use it here like
            System.out.println(machine.getREFERENCE());
            titre_pane.setText(metier.findPanneByReferenceMachine(machine.getREFERENCE()).getTITRE());
            description_pane.setText(metier.findPanneByReferenceMachine(machine.getREFERENCE()).getDESCRIPTION());
            start_date_pane.setText(metier.findPanneByReferenceMachine(machine.getREFERENCE()).getSTART_DATE());
            end_date_pane.setText(metier.findPanneByReferenceMachine(machine.getREFERENCE()).getEND_DATE());



            JFXButton closebutton = (JFXButton) scene.lookup("#closebutton");
            closebutton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                Stage currentwinodw = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                currentwinodw.close();
                previousStage.show();
            });


            Stage detailsmachinewindow = new Stage();
            detailsmachinewindow.setScene(scene);
            detailsmachinewindow.initStyle(StageStyle.TRANSPARENT);
            detailsmachinewindow.show();

        } catch (Exception e) {
            System.out.println("exeception on click in MachineController ");
            e.printStackTrace();
        }
    }



}
