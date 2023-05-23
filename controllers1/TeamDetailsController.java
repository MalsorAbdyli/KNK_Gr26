package project.controllers1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.components.ErrorPopupComponent;
import project.models.Team;
import project.models.views.TeamViewModel;
import project.repository.TeamRepository;
import project.utils.DateHelper;
import project.utils.FileHelper;
import project.utils.Util;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TeamDetailsController extends ChildController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField coachField;
    @FXML
    private ImageView logoField;
    @FXML
    private TextField nr_playersField;
    @FXML
    private TextField createdAtField;
    @FXML
    private TextField updatedAtField;


    private boolean isEditable = false;
    private TeamViewModel viewModel;
    private FileChooser fileChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("logo", "*.png", "*.jpg", "*.jpeg"));
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        try {
            parentController.setView(project.controllers1.MainController.PRODUCT_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        try {
            if (viewModel.getId() > 0) {
                TeamRepository.update(viewModel.getModel());
            } else {
                TeamRepository.create(viewModel.getModel());
            }
            parentController.setView(MainController.PRODUCT_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    public void setEditable(boolean editable) {
        this.isEditable = editable;

        nameField.setDisable(!isEditable);
        coachField.setDisable(!isEditable);
        nr_playersField.setDisable(!isEditable);

        if (isEditable) {
            logoField.setOnMouseClicked(e -> this.onImageClick(e));
        } else {
            logoField.setOnMouseClicked(null);
        }
    }

    public void setModel(Team model) {
        viewModel = new TeamViewModel(model);

        idField.setText(Integer.toString(viewModel.getId()));
        nameField.setText(viewModel.getName());
        coachField.setText(viewModel.getCoach());
        if (!Util.isEmpty(viewModel.getLogo())) {
            File src = new File(FileHelper.get().getImageDir() + "/" + viewModel.getLogo());
            Image image = new Image(src.toURI().toString());
            logoField.setImage(image);
        }
        nr_playersField.setText(Double.toString(viewModel.getNr_players()));
        createdAtField.setText(DateHelper.toSqlFormat(viewModel.getCreatedAt()));
        updatedAtField.setText(DateHelper.toSqlFormat(viewModel.getUpdatedAt()));

        nameField.textProperty().bindBidirectional(viewModel.nameProperty());
        coachField.textProperty().bindBidirectional(viewModel.coachProperty());
        nr_playersField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Util.isEmpty(newVal)) {
                try {
                    viewModel.setNr_players(Integer.parseInt(newVal));
                } catch (Exception e) {
                }
            }
        });
    }

    private void onImageClick(MouseEvent event) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            File srcFile = fileChooser.showOpenDialog(primaryStage);
            if (srcFile != null) {
                FileHelper fh = FileHelper.get();
                String filename = new Date().getTime() + (int) (Math.random() * 100) + "." + fh.fileExt(srcFile);
                File destFile = new File(fh.getImageDir() + "/" + filename);
                fh.copyFile(srcFile, destFile);

                Image image = new Image(destFile.toURI().toString());
                logoField.setImage(image);
                viewModel.setLogo(filename);
            }
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
}
