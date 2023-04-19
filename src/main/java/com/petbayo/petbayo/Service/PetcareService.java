package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Care;
import java.util.List;

public interface PetcareService {

    List<Care> careList();

    void careCreate(Care item);

    Care careItem(int text_id);

    void careUpdate(Care item);

    void careDelete(int text_id);



}
