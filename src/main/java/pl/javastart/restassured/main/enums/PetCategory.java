package pl.javastart.restassured.main.enums;

import java.util.Random;

public enum PetCategory {

  CATEGORY_1(1, "dogs"),
  CATEGORY_2(2, "cats"),
  CATEGORY_3(3, "other");

  private Integer id;
  private String name;

  PetCategory(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  PetCategory(Integer id) {
    this.id = id;
  }


  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }


}
