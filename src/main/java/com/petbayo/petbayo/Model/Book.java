package com.petbayo.petbayo.Model;



import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class Book {

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    private int petId;

    private String species;

    private String description;

    private String writer;


    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    private List<MultipartFile> files = new ArrayList<>();

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<Integer> getRemoveFileIds() {
        return removeFileIds;
    }

    public void setRemoveFileIds(List<Integer> removeFileIds) {
        this.removeFileIds = removeFileIds;
    }

    private List<Integer> removeFileIds = new ArrayList<>();


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
