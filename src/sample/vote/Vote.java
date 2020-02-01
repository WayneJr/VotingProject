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
    public JFXComboBox vChairVote;

    @FXML
    public JFXButton vChairVoteButton;

    @FXML
    public JFXComboBox genVote;

    @FXML
    public JFXButton genVoteButton;

    @FXML
    public JFXComboBox assGenVote;

    @FXML
    public JFXButton assGenVoteButton;

    @FXML
    public JFXComboBox financeVote;

    @FXML
    public JFXButton financeVoteButton;

    @FXML
    public JFXComboBox interiorVote;

    @FXML
    public JFXButton interiorVoteButton;

    @FXML
    public JFXComboBox infoVote;

    @FXML
    public JFXButton infoVoteButton;

    @FXML
    public JFXComboBox socialVote;

    @FXML
    public JFXButton socialVoteButton;

    @FXML
    public JFXComboBox healthVote;

    @FXML
    public JFXButton healthVoteButton;

    @FXML
    public JFXComboBox sportVote;

    @FXML
    public JFXButton sportVoteButton;







    private int currentVote;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        candidates.add("void");
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

       if (vote.equals("void")) {
           try {
               Stage stage = (Stage)chairVoteButton.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("voteVice.fxml"));
               stage.setTitle("Voters Arise!");
               Scene scene = new Scene(root);
               stage.setScene(scene);
               Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
               // Set Stage boundaries to visible bounds of main screen
               stage.setX(primaryScreenBounds.getMinX());
               stage.setY(primaryScreenBounds.getMinY());
               stage.setWidth(primaryScreenBounds.getWidth());
               stage.setHeight(primaryScreenBounds.getHeight());
           } catch (IOException e) {
               e.printStackTrace();
           }
       } else {

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
           Parent root = FXMLLoader.load(getClass().getResource("voteVice.fxml"));
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        vChairVote.setItems(candidates);
   }


    /**
     * This records the vote and moves to the next scene
     */
    @FXML
    public void handleVChairVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = vChairVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) vChairVote.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteGen.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) vChairVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteGen.fxml"));
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

    /**
     * This method fills the combo box with values from the database for General Secretary candidates
     */
    @FXML
    public void fillGen() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'General Secretary'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        genVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleGenVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = genVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage)genVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteAssGen.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           /* // Convert the vote to text format
            String vote = assGenVote.getSelectionModel().getSelectedItem().toString();
            System.out.println(vote);*/

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
                Stage stage = (Stage)assGenVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteFinance.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillAssGen() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Assistant General Secretary'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        candidates.add("void");
        assGenVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleAssGenVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = assGenVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) assGenVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteFinance.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) assGenVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteFinance.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillFinance() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Finance Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        financeVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleFinanceVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = financeVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) financeVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteInterior.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) financeVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteInterior.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillInterior() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Interior Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        interiorVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleInteriorVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = interiorVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) interiorVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteInfo.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) interiorVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteInfo.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillInfo() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Information Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        infoVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleInfoVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = infoVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) infoVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteSocial.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) infoVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteSocial.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillSocial() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Social Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        socialVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleSocialVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = socialVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) socialVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteHealth.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) socialVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteHealth.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Health Minister candidates
     */
    @FXML
    public void fillHealth() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Health Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        healthVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleHealthVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = healthVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) healthVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteSport.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) healthVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteSport.fxml"));
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

    /**
     * This method fills the combo box with values from the database for Assistant General Secretary candidates
     */
    @FXML
    public void fillSport() {
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

            pst = conn.prepareStatement("SELECT candidate_nickname, votes_received FROM candidates WHERE candidate_position = 'Sport Minister'");
            rs = pst.executeQuery();

            while (rs.next()) {
                String nickname;
                nickname = rs.getString("candidate_nickname");
                candidates.add(nickname);
//                System.out.println(currentVote);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        candidates.add("void");
        sportVote.setItems(candidates);
    }

    /**
     * This records the vote and moves to the next scene
     */
    public void handleSportVote(MouseEvent event) {
        // Convert the vote to text format
        String vote = sportVote.getSelectionModel().getSelectedItem().toString();
        System.out.println(vote);

        if (vote.equals("void")) {
            try {
                Stage stage = (Stage) genVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteHlc1.fxml"));
                stage.setTitle("Voters Arise!");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                // Set Stage boundaries to visible bounds of main screen
                stage.setX(primaryScreenBounds.getMinX());
                stage.setY(primaryScreenBounds.getMinY());
                stage.setWidth(primaryScreenBounds.getWidth());
                stage.setHeight(primaryScreenBounds.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

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
                pst = conn.prepareStatement("UPDATE candidates SET votes_received = " + newVote + " WHERE candidate_nickname = '" + vote + "'");
                pst.execute();

                // Switch to the next Voting panel after the vote is recorded
                Stage stage = (Stage) sportVoteButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("voteSport.fxml"));
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


}
