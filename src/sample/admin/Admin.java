package sample.admin;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    @FXML
    public JFXTextField serverAdd;

    @FXML
    private Button loginbutton;





    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*try {
            // Load the JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Set properties for the login
            Properties prop = new Properties();
            prop.setProperty("user", "wayne");
            prop.setProperty("password", "admin");

            // Set the JDBC url
            String dburl = "jdbc:postgresql://localhost:5432/mellanbytest";

            // Get connected
            Connection con = DriverManager.getConnection(dburl, prop);
            System.out.println("Connected Successfully");

            PreparedStatement pst = con.prepareStatement("SELECT * FROM images WHERE image_name = 'Itachi'");
            ResultSet rs = pst.executeQuery();







        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
    }

    // This method allows the user to login by pressing the enter key
    @FXML
    public void handleLogin(ActionEvent event) {
        serverAdd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanelM.fxml"));
                        Parent roots = loader.load();
                        ((Stage)loginbutton.getScene().getWindow()).setScene(new Scene(roots));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DbConnection dbConnect = new DbConnection();
                    dbConnect.getConnection(getTextfield());
                    try {
                        DbConnection.pst = DbConnection.connection.prepareStatement("SELECT * FROM candidates");
                        ResultSet rs = DbConnection.pst.executeQuery();



                        while (rs.next()) {
                            int id, votesReceived;
                            String candidateName, candidateNickname, candidatePositionV;

                            id = rs.getInt("candidate_id");
                            candidateName = rs.getString("candidate_name");
                            candidateNickname = rs.getString("candidate_nickname");
                            candidatePositionV = rs.getString("candidate_position");
                            votesReceived = rs.getInt("votes_received");
                            System.out.println(id + " " + candidateName + " " + candidateNickname + " " + votesReceived + " "
                            + candidatePositionV + "\n");
                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    // This method logs the user in when the user clicks the sign in button
    @FXML
    public void handleServerLogin(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPanelM.fxml"));
            Parent roots = loader.load();
            ((Stage)loginbutton.getScene().getWindow()).setScene(new Scene(roots));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DbConnection dbConnect = new DbConnection();
        dbConnect.getConnection(getTextfield());
        System.out.println(getTextfield());
        try {
            DbConnection.pst = DbConnection.connection.prepareStatement("SELECT * FROM candidates");
            ResultSet rs = DbConnection.pst.executeQuery();



            while (rs.next()) {
                int id, votesReceived;
                String candidateName, candidateNickname;

                id = rs.getInt("candidate_id");
                candidateName = rs.getString("candidate_name");
                candidateNickname = rs.getString("candidate_nickname");
                votesReceived = rs.getInt("votes_received");
                System.out.print(id + " " + candidateName + " " + candidateNickname + " " + votesReceived);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public String getTextfield() {

        Admin admin = new Admin();
        return serverAdd.getText();
    }


}
