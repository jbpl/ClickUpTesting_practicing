package pl.jb.tests.e2e;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import pl.jb.requests.list.CreateFolderlessListRequest;
import pl.jb.requests.space.CreateSpaceRequest;
import pl.jb.requests.space.DeleteSpaceRequest;
import pl.jb.requests.task.CreateTaskRequest;
import pl.jb.requests.task.UpdateTaskRequest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateTaskWithRandomDataE2ETest {

    private static Faker faker;
    private String randomSpaceName;
    private String randomListName;
    private String randomTaskName;
    private String randomDescription;

    private static String spaceId;
    private static String listId;
    private static String taskId;

    @BeforeAll
    static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    void BeforeEach() {
        randomSpaceName = faker.food().ingredient();
        randomListName = faker.book().title();
        randomTaskName = faker.name().firstName();
        randomDescription = faker.food().ingredient();
    }

    @Test
    @Order(1)
    void CreateSpaceTest() {
        JSONObject json = new JSONObject();
        json.put("name", randomSpaceName);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(randomSpaceName);

        spaceId = jsonData.getString("id");
    }

    @Test
    @Order(2)
    void CreateFolderlessListTest() {
        JSONObject json = new JSONObject();
        json.put("name", randomListName);

        final var response = CreateFolderlessListRequest.createFolderlessList(json, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(randomListName);

        listId = jsonData.getString("id");
    }

    @Test
    @Order(3)
    void CreateTaskTest() {
        JSONObject json = new JSONObject();
        json.put("name", randomTaskName);
        json.put("description", randomDescription);
        json.put("status", "to do");

        final var response = CreateTaskRequest.createTask(json, listId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(randomTaskName);
        Assertions.assertThat(jsonData.getString("description")).isEqualTo(randomDescription);
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("to do");

        taskId = jsonData.getString("id");
    }

    @Test
    @Order(4)
    void UpdateTaskTest() {
        JSONObject json = new JSONObject();
        json.put("name", randomTaskName);
        json.put("description", randomDescription);

        final var response = UpdateTaskRequest.updateTask(json, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(randomTaskName);
        Assertions.assertThat(jsonData.getString("description")).isEqualTo(randomDescription);
    }

    @Test
    @Order(5)
    void DeleteSpaceTest() {
        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
