package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setFullScreen(true);
//        Parent root = FXMLLoader.load(getClass().getResource("vote/vote1.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("admin/admin.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("vote/vote1.fxml"));
        primaryStage.setTitle("Voters Arise!");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

//        primaryStage.setFullScreen(true);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
