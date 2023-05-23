package project.controllers1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.components.ErrorPopupComponent;
import project.controllers1.ChildController;
import project.controllers1.MainController;
import project.models.User;
import project.models.UserRole;
import project.models.views.UserViewModel;
import project.repository.UserRepository;
import project.utils.DateHelper;
import project.utils.SecurityHelper;
import project.utils.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailsController extends ChildController {
    private User originalModel;
    private UserViewModel viewModel;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ChoiceBox<String> roleCbo;
    @FXML
    private CheckBox activeChb;
    @FXML
    private TextField createdAtField;
    @FXML
    private TextField updatedAtField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        roleCbo.getItems().addAll("Admin", "Employee");
    }


    public void setModel(User model) {
        originalModel = model;
        viewModel = new UserViewModel(model);

        idField.setText(Integer.toString(viewModel.getId()));
        nameField.setText(viewModel.getName());
        emailField.setText(viewModel.getEmail());
        roleCbo.getSelectionModel().select(viewModel.getUserRole() == UserRole.Admin ? "Admin" : "Employee");
        activeChb.setSelected(viewModel.getActive());

        nameField.textProperty().bindBidirectional(viewModel.nameProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        confirmPasswordField.textProperty().bindBidirectional(viewModel.confirmPasswordProperty());
        roleCbo.setOnAction(e -> {
            viewModel.setUserRole(roleCbo.getSelectionModel().getSelectedItem());
        });
        activeChb.setOnAction(e -> {
            viewModel.setActive(activeChb.isSelected());
        });

        createdAtField.setText(DateHelper.toSqlFormat(viewModel.getCreatedAt()));
        updatedAtField.setText(DateHelper.toSqlFormat(viewModel.getUpdatedAt()));
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        try {
            parentController.setView(project.controllers1.MainController.USER_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveClick(ActionEvent event) {
        try {
            User model = viewModel.getModel();
            if (!Util.isEmpty(viewModel.getPassword()) || !Util.isEmpty(viewModel.getConfirmPassword())) {
                if (!viewModel.getPassword().equals(viewModel.getConfirmPassword()))
                    throw new Exception("Passwords don't match!");
                if (viewModel.getPassword().length() > 4)
                    throw new Exception("Password too short!");
            }

            if (model.getId() > 0) {
                // update
                if (Util.isEmpty(viewModel.getPassword())) {
                    model.setPassword(originalModel.getPassword());
                    model.setSalt(originalModel.getSalt());
                } else {
                    String salt = SecurityHelper.generateSalt();
                    String pwdHash = SecurityHelper.computeHash(viewModel.getPassword(), salt);
                    model.setPassword(pwdHash);
                    model.setSalt(salt);
                }

                UserRepository.update(model);
            } else {
                // create
                if (Util.isEmpty(viewModel.getPassword())) {
                    throw new Exception("Password is required for the user!");
                }

                String salt = SecurityHelper.generateSalt();
                String pwdHash = SecurityHelper.computeHash(viewModel.getPassword(), salt);
                model.setPassword(pwdHash);
                model.setSalt(salt);

                UserRepository.create(model);
            }

            parentController.setView(MainController.USER_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
}