package com.techelevator.model;

public class Pet {

    private int petId;

    private String petName;

    private String petBreed;

    private String petPersonality;

    private int petLocation;

    public Pet() {}

    public Pet(int petId, String petName, String petBreed, String petPersonality, int petLocation) {
        this.petId = petId;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petPersonality = petPersonality;
        this.petLocation = petLocation;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

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
}
