package apiRequest.adapters;

import com.google.common.reflect.TypeToken;
import apiRequest.adapters.endpoints.ProjectEndpoints;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectsAdapter extends BaseAdapter {

    public List<Project> get() {

        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_ALL_PROJECTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return (List<Project>) gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>() {
        }.getType());

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
