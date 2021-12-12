package pl.javastart.restassured.tests.pet;

import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.javastart.restassured.main.test.data.PetTestDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

  private Pet pet;

  @Test
  public void givenPetWhenPostPetThenPetIsCreatedTest() {
    pet = new PetTestDataGenerator().generatePet();

    Pet actualPet = given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .body(pet)
            .when().post("pet")
            .then().statusCode(200).extract().as(Pet.class);

    assertEquals(actualPet.getId(), pet.getId(), "Pet id");
    assertEquals(actualPet.getName(), pet.getName(), "Pet name");
  }


  @AfterTest
  public void cleanUpAfterTest() {
    ApiResponse petDeleted = given()
             .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .pathParam("petId", pet.getId())
            .when()
            .delete("/pet/{petId}")
            .then()
            .statusCode(200)
            .extract().as(ApiResponse.class);

    ApiResponse expectedApiResponse = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message(pet.getId().toString())
            .build();

    Assertions.assertThat(petDeleted.getMessage().contains(String.valueOf(pet.getId())));
    Assertions.assertThat(petDeleted)
            .describedAs("API Response from system was not as expected")
            .usingRecursiveComparison().isEqualTo(expectedApiResponse);
  }

}