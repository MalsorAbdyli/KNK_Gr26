package project.controllers1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import project.components.ErrorPopupComponent;
import project.components.PaginationComponent;
import project.components.GameCardComponent;
import project.controllers1.ChildController;
import project.controllers1.MainController;
import project.controllers1.UserDetailsController;
import project.models.Game;
import project.models.Game;
import project.repository.GameRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameListController extends ChildController {
    private final int PAGE_SIZE = 10;

    private PaginationComponent paginationComponent;

    @FXML
    private FlowPane usersPane;
    @FXML
    private HBox paginationPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            super.initialize(location, resources);
            paginationComponent = new PaginationComponent(gameCount(), PAGE_SIZE);
            paginationComponent.render(paginationPane, (page) -> {
                try {
                    showGames(page);
                } catch (Exception e) {
                    ErrorPopupComponent.show(e);
                }
            });

            showGames(0);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    private int gameCount() throws Exception {
        return GameRepository.count();
    }

    private void showGames(int page) throws Exception {
        usersPane.getChildren().clear();
        List<Game> games = GameRepository.getAll(PAGE_SIZE, page);
        GameCardComponent userCard = new GameCardComponent();
        for (Game game : games) {
            usersPane.getChildren()
                    .add(userCard.getContent(game, e -> showGame(game), e -> removeGame(game)));
        }
    }

    private void removeGame(Game game) {
        try {
            GameRepository.remove(game.getId());
            showGames(paginationComponent.getCursor());
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    private void showGame(Game game) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/" + project.controllers1.MainController.GAME_DETAILS_VIEW + ".fxml"));

            Pane pane = loader.load();
            GameDetailsController controller = loader.getController();
            controller.setModel(game);

            parentController.setView(MainController.GAME_DETAILS_VIEW, pane, controller);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
}