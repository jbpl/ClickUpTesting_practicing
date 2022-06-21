package pl.jb.requests.list;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.jb.properties.ClickUpProperties;
import pl.jb.requests.BaseRequest;
import pl.jb.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateFolderlessListRequest {

    public static Response createFolderlessList(JSONObject list, String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(list.toString())
                .when()
                .post(ClickUpUrl.getListsUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
