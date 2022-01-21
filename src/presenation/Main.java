package presenation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
    private Parent root;
    @Override
    public void start(Stage stage) throws Exception {
        try {
            root = FXMLLoader.load(getClass().getResource("./view/login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            System.out.println("exeception on start method in login ");
        }

    }
    public static void main(String args[])
    {
        launch(args);
    }
}
