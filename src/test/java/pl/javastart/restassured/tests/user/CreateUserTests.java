package pl.javastart.restassured.tests.user;

import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

  @Test
  public void givenUserWhenPostUserThenUserIsCreatedTest() {
    User user = User.builder()
            .id(10).username("patkowicz").firstName("Patrycja").lastName("Żurnalska").email("patka@test.com")
            .password("13123@#123eqw").phone("+123456789").userStatus(123).build();

    given().contentType("application/json")
            .body(user)
            .when().post("user")
            .then().statusCode(200);
  }

}
