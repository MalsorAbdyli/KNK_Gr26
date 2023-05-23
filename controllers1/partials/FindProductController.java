package project.controllers1.partials;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ResourceBundle;

public class FindProductController implements Initializable {
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setOnKeyPressedAction(project.controllers1.partials.FindProductController.SearchSubmittedHandler handler) {
        searchField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handler.run(searchField.getText());
            }
        });
    }

    public interface SearchSubmittedHandler {
        public void run(String str);
    }
}
