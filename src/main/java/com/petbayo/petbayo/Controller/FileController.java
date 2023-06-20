package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileUtils;
import com.petbayo.petbayo.Service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    private final FileUtils fileUtils;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/upload-files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Path file = Paths.get(uploadPath).resolve(filename);
        Resource resource;

        try {
            resource = new UrlResource(file.toUri());
        } catch (Exception e) {
            throw new RuntimeException("Issue reading the file", e);
        }

        if (resource.exists() || resource.isReadable()) {
            String mimeType;
            try {
                mimeType = Files.probeContentType(file);
            } catch (IOException e) {
                throw new RuntimeException("Could not determine file type", e);
            }
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(resource);
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    @DeleteMapping("/file/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable int id) {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/file/{petid}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile, @PathVariable int petid) {
        if(fileService.findOne(petid).size() > 1) {
            return ResponseEntity.status(HttpStatus.OK).body("사진은 최대 두 장까지만 가능합니다.");
        }


        if (uploadfile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select a file!");
        }

        FileRequest  fileRequest = fileUtils.uploadFile(uploadfile);
        List<FileRequest> list = new ArrayList();
        list.add(fileRequest);
        fileService.saveFiles(petid, list);


        return ResponseEntity.status(HttpStatus.OK).body("Successfully uploaded - " +
                uploadfile.getOriginalFilename());

    }


}