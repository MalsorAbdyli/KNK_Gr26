package project.controllers1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import project.components.ErrorPopupComponent;
import project.components.FindProductComponent;
import project.components.PaginationComponent;
import project.models.Team;
import project.repository.TeamRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamListController extends ChildController {
    final KeyCombination keyCtrlT = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_ANY);
    private final int PAGE_SIZE = 10;

    @FXML
    private TableView<Team> tableView;
    @FXML
    private TableColumn<Team, Integer> idColumn;
    @FXML
    private TableColumn<Team, String> nameColumn;
    @FXML
    private TableColumn<Team, String> coachColumn;
    @FXML
    private TableColumn<Team, Double> nr_playersColumn;
    @FXML
    private HBox paginationPane;
    @FXML
    private ToggleButton multipleButton;
    @FXML
    private Button showAllButton;
    @FXML
    private MenuItem viewMenuItem;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem removeMenuItem;

    private PaginationComponent paginationComponent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            super.initialize(location, resources);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            coachColumn.setCellValueFactory(new PropertyValueFactory<>("coach"));
            nr_playersColumn.setCellValueFactory(new PropertyValueFactory<>("nr_players"));
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            showTeams(0);
            paginationComponent = new PaginationComponent(teamCount(), PAGE_SIZE);
            paginationComponent.render(paginationPane, page -> {
                try {
                    showTeams(page);
                } catch (Exception ex) {
                    ErrorPopupComponent.show(ex);
                }
            });
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onMultipleButtonClick(ActionEvent ev) {
        tableView.getSelectionModel().setSelectionMode(
                multipleButton.isSelected() ? SelectionMode.MULTIPLE : SelectionMode.SINGLE
        );
    }

    @FXML
    private void onContextMenuShowing(WindowEvent ev) {
        int selected = tableView.getSelectionModel().getSelectedItems().size();
        if (selected == 1) {
            editMenuItem.setDisable(false);
            removeMenuItem.setDisable(false);
            viewMenuItem.setDisable(false);
        } else if (selected > 1) {
            editMenuItem.setDisable(true);
            removeMenuItem.setDisable(false);
            viewMenuItem.setDisable(true);
        } else {
            editMenuItem.setDisable(true);
            removeMenuItem.setDisable(true);
            viewMenuItem.setDisable(true);
        }
    }

    @FXML
    private void onShowAllButtonClick(ActionEvent ev) {
        try {
            showTeams(paginationComponent.getCursor());
            paginationPane.setVisible(true);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onFindClick(ActionEvent ev) {
        try {
            List<Team> teams = findTeams();
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableList(teams));
            paginationPane.setVisible(false);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onViewMenuItemClick(ActionEvent ev) {
        Team selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/" + project.controllers1.MainController.PRODUCT_DETAILS_VIEW + ".fxml"));

            Pane pane = loader.load();
            project.controllers1.TeamDetailsController controller = loader.getController();
            controller.setModel(selected);
            controller.setEditable(false);

            parentController.setView(project.controllers1.MainController.PRODUCT_DETAILS_VIEW, pane, controller);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onEditMenuItemClick(ActionEvent ev) {
        Team selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/" + project.controllers1.MainController.PRODUCT_DETAILS_VIEW + ".fxml"));

            Pane pane = loader.load();
            TeamDetailsController controller = loader.getController();
            controller.setModel(selected);
            controller.setEditable(true);

            parentController.setView(MainController.PRODUCT_DETAILS_VIEW, pane, controller);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }

    }

    @FXML
    private void onRemoveMenuItemClick(ActionEvent ev) {
        try {
            for (Team p : tableView.getSelectionModel().getSelectedItems()) {
                TeamRepository.remove(p.getId());
            }
            showTeams(paginationComponent.getCursor());
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onScreenKeyPressed(KeyEvent event) {
        if (keyCtrlT.match(event)) {
            try {
                List<Team> teams = this.findTeams();
                tableView.getItems().clear();
                tableView.setItems(FXCollections.observableArrayList(teams));
                paginationPane.setVisible(false);
            } catch (Exception e) {
                ErrorPopupComponent.show(e);
            }
        }
    }

    private void showTeams(int page) throws Exception {
        List<Team> teams = TeamRepository.getAll(PAGE_SIZE, page);
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableList(teams));
    }

    private List<Team> findTeams() throws Exception {
        String text = new FindProductComponent().showDialog();
        if (text.length() == 0) return new ArrayList<>();
        return TeamRepository.find(text);
    }

    private int teamCount() throws Exception {
        return TeamRepository.count();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        super.loadLangTexts(langBundle);

        String showAllTxt = langBundle.getString("product_list_show_all_button");
        showAllButton.setText(showAllTxt);
    }
}
