package pl.javastart.restassured.main.test.data;

import com.github.javafaker.Faker;

public class TestDataGenerator {

  public Faker faker() {
    return Faker.instance();
  }
}
