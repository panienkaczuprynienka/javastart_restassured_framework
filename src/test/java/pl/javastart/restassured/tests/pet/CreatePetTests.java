package pl.javastart.restassured.tests.pet;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.enums.PetStatus;
import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.main.properties.EnvironmentConfig;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

  @BeforeMethod
  public void setupConfiguration() {
    EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

    RestAssured.baseURI = environmentConfig.baseUri();
    RestAssured.basePath = environmentConfig.basePath();
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
  }

  @Test
  public void givenPetWhenPostPetThenPetIsCreatedTest() {

    Pet pet = Pet.builder()
            .id(777).category(Category.builder().id(1).name("dogs").build())
            .photoUrls(Collections.singletonList("http://photos.com/dog1.jpg"))
            .tags(Collections.singletonList(Tag.builder().id(1).name("dogs-category").build()))
            .status(PetStatus.SOLD.getStatus())
            .build();

    Pet actualPet = given().body(pet).contentType("application/json")
            .when().post("pet")
            .then().statusCode(200).extract().as(Pet.class);

    assertEquals(actualPet.getId(), pet.getId(), "Pet id");
    assertEquals(actualPet.getName(), pet.getName(), "Pet name");
  }

}