package pl.jb.tests.space;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.jb.requests.space.CreateSpaceRequest;
import pl.jb.requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

class CreateSpaceWithParametersTest {

    @DisplayName("Create Space with valid data")
    @ParameterizedTest(name = "Create space with name: {0}")
    @MethodSource("sampleSpaceNameData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var createResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createResponse.statusCode()).isEqualTo(200);

        JsonPath json = createResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(spaceName);

        final var spaceId = json.getString("id");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(deleteResponse.body()).isNotNull();
    }

    private static Stream<Arguments> sampleSpaceNameData() {

        return Stream.of(
                Arguments.of("spaceName"),
                Arguments.of("SPACENAME"),
                Arguments.of("SPACE_NAME"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&")
        );
    }
}
