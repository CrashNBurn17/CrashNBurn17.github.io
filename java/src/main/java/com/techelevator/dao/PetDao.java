package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.RegisterPetDto;

import java.util.List;
public interface PetDao {

    List<Pet> getPets();

    Pet getPetById(int petId);

    Pet getPetByName(String petName);

    Pet getPetByLocation(int petLocation);

    Pet registerPet(RegisterPetDto pet);

    Pet getPetByBreed (String petBreed);

    Pet getPetByPersonality(String petPersonality);
}
