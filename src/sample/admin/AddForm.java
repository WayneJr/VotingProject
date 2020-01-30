package sample.admin;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.DbConnection;

import java.sql.SQLException;

public class AddForm {

    @FXML
    private TextField candID;

    @FXML
    private TextField candName;

    @FXML
    private TextField candNick;

    @FXML
    private TextField candPosV;

    @FXML
    private TextField serveAddress;

    @FXML
    private JFXButton addPart;


    public String getCandidateID() {
        return candID.getText();
    }

    public String getCandidateName() {
        return candName.getText();
    }

    public String getCandidateNick() {
        return candNick.getText();
    }

    public String getCandidatePosV() {
        return candPosV.getText();
    }

    @FXML
    public void addParticipant(MouseEvent event) {
        DbConnection dbConnect = new DbConnection();
        dbConnect.getConnection(serveAddress.getText());

        System.out.println(serveAddress.getText());

        try {
            String sql = "INSERT INTO candidates(candidate_id, candidate_name, candidate_nickname, candidate_position) values(" +
                    Integer.parseInt(getCandidateID()) + ", '" + getCandidateName() + "', '" + getCandidateNick()
                    + "', '" + getCandidatePosV() + "')";
            DbConnection.pst = DbConnection.connection.prepareStatement(sql);
            DbConnection.pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have Successfully added a candidate");
            alert.setTitle("Candidate Addition");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());


            /*while (rs.next()) {
                int id, votesReceived;
                String candidateName, candidateNickname;

                id = rs.getInt("candidate_id");
                candidateName = rs.getString("candidate_name");
                candidateNickname = rs.getString("candidate_nickname");
                votesReceived = rs.getInt("votes_received");
                System.out.println("ID\t Name\t Nickname\t");
                System.out.println(id + " " + candidateName + " " + candidateNickname + " " + votesReceived);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
