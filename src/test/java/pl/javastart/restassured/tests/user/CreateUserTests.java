package pl.javastart.restassured.tests.user;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.pojo.user.UserCreatedResponse;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

  @Test
  public void givenUserWhenPostUserThenUserIsCreatedTest() {
    User user = User.builder()
            .id(445).username("patkowicz").firstName("Patrycja").lastName("Żurnalska").email("patka@test.com")
            .password("13123@#123eqw").phone("+123456789").userStatus(123).build();

    UserCreatedResponse userCreatedRespone = given().contentType("application/json")
            .body(user)
            .when().post("user")
            .then().statusCode(200)
            .extract().as(UserCreatedResponse.class);

    Assertions.assertThat(userCreatedRespone.getCode()).isEqualTo(200);
    Assertions.assertThat(userCreatedRespone.getType()).isEqualTo("unknown");
    Assertions.assertThat(userCreatedRespone.getMessage()).isEqualTo("445");
  }

}
