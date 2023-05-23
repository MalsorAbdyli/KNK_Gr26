package project.controllers1;

import javafx.fxml.Initializable;
import project.utils.SessionManager;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLangTexts(getLangBundle());
    }

    public ResourceBundle getLangBundle() {
        Locale locale = SessionManager.getLocale();
        return ResourceBundle.getBundle("project.resources.bundles.LangBundle", locale);
    }

    public abstract void loadLangTexts(ResourceBundle langBundle);
}