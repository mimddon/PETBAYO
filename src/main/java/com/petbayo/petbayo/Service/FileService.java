package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Domain.FileMapper;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    private final FileRepository fileRepository;

    @Transactional
    public void saveFiles(final int petId, final List<FileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setPetId(petId);
        }
        fileRepository.saveAll(files);
    }

    @Transactional
    public List<HashMap<String,String>> findOne(int petId) {
        return fileRepository.findOne(petId);
    }

    @Transactional
    public void deleteFile(int petId) {
        fileRepository.delete(petId);
    }


}
