package apitest;

import config.ApplicationConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersTest {
    ApplicationConfig config = new ApplicationConfig();
    @Test
    public void loginTest(){
        Authorization auth= new Authorization(config.userPassword,config.username);
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post(config.apiUrl + "/login")
                .then()
                .log().ifValidationFails()
                .statusCode(202);

    }
    @Test
    public void UsersTest(){
        List<UserBody> usersList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(config.apiUrl + "/users")
                .then().log().all()
                .extract().body().jsonPath().getList("$", UserBody.class);
    }

}
