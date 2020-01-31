package sample.vote;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

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
        chairVote.setItems(candidates);

    }


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

//            String dburl = "jdbc:postgresql://localhost:5432/mellanbytest";

            String dburl = "jdbc:postgresql://" + getDataConnect();
            conn = DriverManager.getConnection(dburl, props);

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Hall Chairman'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
                currentVote = rs.getInt("votes_received");
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // "SELECT candidate_nickname FROM candidates WHERE candidate_position = 'Hall Chairman'"

   /* public String getConnect() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/root/connection.txt"));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

    }*/


   @FXML
    public void handleVote(MouseEvent event) {
       String vote = chairVote.getSelectionModel().getSelectedItem().toString();
       System.out.println(vote);
       int newVote = currentVote + 1;

       System.out.println(newVote);

       Connection conn;
       PreparedStatement pst;
       ResultSet rs;

       try {
           Class.forName("org.postgresql.Driver");

           Properties props = new Properties();
           props.setProperty("user", "wayne");
           props.setProperty("password", "admin");

           String dburl = "jdbc:postgresql://localhost:5432/mellanbytest";

//            String dburl = "jdbc:postgresql://" + getConnect();
           conn = DriverManager.getConnection(dburl, props);

           pst = conn.prepareStatement("UPDATE candidates SET votes_received = "+ newVote + " WHERE candidate_nickname = '" + vote + "'");
           pst.execute();

           /*while (rs.next()) {

//                System.out.println(currentVote);
           }*/
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }



   }
}
