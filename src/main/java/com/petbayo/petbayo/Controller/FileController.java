package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;


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
}