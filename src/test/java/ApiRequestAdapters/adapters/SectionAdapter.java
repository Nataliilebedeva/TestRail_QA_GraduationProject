package ApiRequestAdapters.adapters;

import ApiRequestAdapters.adapters.endpoints.SectionEndpoints;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;


public class SectionAdapter extends BaseAdapter {

    @Step("Create a section by POST API request")
    public Section add(Section section, int projectID) {
        Response response = given()
                .body(section, ObjectMapperType.GSON)
                .when()
                .post(String.format(SectionEndpoints.ADD_SECTION, projectID))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Section.class);
    }
}
