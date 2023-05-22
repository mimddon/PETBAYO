package com.petbayo.petbayo.Model;



import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class Book {

    private Long petId;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    private String species;

    private String description;

    private List<MultipartFile> files = new ArrayList<>();

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
