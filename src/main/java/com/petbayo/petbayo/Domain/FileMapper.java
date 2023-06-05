package com.petbayo.petbayo.Domain;

import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    /**
     * 파일 정보 저장
     *
     * @param files - 파일 정보 리스트
     * @return
     */
    void saveAll(List<FileRequest> files);

    /**
     * 파일 리스트 조회
     * @param petId - 게시글 번호 (FK)
     * @return 파일 리스트
     */
    List<FileResponse> findAllByPetId(Integer petId);

    /**
     * 파일 리스트 조회
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    List<FileResponse> findAllByIds(List<Integer> ids);

    /**
     * 파일 삭제
     * @param ids - PK 리스트
     */
    void deleteAllByIds(List<Integer> ids);
}
