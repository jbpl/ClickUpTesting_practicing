package pl.jb.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jb.dto.request.task.CreateTaskRequestDto;
import pl.jb.requests.list.CreateFolderlessListRequest;
import pl.jb.requests.space.CreateSpaceRequest;
import pl.jb.requests.space.DeleteSpaceRequest;
import pl.jb.requests.task.CreateTaskRequest;
import pl.jb.requests.task.UpdateTaskRequest;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static final String SPACE_NAME = "Space created with Java";
    private static final String LIST_NAME = "List created with Java";
    private static final String TASK_NAME = "Task name created with Java";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createFolderlessListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(SPACE_NAME);

        return jsonData.getString("id");
    }

    private String createFolderlessListStep() {
        JSONObject json = new JSONObject();
        json.put("name", LIST_NAME);

        final var response = CreateFolderlessListRequest.createFolderlessList(json, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(LIST_NAME);

        return jsonData.getString("id");
    }

    private String createTaskStep() {

        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(TASK_NAME);
        taskDto.setDescription("Description set with Java");
        taskDto.setStatus("to do");

        final var response = CreateTaskRequest.createTask(taskDto, listId);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(TASK_NAME);
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Description set with Java");
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("to do");

        return jsonData.getString("id");
    }

    private void updateTaskStep() {
        JSONObject json = new JSONObject();
        json.put("name", "Task name CHANGED with Java");
        json.put("description", "Description CHANGED with Java");

        final var response = UpdateTaskRequest.updateTask(json, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("Task name CHANGED with Java");
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Description CHANGED with Java");
    }

    private void closeTaskStep() {
        JSONObject json = new JSONObject();
        json.put("status", "complete");

        final var response = UpdateTaskRequest.updateTask(json, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {
        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
