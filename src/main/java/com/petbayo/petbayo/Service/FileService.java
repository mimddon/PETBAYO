package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Domain.FileMapper;
import com.petbayo.petbayo.Model.FileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;


    @Transactional
    public void saveFiles(final int petId, final List<FileRequest> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setPetId(petId);
        }
        fileMapper.saveAll(files);
    }



}
