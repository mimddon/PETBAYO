package com.petbayo.petbayo.Dao;

import com.petbayo.petbayo.Model.Pet;

import java.util.List;

public interface PetDao {

    List<Pet> petList(int userId);

    void petCreate(Pet item);

    void petDelete(int petId);

    Pet findOne(int petId);
}
