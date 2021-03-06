package sample.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DbConnection;

import java.io.IOException;

public class AdminPanelM {

    Stage stage;

    @FXML
    private JFXButton addChairman;

    @FXML
    private JFXButton mainSwitch;

    @FXML
    private JFXButton hlcSwitch;

    @FXML
    public JFXButton voteSwitch;

    @FXML
    private JFXButton resultSwitch;

    @FXML
    private JFXTextField serverAddress;

    Admin admin = new Admin();
    DbConnection dbConnect = new DbConnection();




    /**
     * The main switch to the main panel
     */

    @FXML
    public void setMainSwitch(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanelM.fxml"));
            Parent roots = loader.load();
            ((Stage)mainSwitch.getScene().getWindow()).setScene(new Scene(roots));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The hlc switch on the main panel
     */

    @FXML
    public void setHlcSwitch(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanelH.fxml"));
            Parent roots = loader.load();
            ((Stage)hlcSwitch.getScene().getWindow()).setScene(new Scene(roots));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The hlc switch on the main panel
     */

    @FXML
    public void setResultSwitch(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanelR.fxml"));
            Parent roots = loader.load();
            ((Stage)resultSwitch.getScene().getWindow()).setScene(new Scene(roots));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Vote switch on the main panel
     */

    public void setVoteSwitch(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vote/voteChair.fxml"));
            Parent roots = loader.load();
//            stage.getScene().setRoot(roots);
            ((Stage)voteSwitch.getScene().getWindow()).setScene(new Scene(roots));


//            ((Stage)voteSwitch.getScene().getWindow()).setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for chairman to the database
     */

    @FXML
    public void addChair(MouseEvent event) {
        // Load the form to input candidate details
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A candidate");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window if you want
//        ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Add a candidate for vice Chairman to the database
     */

    @FXML
    public void addViceChair(MouseEvent event) {
        // Load the form to input candidate details

        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for General Secretary to the database
     */

    @FXML
    public void addGen(MouseEvent event) {
        // Load the form to input candidate details
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Assistant General Secretary to the database
     */

    @FXML
    public void addAssG(MouseEvent event) {
        // Load the form to input candidate details
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Finance Minister
     */

    @FXML
    public void addFinM(MouseEvent event) {
        // Load the form to input the candidate details
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Add a candidate for Interior Minister
     */

    @FXML
    public void addIntM(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Information Minister
     */

    @FXML
    public void addInfoM(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Social Minister
     */

    @FXML
    public void addSocialM(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Health Minister
     */

    @FXML
    public void addHealthM(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a candidate for Sports Minister
     */

    @FXML
    public void addSportsM(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add A Candidate");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
