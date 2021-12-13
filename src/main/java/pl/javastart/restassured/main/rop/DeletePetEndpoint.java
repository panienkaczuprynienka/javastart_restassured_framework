package pl.javastart.restassured.main.rop;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class DeletePetEndpoint extends BaseEndpoint<DeletePetEndpoint, ApiResponse> {

  private int petId;

  @Override
  protected Type getModelType() {
    return ApiResponse.class;
  }

  @Step("Delete Pet")
  @Override
  public DeletePetEndpoint sendRequest() {
    response = given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .pathParam("petId", petId).when().delete("/pet/{petId}");
    return this;
  }

  @Override
  protected int getSuccessStatusCode() {
    return HttpStatus.SC_OK;
  }

  @Override
  public DeletePetEndpoint assertStatusCode(int statusCode) {
    assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
    return this;
  }

  public DeletePetEndpoint setPetId(int petId) {
    this.petId = petId;
    return this;
  }
}
