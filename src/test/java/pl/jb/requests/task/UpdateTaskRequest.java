package pl.jb.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.jb.requests.BaseRequest;
import pl.jb.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject task, String taskId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .put(ClickUpUrl.getTaskUrl(taskId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
