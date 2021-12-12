package pl.javastart.restassured.tests.user;

import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.test.data.UserTestDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

  private User user;

  @Test
  public void givenUserWhenPostUserThenUserIsCreatedTest() {
    UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator(); // Tworzymy generator
    user = userTestDataGenerator.generateUser();

    ApiResponse userCreatedRespone = given().contentType("application/json")
            .body(user)
            .when().post("user")
            .then().statusCode(200)
            .extract().as(ApiResponse.class);

    Assertions.assertThat(userCreatedRespone.getCode()).isEqualTo(200);
    Assertions.assertThat(userCreatedRespone.getType()).isEqualTo("unknown");
    Assertions.assertThat(userCreatedRespone.getMessage()).isEqualTo(String.valueOf(user.getId()));
  }

  @AfterTest
  public void cleanUpAfterTest(){
    ApiResponse apiResponse = given()
            .contentType(ContentType.JSON)
            .pathParam("username", user.getUsername())
            .when()
            .delete("/user/{username}")
            .then()
            .statusCode(200)
            .extract().as(ApiResponse.class);

    Assertions.assertThat(apiResponse.getMessage().contains(user.getUsername()));

  }
}
