package GANTT;

import java.util.Arrays;

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

        String[] taches = new String[] { "Tâche 1", "Tâche 2", "Tâche 3", "Tâche 4" };

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(13);

        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(taches)));

        chart.setTitle("Machine Monitoring");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 50);
        String machine;

        machine = taches[0];
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(0, machine, new GanttChart.ExtraData( 9, "status-red")));

        machine = taches[1];
        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data(1, machine, new GanttChart.ExtraData( 1, "status-green")));
        series2.getData().add(new XYChart.Data(5, machine, new GanttChart.ExtraData( 2, "status-red")));

        machine = taches[2];
        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data(0, machine, new GanttChart.ExtraData( 2, "status-red")));
        series3.getData().add(new XYChart.Data(3, machine, new GanttChart.ExtraData( 1, "status-green")));

        machine = taches[3];
        XYChart.Series series4 = new XYChart.Series();
        series4.getData().add(new XYChart.Data(0, machine, new GanttChart.ExtraData( 2, "status-blue")));
        series4.getData().add(new XYChart.Data(3, machine, new GanttChart.ExtraData( 1, "status-green")));
        series4.getData().add(new XYChart.Data(3, machine, new GanttChart.ExtraData( 1, "status-red")));

        chart.getData().addAll(series1, series2, series3, series4);

        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());

        Scene scene  = new Scene(chart,620,350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}