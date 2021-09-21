package ApiRequestAdapters.adapters;

import ApiRequestAdapters.adapters.endpoints.CasesEndpoint;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Cases;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class CasesAdapter extends BaseAdapter {

    @Step("Create a test case by POST API request")
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

    @Step("GET Test Cases API request")
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

    @Step("GET Test Cases API request")
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

    @Step("GET Test Case History API request")
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

    @Step("GET Test Case History API request")
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

    public Cases updateCase(int caseId, Cases cases) {
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
}
