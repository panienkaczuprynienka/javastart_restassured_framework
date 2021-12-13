package pl.javastart.restassured.tests.pet;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.rop.DeletePetEndpoint;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

public class DeletePetTests extends SuiteTestBase {

  private int nonExistingPetId;

  @BeforeMethod //upewnienie się, że zwierzak nie istnieje
  public void beforeTest() {
    nonExistingPetId = new Faker().number().numberBetween(1000, 10000);
    new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest();
  }

  @Issue("DEFECT-1")
  @TmsLink("ID-2")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Fil to delete non existing pet")
  @Test
  public void givenNonExistingPetWhenDeletingPetThenPetNotFoundTest() {
    new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest().assertStatusCode(HttpStatus.SC_NOT_FOUND);
  }
}
