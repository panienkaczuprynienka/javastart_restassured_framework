package pl.javastart.restassured.main.rop;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Type;

import static org.assertj.core.api.Assertions.*;

//Parametr E - będzie oznaczał klasę Endpointu na przykład CreatePetEndpoint
//Parametr M - będzie oznaczał klasę POJO (modelu) dla odpowiedzi
public abstract class BaseEndpoint<E, M> {

  // Pole do przechowywania odpowiedzi
  protected Response response;

  // Metoda do zwracania TYPU modelu, czyli klasy POJO. O tym czym jest Type dowiesz się później
  protected abstract Type getModelType();

  // Metoda do wysłania żądania, zwraca ona paramter E, czyli klasę endpointu
  public abstract E sendRequest();

  // Metoda do zwracania kodu sukcesu odpowiedzi
  protected abstract int getSuccessStatusCode();

  // Metoda zwraca odpowiedź w formacie modelu, zapis w metodzie jest równoznaczny z zapisem np. response.as(Pet.class);
  public M getResponseModel() {
    return response.as(getModelType());
  }

  //Metoda do weryfikacji czy kod statusowy jest poprawny tzw. sukces. Metoda bieżę wartość kodu statusowego z metody getSuccessStatusCode()
  public E assertRequestSuccess() {
    return assertStatusCode(getSuccessStatusCode());
  }

  // Metoda do weryfikacji koduy statusowego - dowolnego innego. Zwraca parametr (E), czyli klasę endpointu
  public E assertStatusCode(int statusCode) {
    assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
    return (E) this;
  }

}