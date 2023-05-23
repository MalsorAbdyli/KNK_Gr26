package project.controllers1.partials;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import project.models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameCardController implements Initializable {
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label idLabel;
    @FXML
    private Label ekipiILabel;
    @FXML
    private Label ekipiIILabel;
    @FXML
    private Label hourLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Label playsAtLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUser(Game game) {
        idLabel.setText("Identifier: " + game.getId());
        ekipiILabel.setText(game.getEkipiI());
        ekipiIILabel.setText(game.getEkipiII());
        hourLabel.setText(game.getHour());
        resultLabel.setText(game.getResult());
        playsAtLabel.setText(game.getPlaysAt());
    }

    public void setOnEditAction(EventHandler<ActionEvent> handler) {
        this.editButton.setOnAction(handler);
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> handler) {
        this.deleteButton.setOnAction(handler);
    }
}
