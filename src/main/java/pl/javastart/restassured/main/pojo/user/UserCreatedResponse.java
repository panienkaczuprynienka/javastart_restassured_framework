package pl.javastart.restassured.main.pojo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreatedResponse {
  private Integer code;
  private String type;
  private String message;
  private Map<String, Object> additionalProperties = new HashMap<>();  //nie wiem skad to i po co
}
