package com.petbayo.petbayo.Model;


public class Pet {
    private int petId;
    private String petBreed;
    private String petName;
    private int petAge;
    private int petGender;
    private int userId;
    private String petImg;
    private String petImgPath;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public int getPetGender() {
        return petGender;
    }

    public void setPetGender(int petGender) {
        this.petGender = petGender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPetImg() {
        return petImg;
    }

    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }

    public String getPetImgPath() {
        return petImgPath;
    }

    public void setPetImgPath(String petImgPath) {
        this.petImgPath = petImgPath;
    }
}
