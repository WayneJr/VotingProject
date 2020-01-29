package sample.admin;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    private JFXButton addPart;


    public String getCandidateID() {
        return candID.getText();
    }

    public String getCandidateName() {
        return candName.getText();
    }

    public String getCandidateNick() {
        return candPosV.getText();
    }

}
