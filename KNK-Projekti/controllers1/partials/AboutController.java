package project.controllers1.partials;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import project.utils.AppConfig;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    @FXML
    private Label appNameLabel;
    @FXML
    private Label versionLabel;
    @FXML
    private Label releasedLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        appNameLabel.setText(AppConfig.get().getAppName());
        versionLabel.setText(AppConfig.get().getNBA());
        releasedLabel.setText(AppConfig.get().getReleased());

    }
}
