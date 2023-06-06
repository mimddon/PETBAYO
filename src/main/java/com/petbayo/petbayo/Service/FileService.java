package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Domain.FileMapper;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
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

    /**
     * 파일 리스트 조회
     * @param petId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByPetId(final int petId) {
        return fileMapper.findAllByPetId(petId);
    }

    /**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    public List<FileResponse> findAllFileByIds(final List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return fileMapper.findAllByIds(ids);
    }

    /**
     * 파일 삭제 (from Database)
     * @param ids - PK 리스트
     */
    @Transactional
    public void deleteAllFileByIds(final List<Integer> ids) {
        fileMapper.deleteAllByIds(ids);
    }



}
