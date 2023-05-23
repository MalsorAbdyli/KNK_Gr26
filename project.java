package project;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.*;
import project.utils.DbHelper;
import project.utils.FileHelper;

public class project extends Application {
    public static void main(String[] args) throws Exception {
        DbHelper.migrate();
        FileHelper.get().loadImageDir();
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Kosovo Basketball League Results");
        primaryStage.show();

        primaryStage.sizeToScene();
        primaryStage.setMinWidth(scene.getWidth());
        primaryStage.setMinHeight(scene.getHeight());
    }
}
