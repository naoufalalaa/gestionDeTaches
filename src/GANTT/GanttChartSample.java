package GANTT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GANTT.Tache;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// TODO: use date for x-axis
public class GanttChartSample extends Application {

    @Override public void start(Stage stage) {

        stage.setTitle("Gantt Chart Sample");

        List<Tache> taches = new ArrayList<>();
        Tache t1 = new Tache(1,"Buy material","status-green");
        Tache t2 = new Tache(8,"Start repare","status-blue");
        Tache t3 = new Tache(3,"End repare","status-red");
        taches.add(t1);
        taches.add(t2);
        taches.add(t3);
        String[] machines = new String[] { t1.getName(), t2.getName(), t3.getName() };

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(13);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machines)));

        chart.setTitle("Machine Monitoring");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 50);

        XYChart.Series series;
        for (int i = taches.size()-1 ; i >=0 ; i--){
            series = new XYChart.Series();
            series.getData().add(new XYChart.Data(0, taches.get(i).getName(), new GanttChart.ExtraData( taches.get(i).getDuration(), taches.get(i).getStatus())));
            chart.getData().add(series);
        }


        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());

        Scene scene  = new Scene(chart,620,350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}