package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.FileResponse;
import com.petbayo.petbayo.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileApiController {
    private final FileService fileService;

    // 파일 리스트 조회
    @GetMapping("/book/{petId}/files")
    public List<FileResponse> findAllFileByPetId(@PathVariable final int petId) {
        return fileService.findAllFileByPetId(petId);
    }
}
