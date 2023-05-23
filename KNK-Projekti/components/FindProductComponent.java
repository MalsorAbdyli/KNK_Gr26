package project.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.controllers1.partials.FindProductController;

public class FindProductComponent {
    public String showDialog() throws Exception {
        final StringBuilder sb = new StringBuilder();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/partials/find-product.fxml"));

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("KOSOVO LEAGUE - Find Team");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);

        FindProductController controller = loader.getController();
        controller.setOnKeyPressedAction(str -> {
            sb.append(str);
            stage.close();
        });

        stage.showAndWait();

        return sb.toString();
    }
}