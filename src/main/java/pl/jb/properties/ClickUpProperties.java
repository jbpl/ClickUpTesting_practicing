package pl.jb.properties;

import java.util.ResourceBundle;

public class ClickUpProperties {
    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    public static String getToken() {
        return getProperty(TOKEN);
    }

    public static String getTeamId(){
        return getProperty(TEAM_ID);
    }

    public static String getProperty(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}
