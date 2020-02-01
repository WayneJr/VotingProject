package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("vote/vote1.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("admin/admin.fxml"));
//        Parent root2 = FXMLLoader.load(getClass().getResource("vote/vote1.fxml"));
        primaryStage.setTitle("Voters Arise!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        // Set Stage boundaries to visible bounds of main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());


//        primaryStage.setFullScreen(true);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
