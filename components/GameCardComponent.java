package project.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import project.controllers1.partials.GameCardController;
import project.models.Game;

public class GameCardComponent {
    public Node getContent(Game game, EventHandler<ActionEvent> editHandler, EventHandler<ActionEvent> deleteHandler) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/partials/game-card.fxml"));
        Node node = loader.load();

        GameCardController controller = loader.getController();
        controller.setUser(game);
        controller.setOnEditAction(editHandler);
        controller.setOnDeleteAction(deleteHandler);

        return node;
    }
}
