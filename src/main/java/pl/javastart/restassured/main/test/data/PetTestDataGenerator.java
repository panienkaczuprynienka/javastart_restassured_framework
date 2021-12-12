package pl.javastart.restassured.main.test.data;

import pl.javastart.restassured.main.enums.PetCategory;
import pl.javastart.restassured.main.enums.PetStatus;
import pl.javastart.restassured.main.enums.PetTag;
import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;

import java.util.Collections;
import java.util.Random;

public class PetTestDataGenerator extends TestDataGenerator {


  public Pet generatePet() {

    PetCategory petCategory = randomPetCategory();
    PetTag petTag = randomPetTag();

    Pet pet = Pet.builder()
            .id(faker().number().numberBetween(1, 9999))
            .category(Category.builder().id(petCategory.getId()).name(petCategory.getName()).build())
            .photoUrls(Collections.singletonList(faker().internet().url()))
            .tags(Collections.singletonList(Tag.builder().id(petTag.getId()).name(petTag.getName()).build()))
            .status(randomPetStatus().getStatus())
            .build();

    return pet;
  }


  private PetCategory randomPetCategory() {
    int pick = new Random().nextInt(PetCategory.values().length);
    return PetCategory.values()[pick];
  }

  private PetTag randomPetTag() {
    int pick = new Random().nextInt(PetTag.values().length);
    return PetTag.values()[pick];
  }

  private PetStatus randomPetStatus() {
    int pick = new Random().nextInt(PetStatus.values().length);
    return PetStatus.values()[pick];
  }
}


