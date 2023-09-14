package com.techelevator.model;

public class RegisterPetDto {

    private String petName;
    private String petBreed;
    private String petPersonality;

    private int petLocation;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetPersonality() {
        return petPersonality;
    }

    public void setPetPersonality(String petPersonality) {
        this.petPersonality = petPersonality;
    }

    public int getPetLocation() {
        return petLocation;
    }

    public void setPetLocation(int petLocation) {
        this.petLocation = petLocation;
    }

    public RegisterPetDto() {}
}
