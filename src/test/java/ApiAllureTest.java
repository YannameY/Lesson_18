import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("Тестирование API")
@Feature("GET Запросы")
public class ApiAllureTest {

    @Test
    @DisplayName("Проверка GET-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение GET-запроса к API Postman Echo с параметрами запроса.")
    @Story("отправляет корректный GET-запрос и проверяет ответ")
    public void testGetRequest() {
        baseURI = "https://postman-echo.com";

        given().log().all()
                .contentType("application/json")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when().get("/get")
                .then().log().all()
                .statusCode(200)

                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", notNullValue())
                .body("headers.accept-encoding", equalTo("gzip,deflate"))
                // .body("headers.cookie", not(emptyOrNullString()))
                .body("headers.json", nullValue())
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
    @Test
    @DisplayName("Проверка POST-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение POST-запроса к API Postman Echo")
    @Story("отправляет корректный POST-запрос и проверяет ответ")
    public void testPostRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given().log().all()
                .contentType("application/json")
                .when().post("/post")
                .then().log().all()
                .statusCode(200)

                .body("args", equalTo(Collections.emptyMap()))
                .body("data", not(emptyOrNullString()))
                .body("files", equalTo(Collections.emptyMap()))
                .body("from", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                .body("headers.content-length", not(emptyOrNullString()))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                //  .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", not(emptyOrNullString()))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", not(emptyOrNullString()))
                // .body("headers.accept-encoding", equalTo("gzip,deflate, br"))
                // .body("headers.cookie", not(emptyOrNullString()));
                .body("headers.json",  nullValue())
                .body("url", equalTo("https://postman-echo.com/post"));
    }


    @Test
    @DisplayName("Проверка POST-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение POST-запроса к API Postman Echo")
    @Story("отправляет корректный POST-запрос и проверяет ответ")
    public void testPostDataRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given().log().all()
                .contentType("application/json")
                .when().post("/post")
                .then().log().all()
                .statusCode(200)

                .body("args", equalTo(Collections.emptyMap()))
                // .body("data", nullValue())
                .body("files", equalTo(Collections.emptyMap()))
                .body("from", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                //  .body("headers.content-length", equalTo("19"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                // .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", not(emptyOrNullString()))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", not(emptyOrNullString()))
                // .body("headers.accept-encoding", equalTo("gzip,deflate, br"))
                // .body("headers.cookie", not(emptyOrNullString()))
                // .body("headers.contetnType", equalTo("application/x-www-form-urlencoded"))
                // .body("headers.json",  nullValue())
                .body("url", equalTo("https://postman-echo.com/post"));

    }

    @Test
    @DisplayName("Проверка PUT-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение PUT-запроса к API Postman Echo")
    @Story("отправляет корректный PUT-запрос и проверяет ответ")
    public void testPutRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given().log().all()
                .contentType("application/json")
                .when().put("/put")
                .then().log().all()
                .statusCode(200)

                .body("args", equalTo(Collections.emptyMap()))
                // .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("from", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                // .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                // .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", not(emptyOrNullString()))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", not(emptyOrNullString()))
                // .body("headers.accept-encoding", equalTo("gzip,deflate, br"))
                // .body("headers.cookie", not(emptyOrNullString()))
                .body("headers.json",  nullValue())
                .body("url", equalTo("https://postman-echo.com/put"));



    }

    @Test
    @DisplayName("Проверка PATCH-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение PATCH-запроса к API Postman Echo")
    @Story("отправляет корректный PATCH-запрос и проверяет ответ")
    public void testPatchRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given().log().all()
                .contentType("application/json")
                .when().patch("/patch")
                .then().log().all()
                .statusCode(200)

                .body("args", equalTo(Collections.emptyMap()))
                // .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("from", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                // .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                // .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", not(emptyOrNullString()))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", not(emptyOrNullString()))
                // .body("headers.accept-encoding", equalTo("gzip,deflate, br"))
                // .body("headers.cookie", not(emptyOrNullString()))
                .body("headers.json",  nullValue())
                .body("url", equalTo("https://postman-echo.com/patch"));

    }

    @Test
    @DisplayName("Проверка DELETE-запроса к API Postman Echo")
    @Description("Этот тест проверяет поведение DELETE-запроса к API Postman Echo")
    @Story("отправляет корректный DELETE-запрос и проверяет ответ")
    public void testDeleteRequest() {
        RestAssured.baseURI = "https://postman-echo.com";
        given().log().all()
                .contentType("application/json")
                .when().delete("/delete")
                .then().log().all()
                .statusCode(200)

                .body("args", equalTo(Collections.emptyMap()))
                // .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("files", equalTo(Collections.emptyMap()))
                .body("from", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.x-request-start", not(emptyOrNullString()))
                .body("headers.connection", equalTo("close"))
                // .body("headers.content-length", equalTo("58"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.x-forwarded-port", equalTo("443"))
                .body("headers.x-amzn-trace-id", not(emptyOrNullString()))
                // .body("headers.content-type", equalTo("text/plain"))
                .body("headers.user-agent", not(emptyOrNullString()))
                .body("headers.accept", not(emptyOrNullString()))
                .body("headers.cache-control", nullValue())
                // .body("headers.postman-token", not(emptyOrNullString()))
                // .body("headers.accept-encoding", equalTo("gzip,deflate, br"))
                // .body("headers.cookie", not(emptyOrNullString()))
                .body("headers.json",  nullValue())
                .body("url", equalTo("https://postman-echo.com/delete"));


    }
}
