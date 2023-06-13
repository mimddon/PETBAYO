package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Care;
import com.petbayo.petbayo.Repository.PetcareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PetcareServiceImpl implements  PetcareService {

    @Autowired
    PetcareRepository petcareRepository;

    @Override
    public List<Care> careList() {
        return petcareRepository.careList();
    }

    @Override
    public void careCreate(Care item) {
        petcareRepository.careCreate(item);
    }

    @Override
    public Care careItem(int textId) {
        return petcareRepository.careItem(textId);
    }

    @Override
    public void careUpdate(Care item) {

    }

    @Override
    public void careDelete(int textId) {
        petcareRepository.careDelete(textId);
    }

}
