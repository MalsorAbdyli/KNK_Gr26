package project.controllers1.partials;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import project.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController implements Initializable {
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Label idLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUser(User user) {
        idLabel.setText("Identifier: " + user.getId());
        nameLabel.setText(user.getName());
        emailLabel.setText(user.getEmail());
        isActiveCheckBox.setSelected(user.getActive());
    }

    public void setOnEditAction(EventHandler<ActionEvent> handler) {
        this.editButton.setOnAction(handler);
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> handler) {
        this.deleteButton.setOnAction(handler);
    }

    public void setOnActiveAction(EventHandler<ActionEvent> handler) {
        this.isActiveCheckBox.setOnAction(handler);
    }
}