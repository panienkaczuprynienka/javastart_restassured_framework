package pl.javastart.restassured.main.rop;

import org.apache.http.HttpStatus;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.request.configuration.RequestConfigurationBuilder;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, ApiResponse> {

  private String username;

  @Override
  protected Type getModelType() {
    return ApiResponse.class;
  }

  @Override
  public DeleteUserEndpoint sendRequest() {
   response =  given()
            .spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
            .pathParam("username", username)
            .when()
            .delete("/user/{username}");
    return this;
  }

  @Override
  protected int getSuccessStatusCode() {
    return HttpStatus.SC_OK;
  }

  public DeleteUserEndpoint setUsername(String username) {
    this.username = username;
    return this;
  }
}
