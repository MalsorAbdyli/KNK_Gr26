package project.utils;

import project.models.LangEnum;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Properties;

public class AppConfig {
    private static project.utils.AppConfig instance;
    private Properties props;

    private AppConfig() {
        try {
            props = new Properties();
            props.load(getClass().getResourceAsStream("../resources/config.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static project.utils.AppConfig get() {
        if (instance == null) {
            instance = new project.utils.AppConfig();
        }
        return instance;
    }

    public String getAppName() {
        return props.getProperty("appname", "Kosovo Basketball League");
    }

    public String getNBA() {
        return props.getProperty("team", "teams");
    }

    public String getReleased() {
        return props.getProperty("released", "2023-05-24");
    }

    public String getConnectionString() {
        return props.getProperty("connectionString");
    }

    public String getDriverType() {
        return props.getProperty("driverType");
    }

    public LangEnum getLanguage() {
        return props.getProperty("lang", "en").equals("en") ? LangEnum.EN : LangEnum.AL;
    }

    public void setLanguage(LangEnum lang) throws Exception {
        URI confPath = getClass().getResource("../resources/config.properties").toURI();
        props.setProperty("lang", lang == LangEnum.EN ? "en" : "al");
        props.store(new FileOutputStream(new File(confPath)), "");
    }
}