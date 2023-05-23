package project.utils;

import project.models.LangEnum;
import project.models.User;
import project.utils.AppConfig;

import java.util.Date;
import java.util.Locale;

public class SessionManager {
    public static User user = null;
    public static Date lastLogin = null;

    public static Locale getLocale() {
        LangEnum lang = AppConfig.get().getLanguage();
        if (lang == LangEnum.EN)
            return new Locale("en", "US");
        else
            return new Locale("al", "AL");
    }
}
