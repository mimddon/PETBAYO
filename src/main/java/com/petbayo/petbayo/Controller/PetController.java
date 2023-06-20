package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Care;
import com.petbayo.petbayo.Model.Pet;
import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@Slf4j
public class PetController {

    @Autowired
    PetService service;

    @PostMapping("/petCreate/{userId}")
    public String petAdd(@PathVariable int userId, Pet item, @RequestParam(name = "petProfile", required = false) MultipartFile file) throws IOException {
        service.petCreate(item, file);

        return "redirect:/mypage/" + userId;
    }

    @GetMapping("/care/delete/{petId}/{userId}")
    public String petDelete(@PathVariable int petId, @PathVariable int userId) {
        service.petDelete(petId);

        return "redirect:/mypage/" + userId;
    }

    @GetMapping("/api/pet/img/{targetId}")
    @ResponseBody
    public ResponseEntity<byte[]> findPetImg (@PathVariable int targetId) throws Exception{
        Pet findPet = service.findOne(targetId);
        File file = new File(findPet.getPetImgPath());
        byte[] bytes = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        String filename = file.getName();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        switch (fileExtension) {
            case "png":
                headers.setContentType(MediaType.IMAGE_PNG);
                break;
            case "jpg":
            case "jpeg":
                headers.setContentType(MediaType.IMAGE_JPEG);
                break;
            case "gif":
                headers.setContentType(MediaType.IMAGE_GIF);
                break;
            default:
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                break;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(bytes);
    }


}
