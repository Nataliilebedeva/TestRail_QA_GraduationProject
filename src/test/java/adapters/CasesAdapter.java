package adapters;

import com.google.common.reflect.TypeToken;
import endpoints.CasesEndpoint;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Cases;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CasesAdapter extends BaseAdapter {

    public Cases add(Cases cases, int sectionId) {
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoint.ADD_CASE, sectionId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Cases get(Cases cases) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoint.GET_CASE, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Cases getFailed(Cases cases) {
        Response response = given()
                .when()
                .get(String.format(CasesEndpoint.GET_CASE, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Response getHistory(Cases cases) {
        return given()
                .when()
                .get(String.format(CasesEndpoint.GET_HISTORY_FOR_CASES, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

    }

    public Response getHistoryFailed(Cases cases) {
        return given()
                .when()
                .get(String.format(CasesEndpoint.GET_HISTORY_FOR_CASES, cases.getId()))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().response();

    }

    public Cases updateCase(int caseId, Cases cases){
        Response response = given()
                .body(cases, ObjectMapperType.GSON)
                .when()
                .post(String.format(CasesEndpoint.UPDATE_CASE, caseId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(), Cases.class);
    }

    public Response deleteCases(int projectId, int suitId){
        Response response = given()
                .when()
                .post(String.format(CasesEndpoint.DELETE_CASE, projectId, suitId))
                .then()
                .log().body()
                .log().status()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return response;
    }
}
