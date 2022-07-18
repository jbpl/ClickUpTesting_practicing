package pl.jb.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClickUpUrl {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";
    private static final String LIST = "/list";
    private static final String TASK = "/task";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getTeamsUrl() {
        return TEAM;
    }

    public static String getTeamUrl(String teamId) {
        return getTeamsUrl() + "/" + teamId;
    }

    public static String getSpacesUrl(String teamId) {
        return getTeamUrl(teamId) + SPACE;
    }

    public static String getSpaceUrl(String spaceId) {
        return SPACE + "/" + spaceId;
    }

    public static String getListsUrl(String spaceId) {
        return getSpaceUrl(spaceId) + LIST;
    }

    public static String getListUrl(String listId) {
        return LIST + "/" + listId;
    }

    public static String getTasksUrl(String listId) {
        return getListUrl(listId) + TASK;
    }

    public static String getTaskUrl(String taskId) {
        return TASK + "/" + taskId;
    }
}
