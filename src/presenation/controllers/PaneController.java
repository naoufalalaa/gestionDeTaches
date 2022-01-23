package presenation.controllers;

import GANTT.GanttChart;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import metier.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
            /*
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/gantt/main.fxml"));
            Pane pane = loader.load();

            //passing data to gantt view
            // always use ids !
            Text title = (Text) pane.lookup("#title");
            title.setText(panne.getTITRE()+" "+panne.getID_PANNE());
            */
            int idPanne =panne.getID_PANNE();
            List<Tache> taches= metier.findTacheByIDPanne(idPanne);
            int nbTaches = taches.size();
            List<GANTT.Tache> taches1 = new ArrayList<>();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm-DD-yyyy");
            String[] machines = new String[taches.size()];
            int i=0;
            for (Tache t : taches){
                try {
                    LocalDate dateStart = LocalDate.parse(t.getSTART_DATE(), dtf);
                    LocalDate dateEnd = LocalDate.parse(t.getEND_DATE(),dtf);
                    System.out.println(dateStart);
                    long duration = Duration.between(dateStart,dateEnd).toHours();
                    if(i==0) {
                        taches1.add(new GANTT.Tache(duration, t.getTITRE(), t.getSTATUT(),null));
                    }else {
                        taches1.add(new GANTT.Tache(duration, t.getTITRE(), t.getSTATUT(), taches1.get(i-1)));
                    }
                    i++;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();
            final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
            xAxis.setLabel("Duration");
            xAxis.setTickLabelFill(Color.CHOCOLATE);
            LocalDate dateStart = LocalDate.parse(panne.getSTART_DATE());
            LocalDate dateEnd = LocalDate.parse(panne.getEND_DATE());

            // date de fin de la panne
            xAxis.setMinorTickCount((int) Duration.between(dateStart,dateEnd).toHours());


            yAxis.setLabel("");
            yAxis.setTickLabelFill(Color.CHOCOLATE);
            yAxis.setTickLabelGap(10);
            yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machines)));

            chart.setTitle(panne.getTITRE());
            chart.setLegendVisible(false);
            chart.setBlockHeight( 50);

            XYChart.Series series;
            for (int k = taches.size()-1 ; k >=0 ; k--){
                series = new XYChart.Series();
                double offset = 0;

                for(int j = 0; j <= i ; j++){
                    offset+= taches1.get(j).getPrevious().getDuration();
                }
                series.getData().add(new XYChart.Data(offset, taches1.get(k).getName(), new GanttChart.ExtraData( taches1.get(k).getDuration(), taches1.get(k).getStatus())));
                chart.getData().add(series);
            }

            chart.getStylesheets().add(getClass().getResource("/GANTT/ganttchart.css").toExternalForm());




            /*
            * code here for gantt diagram
            * */


            window.setCenter(chart);
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
