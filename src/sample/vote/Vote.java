package sample.vote;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class Vote implements Initializable {

    final ObservableList candidates = FXCollections.observableArrayList();
    String connectAddress = "";

    @FXML
    public JFXComboBox chairVote;

    @FXML
    public JFXButton chairVoteButton;

    @FXML
    public JFXComboBox VchairVote;

    @FXML
    public JFXButton VchairVoteButton;

    @FXML
    public JFXComboBox GenVote;

    int currentVote;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        fillChairman();



    }

    /**
     * This method returns the value of the database connection using a .txt file
     */

    public String getDataConnect() {
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        String str_data = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("/root/connection.txt"));
            while (strLine != null) {
                if (strLine == null) break;
                str_data += strLine;
                strLine = br.readLine();
            }
//            System.out.println(str_data);
            br.close();
        } catch (IOException e) {
            System.err.println("Unable to read file");
        }
        return str_data;
    }

    /**
     * This method fills the combo box with values from the database for Chairman candidates
     */
    @FXML
    public void fillChairman() {
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;

        try {
            Class.forName("org.postgresql.Driver");

            Properties props = new Properties();
            props.setProperty("user", "wayne");
            props.setProperty("password", "admin");

            String dburl = "jdbc:postgresql://" + getDataConnect();
            conn = DriverManager.getConnection(dburl, props);

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Hall Chairman'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        chairVote.setItems(candidates);
    }


    /**
     * This records the vote and moves to the next scene
     */
   @FXML
    public void handleChairVote(MouseEvent event) {
       // Convert the vote to text format
       String vote = chairVote.getSelectionModel().getSelectedItem().toString();
       System.out.println(vote);

       // Create the database connection
       Connection conn;
       PreparedStatement pst;
       ResultSet rs;

       try {


           Class.forName("org.postgresql.Driver");

           Properties props = new Properties();
           props.setProperty("user", "wayne");
           props.setProperty("password", "admin");

           String dburl = "jdbc:postgresql://" + getDataConnect();
           conn = DriverManager.getConnection(dburl, props);

           PreparedStatement nick = conn.prepareStatement("SELECT votes_received FROM candidates WHERE candidate_nickname = '" + vote + "'");
           rs = nick.executeQuery();

           while (rs.next()) {
               currentVote = rs.getInt("votes_received");
//               System.out.println(currentVote);
           }

           int newVote = currentVote + 1;
           System.out.println(newVote);
           pst = conn.prepareStatement("UPDATE candidates SET votes_received = "+ newVote + " WHERE candidate_nickname = '" + vote + "'");
           pst.execute();

           // Switch to the next Voting panel after the vote is recorded
           Stage stage = (Stage)chairVoteButton.getScene().getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("vote2.fxml"));
           stage.setTitle("Voters Arise!");
           Scene scene = new Scene(root);
           stage.setScene(scene);
           Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
           // Set Stage boundaries to visible bounds of main screen
           stage.setX(primaryScreenBounds.getMinX());
           stage.setY(primaryScreenBounds.getMinY());
           stage.setWidth(primaryScreenBounds.getWidth());
           stage.setHeight(primaryScreenBounds.getHeight());



       } catch (ClassNotFoundException | SQLException | IOException e) {
           e.printStackTrace();
       }
   }


    /**
     * This method fills the combo box with values from the database for Vice-Chairman candidates
     */
    @FXML
    public void fillViceChair() {
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;

        try {
            Class.forName("org.postgresql.Driver");

            Properties props = new Properties();
            props.setProperty("user", "wayne");
            props.setProperty("password", "admin");

            String dburl = "jdbc:postgresql://" + getDataConnect();
            conn = DriverManager.getConnection(dburl, props);

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Vice-Chairman'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VchairVote.setItems(candidates);
   }

    @FXML
    public void handleVChairVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = VchairVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        // Create the database connection
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;

        try {


            Class.forName("org.postgresql.Driver");

            Properties props = new Properties();
            props.setProperty("user", "wayne");
            props.setProperty("password", "admin");

            String dburl = "jdbc:postgresql://" + getDataConnect();
            conn = DriverManager.getConnection(dburl, props);

            PreparedStatement nick = conn.prepareStatement("SELECT votes_received FROM candidates WHERE candidate_nickname = '" + vote + "'");
            rs = nick.executeQuery();

            while (rs.next()) {
                currentVote = rs.getInt("votes_received");
//               System.out.println(currentVote);
            }

            int newVote = currentVote + 1;
            System.out.println(newVote);
            pst = conn.prepareStatement("UPDATE candidates SET votes_received = "+ newVote + " WHERE candidate_nickname = '" + vote + "'");
            pst.execute();

            // Switch to the next Voting panel after the vote is recorded
            Stage stage = (Stage)VchairVoteButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("vote3.fxml"));
            stage.setTitle("Voters Arise!");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            // Set Stage boundaries to visible bounds of main screen
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());



        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
