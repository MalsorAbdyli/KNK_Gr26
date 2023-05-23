package project.controllers1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.components.AboutComponent;
import project.components.ErrorPopupComponent;
import project.models.*;
import project.utils.AppConfig;
import project.utils.DateHelper;
import project.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends project.controllers1.BaseController {
    public static final String PRODUCT_DETAILS_VIEW = "product-details";
    public static final String PRODUCT_LIST_VIEW = "product-list";
    public static final String USER_DETAILS_VIEW = "user-details";
    public static final String USER_LIST_VIEW = "user-list";
    public static final String GAME_DETAILS_VIEW = "game-details";
    public static final String GAME_LIST_VIEW = "game-list";
    public static final String STANDINGS_LIST_VIEW = "standings-list";
    private static final String VIEW_PATH = "../view";

    private BaseController childController;
    private String activeView = "";

    @FXML
    private Label navLabel;
    @FXML
    private Button navTeamsButton;
    @FXML
    private Button navUsersButton;
    @FXML
    private Button navGamesButton;
    @FXML
    private Button navStandingsButton;
    @FXML
    private Button navLogoutButton;
    @FXML
    private VBox contentPage;
    @FXML
    private Label statusLabel;
    @FXML
    private Label sectionLabel;
    @FXML
    private CheckMenuItem enCheckMenuItem;
    @FXML
    private CheckMenuItem alCheckMenuItem;
    @FXML
    private MenuItem userMenuItem;
    @FXML
    private MenuItem teamMenuItem;
    @FXML
    private MenuItem gameMenuItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        boolean enSelected = AppConfig.get().getLanguage() == LangEnum.EN;
        enCheckMenuItem.setSelected(enSelected);
        alCheckMenuItem.setSelected(!enSelected);

        // check role access
        if (SessionManager.user.getRole() == UserRole.Employee) {
            ((Pane) navUsersButton.getParent()).getChildren().remove(navUsersButton);
            userMenuItem.getParentMenu().getItems().remove(userMenuItem);
            userMenuItem.setOnAction(null);
        }
    }

    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(this.viewPath(view)));
        Pane pane = loader.load();
        project.controllers1.ChildController controller = loader.getController();
        setView(view, pane, controller);
    }


    public void setView(String view, Parent node, ChildController controller) throws Exception {
        childController = controller;
        controller.setParentController(this);

        contentPage.getChildren().clear();
        contentPage.getChildren().add(node);
        VBox.setVgrow(node, Priority.ALWAYS);

        switch (view) {
            case PRODUCT_DETAILS_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case PRODUCT_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_LEFT);
                break;
            case USER_DETAILS_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case USER_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case GAME_DETAILS_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case GAME_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case STANDINGS_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        activeView = view;
        loadLangTexts(getLangBundle());
    }

    private String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }

    @FXML
    public void onTeamsNavClick(ActionEvent ev) {
        try {
            this.setView(PRODUCT_LIST_VIEW);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onStandingsNavClick(ActionEvent ev) {
        try {
            this.setView(STANDINGS_LIST_VIEW);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onUsersNavClick(ActionEvent ev) {
        try {
            this.setView(USER_LIST_VIEW);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onGamesNavClick(ActionEvent ev) {
        try {
            this.setView(GAME_LIST_VIEW);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onLogoutNavClick(ActionEvent ev) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(viewPath("login")));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);

            SessionManager.user = null;
            SessionManager.lastLogin = null;
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onLogoutMenuClick(ActionEvent ev) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(viewPath("login")));
            Scene scene = new Scene(parent);

            Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
            primaryStage.setScene(scene);

            SessionManager.user = null;
            SessionManager.lastLogin = null;
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onExitMenuClick(ActionEvent ev) {
        try {
            Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
            primaryStage.close();
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onInsertTeamClick(ActionEvent ev) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewPath(PRODUCT_DETAILS_VIEW)));
            Parent node = loader.load();

            TeamDetailsController controller = loader.getController();
            controller.setModel(new Team());
            controller.setEditable(true);

            this.setView(PRODUCT_DETAILS_VIEW, node, controller);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onInsertUserClick(ActionEvent ev) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewPath(USER_DETAILS_VIEW)));
            Parent node = loader.load();

            UserDetailsController controller = loader.getController();
            controller.setModel(new User());

            this.setView(USER_DETAILS_VIEW, node, controller);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onInsertGameClick(ActionEvent ev) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(viewPath(GAME_DETAILS_VIEW)));
            Parent node = loader.load();

            GameDetailsController controller = loader.getController();
            controller.setModel(new Game());

            this.setView(GAME_DETAILS_VIEW, node, controller);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    public void onAboutClick(ActionEvent ev) {
        try {
            new AboutComponent().showDialog();
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onChangeLangMenuItemEnClick(ActionEvent event) {
        enCheckMenuItem.setSelected(true);
        alCheckMenuItem.setSelected(false);
        changeUILanguage();
    }

    @FXML
    private void onChangeLangMenuItemAlClick(ActionEvent event) {
        enCheckMenuItem.setSelected(false);
        alCheckMenuItem.setSelected(true);
        changeUILanguage();
    }

    private void changeUILanguage() {
        try {
            LangEnum lang = enCheckMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
            AppConfig.get().setLanguage(lang);

            ResourceBundle langBundle = getLangBundle();
            loadLangTexts(langBundle);
            if (childController != null) childController.loadLangTexts(langBundle);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String navLabelTxt = langBundle.getString("main_nav_label");
        String navTeamsTxt =langBundle.getString("main_nav_teams");
        String navGamesTxt =langBundle.getString("main_nav_games");
        String navStandingsTxt =langBundle.getString("main_nav_standings");
        String navUsersTxt = langBundle.getString("main_nav_users");
        String navLogoutTxt = langBundle.getString("main_nav_logout");
        String statusLabelTxt = langBundle.getString("main_status_label");

        String user = SessionManager.user.getEmail();
        String loginTime = DateHelper.toSqlFormat(SessionManager.lastLogin);

        statusLabel.setText(String.format(statusLabelTxt, user, loginTime));
        navLabel.setText(navLabelTxt);
        navTeamsButton.setText(navTeamsTxt);
        navUsersButton.setText(navUsersTxt);
        navGamesButton.setText(navGamesTxt);
        navStandingsButton.setText(navStandingsTxt);
        navLogoutButton.setText(navLogoutTxt);

        switch (activeView) {
            case PRODUCT_DETAILS_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_team_details"));
                break;
            case PRODUCT_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_team_list"));
                break;
            case USER_DETAILS_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_user_details"));
                break;
            case USER_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_user_list"));
                break;
            case GAME_DETAILS_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_game_details"));
                break;
            case GAME_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_game_list"));
                break;
            case STANDINGS_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_standing_list"));
                break;
        }

        if (childController != null) childController.loadLangTexts(langBundle);
    }
}
