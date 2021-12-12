package pl.javastart.restassured.main.enums;

import java.util.Random;

public enum PetTag {

  TAG_1(1, "young-pet"),
  TAG_2(2, "adult-pet");

  private Integer id;
  private String name;

  PetTag(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  PetTag(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }



}
