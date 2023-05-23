package project.controllers1;

import javafx.fxml.Initializable;
import project.controllers1.BaseController;
import project.controllers1.MainController;

import java.util.ResourceBundle;

public abstract class ChildController extends BaseController {
    public MainController parentController;

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
    }
}