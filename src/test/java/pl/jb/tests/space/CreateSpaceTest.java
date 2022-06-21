package pl.jb.tests.space;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.jb.requests.space.CreateSpaceRequest;
import pl.jb.requests.space.DeleteSpaceRequest;

class CreateSpaceTest {
    private static final String SPACE_NAME = "Space name created with Java";

    @Test
    void CreateSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var createResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createResponse.statusCode()).isEqualTo(200);

        JsonPath json = createResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(SPACE_NAME);

        final var spaceId = json.getString("id");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(deleteResponse.body()).isNotNull();
    }
}
