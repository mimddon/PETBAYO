package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Dao.PetDao;
import com.petbayo.petbayo.Model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetDao dao;

    @Override
    public List<Pet> petList(int userId) {
        return dao.petList(userId);
    }

    @Override
    public void petCreate(Pet item, MultipartFile file) throws IOException {
        String petUploadPath = "C:/img/petProfiles";
        File petDirectory = new File(petUploadPath);

        if (!petDirectory.exists()) {
            petDirectory.mkdirs();
        }

        if(!file.isEmpty()) {
            int index = file.getOriginalFilename().indexOf(".");
            String extend = file.getOriginalFilename().substring(index);

            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,12);
            String fileName = uuid + extend;


            File saveFile = new File(petDirectory.getPath(), fileName);

            file.transferTo(saveFile);

            item.setPetImg(fileName);

            String localPath = "C:\\img\\petProfiles\\";
            item.setPetImgPath(localPath + fileName);

        }
        dao.petCreate(item);



        /*if (item.getPetImg() != null) {
            deleteOldFiles(petDirectory.getPath());
        }*/

    }
    /*private void deleteOldFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && files.length > 1) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

            for (int i = 1; i < files.length; i++) {
                files[i].delete();
            }
        }
    }*/

    @Override
    public void petDelete(int petId) {
        dao.petDelete(petId);
    }

    @Override
    public Pet findOne(int petId) {
        return dao.findOne(petId);
    }
}
