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
import project.controllers1.ChildController;
import project.controllers1.MainController;
import project.models.Game;
import project.models.Team;
import project.models.User;
import project.models.UserRole;
import project.models.views.TeamViewModel;
import project.models.views.GameViewModel;
import project.models.views.UserViewModel;
import project.repository.TeamRepository;
import project.repository.GameRepository;
import project.utils.DateHelper;
import project.utils.FileHelper;
import project.utils.SecurityHelper;
import project.utils.Util;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GameDetailsController extends ChildController {
    private Game originalModel;
    private GameViewModel viewModel;
    @FXML
    private TextField idField;
    @FXML
    private TextField ekipiIField;
    @FXML
    private TextField ekipiIIField;
    @FXML
    private TextField hourField;
    @FXML
    private TextField resultField;
    @FXML
    private TextField playsAtField;
    @FXML
    private TextField createdAtField;
    @FXML
    private TextField updatedAtField;


    public void setModel(Game model) {
        originalModel = model;
        viewModel = new GameViewModel(model);

        idField.setText(Integer.toString(viewModel.getId()));
        ekipiIField.setText(viewModel.getEkipiI());
        ekipiIIField.setText(viewModel.getEkipiII());
        hourField.setText(viewModel.getHour());
        resultField.setText(viewModel.getResult());
        playsAtField.setText(viewModel.getPlaysAt());

        ekipiIField.textProperty().bindBidirectional(viewModel.ekipiIProperty());
        ekipiIIField.textProperty().bindBidirectional(viewModel.ekipiIIProperty());
        hourField.textProperty().bindBidirectional(viewModel.hourProperty());
        resultField.textProperty().bindBidirectional(viewModel.resultProperty());
        playsAtField.textProperty().bindBidirectional(viewModel.playsAtProperty());

        createdAtField.setText(DateHelper.toSqlFormat(viewModel.getCreatedAt()));
        updatedAtField.setText(DateHelper.toSqlFormat(viewModel.getUpdatedAt()));
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        try {
            parentController.setView(project.controllers1.MainController.GAME_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveClick(ActionEvent event) {
        try {
            Game model = viewModel.getModel();

            if (model.getId() > 0) {
                GameRepository.update(model);
            } else {
                // create
                if (Util.isEmpty(viewModel.getEkipiI())) {
                    throw new Exception("Team 1 is required for the game!");
                }
                if (Util.isEmpty(viewModel.getEkipiII())) {
                    throw new Exception("Team 2 is required for the game!");
                }

                GameRepository.create(model);
            }

            parentController.setView(MainController.GAME_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
}