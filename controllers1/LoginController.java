package project.controllers1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.components.ErrorPopupComponent;
import project.controllers1.BaseController;
import project.controllers1.MainController;
import project.models.User;
import project.models.UserRole;
import project.repository.UserRepository;
import project.utils.SecurityHelper;
import project.utils.SessionManager;

import java.util.Date;
import java.util.ResourceBundle;

public class LoginController extends BaseController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String emailTxt = langBundle.getString("login_email");
        String passwordTxt = langBundle.getString("login_password");
        String loginTxt = langBundle.getString("login_button_login");
        String registerTxt = langBundle.getString("login_button_register");

        emailField.setPromptText(emailTxt);
        passwordField.setPromptText(passwordTxt);
        try {
            if (hasUsers()) {
                loginButton.setText(loginTxt);
            } else {
                loginButton.setText(registerTxt);
            }
        } catch (Exception e) {
            loginButton.setText(loginTxt);
        }
    }

    @FXML
    private void onLoginButtonClick(ActionEvent e) {
        try {
            User user = null;
            String email = emailField.getText();
            String password = passwordField.getText();

            user = hasUsers() ? login(email, password) : register(email, password);
            if (user == null) throw new Exception("Invalid credentials");
            SessionManager.user = user;
            SessionManager.lastLogin = new Date();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/main-screen.fxml"));

            Parent parent = loader.load();
            MainController controller = loader.getController();
            controller.setView(MainController.PRODUCT_LIST_VIEW);

            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    private boolean hasUsers() throws Exception {
        return UserRepository.count() > 0;
    }

    private User login(String email, String password) throws Exception {
        User user = UserRepository.find(email);
        if (user == null) return user;

        String hashedPassword = SecurityHelper.computeHash(password, user.getSalt());
        if (!user.getPassword().equals(hashedPassword)) return null;

        return user;
    }

    private User register(String email, String password) throws Exception {
        String salt = SecurityHelper.generateSalt();
        String hashedPassword = SecurityHelper.computeHash(password, salt);

        User user = new User();
        user.setEmail(email);
        user.setName("Default Admin");
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setRole(UserRole.Admin);
        user.setActive(true);

        user = UserRepository.create(user);
        return user;
    }
}
