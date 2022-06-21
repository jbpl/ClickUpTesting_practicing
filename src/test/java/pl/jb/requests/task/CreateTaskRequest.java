package pl.jb.requests.task;

import io.restassured.response.Response;
import pl.jb.dto.request.task.CreateTaskRequestDto;
import pl.jb.requests.BaseRequest;
import pl.jb.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response();
    }
}
