package pl.javastart.restassured.tests.pet;

import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.javastart.restassured.main.rop.CreatePetEndpoint;
import pl.javastart.restassured.main.rop.DeletePetEndpoint;
import pl.javastart.restassured.main.test.data.PetTestDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

  private Pet pet;
  private Pet actualPet;

  @Test
  public void givenPetWhenPostPetThenPetIsCreatedTest() {
    pet = new PetTestDataGenerator().generatePet();

    actualPet = new CreatePetEndpoint().setPet(pet).sendRequest().assertRequestSuccess().getResponseModel();

    assertEquals(actualPet.getId(), pet.getId(), "Pet id");
    assertEquals(actualPet.getName(), pet.getName(), "Pet name");
  }


  @AfterTest
  public void cleanUpAfterTest() {
    ApiResponse petDeleted = new DeletePetEndpoint().setPetId(pet.getId()).sendRequest().assertRequestSuccess().getResponseModel();

    ApiResponse expectedApiResponse = ApiResponse.builder()
            .code(200)
            .type("unknown")
            .message(pet.getId().toString())
            .build();

    assertThat(petDeleted.getMessage().contains(String.valueOf(pet.getId())));
    assertThat(petDeleted)
            .describedAs("API Response from system was not as expected")
            .usingRecursiveComparison().isEqualTo(expectedApiResponse);
  }

}