package utils;

import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

@UtilityClass
public class APIUtils {

    public static <T> List<T> getAllRequests(String request, int statusCode, String contentType, Class<T> model){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(request)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .and()
                .contentType(contentType)
                .extract()
                .jsonPath().getList("", model);
    }

    public static <T> List<T> getSpecificRequest(String request, String param, String paramValue, int statusCode, Class<T> model){
        return given()
                .contentType(ContentType.JSON)
                .param(param, paramValue)
                .when()
                .get(request)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .jsonPath().getList("", model);
    }

    public static <T> T getPostRequest(String requestBody, String contentType, String request, int statusCode, Class<T> model){
        return given()
                .contentType(contentType)
                .and()
                .body(requestBody)
                .when()
                .post(request)
                .then()
                .assertThat().statusCode(statusCode)
                .extract()
                .response().getBody().as((Type) model);
    }
}
