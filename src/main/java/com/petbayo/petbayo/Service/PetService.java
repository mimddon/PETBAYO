package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PetService {

    List<Pet> petList(int userId);

    void petCreate(Pet item, MultipartFile file) throws IOException;

    void petDelete(int petId);

    Pet findOne(int petId);

}
