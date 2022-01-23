package presenation.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.Machine;
import metier.Panne;
import metier.IMetier;
import metier.MetierImp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaneController implements Initializable {
    private IMetier metier;

    @FXML
    private Pane box;

    @FXML
    private Text titre;
    public  static int static_label;




    private Panne panne;


    private String[] colors = {"D677B9", "FF6F91", "FA9B7A","FBC869"};


    public void setData(Panne panne) {
        titre.setText(panne.getTITRE());
        System.out.println(panne.getTITRE());
        box.setStyle(
                "-fx-background-color: white;"+ "-fx-background-radius: 15;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0.1,0.1,0.1,0.1),10,0,0,10);");
        this.panne = panne;
    }


    @FXML
    public void click(MouseEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        BorderPane window = (BorderPane) scene.lookup("#Window");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/gantt/main.fxml"));
            Pane pane = loader.load();

            //passing data to gantt view
            // always use ids !
            Text title = (Text) pane.lookup("#title");
            title.setText(panne.getTITRE()+" "+panne.getID_PANNE());

            /*
            * code here for gantt diagram
            * */


            window.setCenter(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTaches(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/taches/liste.fxml"));
            Pane pane = loader.load();
            ListTachesController controller = loader.getController();
            static_label=panne.getID_PANNE();

            controller.displayTaches(panne.getID_PANNE());


            Scene scene = ((Node) event.getSource()).getScene();
            BorderPane Window = (BorderPane) scene.lookup("#Window");


            Text title = (Text) pane.lookup("#panne_title");
            title.setText(panne.getTITRE());

            Window.setCenter(pane);
        } catch (Exception e) {
            System.out.println("exception on start method in loadUsersPane ");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        metier=new MetierImp();

    }
}
