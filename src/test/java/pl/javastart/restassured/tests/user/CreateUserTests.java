package pl.javastart.restassured.tests.user;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.user.User;
import pl.javastart.restassured.main.rop.CreateUserEndpoint;
import pl.javastart.restassured.main.rop.DeleteUserEndpoint;
import pl.javastart.restassured.main.test.data.UserTestDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

public class CreateUserTests extends SuiteTestBase {

  private User user;


  @TmsLink("ID-3")
  @Severity(SeverityLevel.CRITICAL)
  @Description("The goal of this test is to create new user")
  @Test
  public void givenUserWhenPostUserThenUserIsCreatedTest() {
    UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator(); // Tworzymy generator
    user = userTestDataGenerator.generateUser();

    ApiResponse userCreatedRespone = new CreateUserEndpoint().setUser(user).sendRequest().assertRequestSuccess().getResponseModel();

    ApiResponse expectedApiResponse = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message(String.valueOf(user.getId()))
            .build();


    Assertions.assertThat(userCreatedRespone)
            .describedAs("API Response from system was not as expected")
            .usingRecursiveComparison().isEqualTo(expectedApiResponse);

  }

  @AfterTest
  public void cleanUpAfterTest() {
    ApiResponse apiResponse = new DeleteUserEndpoint().setUsername(user.getUsername()).sendRequest().assertRequestSuccess().getResponseModel();

    Assertions.assertThat(apiResponse.getMessage().contains(user.getUsername()));

    ApiResponse expectedApiResponse = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message(user.getUsername())
            .build();

    Assertions.assertThat(apiResponse)
            .describedAs("API Response from system was not as expected")
            .usingRecursiveComparison().isEqualTo(expectedApiResponse);
  }
}
