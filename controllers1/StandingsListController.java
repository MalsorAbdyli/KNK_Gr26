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
import project.models.Standings;
import project.models.Team;
import project.repository.StandingRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StandingsListController extends ChildController {
    final KeyCombination keyCtrlF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
    private final int PAGE_SIZE = 14;

    @FXML
    private TableView<Standings> tableView;
    @FXML
    private TableColumn<Standings, Integer> idColumn;
    @FXML
    private TableColumn<Standings, String> ekipiColumn;
    @FXML
    private TableColumn<Standings, Integer> wColumn;
    @FXML
    private TableColumn<Standings, Integer> lColumn;
    @FXML
    private TableColumn<Standings, String> streakColumn;
    @FXML
    private TableColumn<Standings, String> last_10Column;
    @FXML
    private TableColumn<Standings, Float> winColumn;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            super.initialize(location, resources);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            ekipiColumn.setCellValueFactory(new PropertyValueFactory<>("ekipi"));
            wColumn.setCellValueFactory(new PropertyValueFactory<>("w"));
            lColumn.setCellValueFactory(new PropertyValueFactory<>("l"));
            streakColumn.setCellValueFactory(new PropertyValueFactory<>("streak"));
            last_10Column.setCellValueFactory(new PropertyValueFactory<>("last_10"));
            winColumn.setCellValueFactory(new PropertyValueFactory<>("win"));
            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            showStandings(0);
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
            showStandings(0);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onFindClick(ActionEvent ev) {
        try {
            List<Standings> standing = findStandings();
            tableView.getItems().clear();
            tableView.setItems(FXCollections.observableList(standing));
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onRemoveMenuItemClick(ActionEvent ev) {
        try {
            for (Standings p : tableView.getSelectionModel().getSelectedItems()) {
                StandingRepository.remove(p.getId());
            }
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void onScreenKeyPressed(KeyEvent event) {
        if (keyCtrlF.match(event)) {
            try {
                List<Standings> standing = this.findStandings();
                tableView.getItems().clear();
                tableView.setItems(FXCollections.observableArrayList(standing));
            } catch (Exception e) {
                ErrorPopupComponent.show(e);
            }
        }
    }

    private void showStandings(int page) throws Exception {
        List<Standings> standings = StandingRepository.getAll(PAGE_SIZE, page);
        tableView.getItems().clear();
        tableView.setItems(FXCollections.observableList(standings));
    }

    private List<Standings> findStandings() throws Exception {
        String text = new FindProductComponent().showDialog();
        if (text.length() == 0) return new ArrayList<>();
        return StandingRepository.find(text);
    }

    private int standingCount() throws Exception {
        return StandingRepository.count();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        super.loadLangTexts(langBundle);

        String showAllTxt = langBundle.getString("product_list_show_all_button");
        showAllButton.setText(showAllTxt);
    }
}