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
import javafx.util.StringConverter;
import metier.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PaneController implements Initializable {
    private IMetier metier;

    @FXML
    private Pane box;

    @FXML
    private Text titre;
    public  static int static_label;




    private Panne panne;
    @FXML
    private Text machine;

    private String[] colors = {"D677B9", "FF6F91", "FA9B7A","FBC869"};


    public void setData(Panne panne) {
        titre.setText(panne.getTITRE());
        machine.setText(panne.getMachine().getNOM() + " " + panne.getMachine().getREFERENCE());
        box.setStyle(
                "-fx-background-color: white;"+ "-fx-background-radius: 15;"+
                "-fx-effect: dropshadow(three-pass-box, rgba(0.1,0.1,0.1,0.1),10,0,0,10);");
        this.panne = panne;
    }

    public int dateToInt(LocalDate date){
        int days = (int) ChronoUnit.DAYS.between(LocalDate.of(1,1,1),date);
        return days;
    }

    public String intToDate(int Days){
        double res ;
        int temp = Days;
        int years = (int) (temp / 365.2425);
        res = temp/365.2425 - years;
        int months = (int) ((res*365.2425) /30.417);
        res = ((res*365.2425) /30.417) - months ;
        int days = (int) (res*30.417);
        years++;
        months++;
        days++;

        return (months<10)? (days<10)? ((years)+"-0"+(months)+"-0"+(days)):((years)+"-0"+(months)+"-"+(days)) : (days<10)? ((years)+"-"+(months)+"-0"+(days)):((years)+"-"+(months)+"-"+(days));
    }

    @FXML
    public void click(MouseEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        BorderPane window = (BorderPane) scene.lookup("#Window");
        try {
            int idPanne =panne.getID_PANNE();
            List<Tache> taches= metier.findTacheByIDPanne(idPanne);
            int nbTaches = taches.size();

            Comparator<Tache> CompareByStartDate = new Comparator<Tache>() {
                @Override
                public int compare(Tache o1, Tache o2) {
                    return o1.getSTART_DATE().compareTo(o2.getEND_DATE());
                }
            };
            Collections.sort(taches,CompareByStartDate);
            String[] machines = new String[taches.size()];
            for (int k = 0;k < taches.size() ; k++){
                machines[k] = taches.get(k).getTITRE();
            }
            String[] dates = new String[nbTaches];
            for (int i=0;i<nbTaches;i++){
                dates[i] = taches.get(i).getSTART_DATE().toString();
            }


            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();
            final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
            xAxis.setLabel("Durée (jours)");
            xAxis.setTickLabelFill(Color.CHOCOLATE);

            // date de fin de la panne
            xAxis.setMinorTickCount(dateToInt(panne.getEND_DATE())/dateToInt(panne.getSTART_DATE()));

            xAxis.setTickLabelFormatter(new StringConverter<Number>() {
                @Override
                public String toString(Number object) {
                    int obj = object.intValue();
                    obj += dateToInt(panne.getSTART_DATE()) ;
                    return (object.intValue()==0)? panne.getSTART_DATE().toString() : intToDate(obj);
                }

                @Override
                public Number fromString(String string) {
                    return null;
                }
            });


            yAxis.setLabel("");
            yAxis.setTickLabelFill(Color.CHOCOLATE);
            yAxis.setTickLabelGap(10);
            yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machines)));

            chart.setTitle(panne.getTITRE());
            chart.setLegendVisible(true);

            chart.setBlockHeight( 50);
            XYChart.Series series;
            for (int i = taches.size()-1 ; i >=0 ; i--){
                series = new XYChart.Series();
                series.getData().add(new XYChart.Data(dateToInt(taches.get(i).getSTART_DATE())-dateToInt(panne.getSTART_DATE()), taches.get(i).getTITRE(), new GanttChart.ExtraData( dateToInt(taches.get(i).getEND_DATE())-dateToInt(taches.get(i).getSTART_DATE()), taches.get(i).getSTATUT())));
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
            Machine machine;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/taches/liste.fxml"));
            Pane pane = loader.load();
            ListTachesController controller = loader.getController();
            static_label=panne.getID_PANNE();

            controller.displayTaches(panne.getID_PANNE());
            machine= panne.getMachine();
            Scene scene = ((Node) event.getSource()).getScene();
            BorderPane Window = (BorderPane) scene.lookup("#Window");


            Text title = (Text) pane.lookup("#panne_title");
            title.setText(machine.getNOM() + " : " + panne.getTITRE());

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
