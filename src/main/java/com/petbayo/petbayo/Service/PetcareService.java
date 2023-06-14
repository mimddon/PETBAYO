package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Care;
import org.springframework.ui.Model;

import java.util.List;

public interface PetcareService {

    List<Care> careList();

    void careCreate(Care item);

    Care careItem(int textId);

    void careUpdate(Care item);

    void careDelete(int textId);

}
