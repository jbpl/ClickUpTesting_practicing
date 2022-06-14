package pl.jb.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.jb.properties.ClickUpProperties;
import pl.jb.requests.BaseRequest;
import pl.jb.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject space) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .post(ClickUpUrl.getSpacesUrl(ClickUpProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
