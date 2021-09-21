package ApiRequestAdapters.adapters;

import ApiRequestAdapters.adapters.endpoints.ProjectEndpoints;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.AllProjects;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectsAdapter extends BaseAdapter {

    public AllProjects get() {

        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        log.info(response.jsonPath().get("projects").toString());

        return gson.fromJson(response.asString().trim(), AllProjects.class);
    }

    public Project get(int ID) {

        Response response = given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, ID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);
    }

    @Step("Create a project by POST API request")
    public Project add(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Project.class);
    }

    public void delete(int projectId) {
        given()
                .when()
                .post(String.format(ProjectEndpoints.DELETE_PROJECTS, projectId))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
