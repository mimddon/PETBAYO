package com.petbayo.petbayo.Domain;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface FileMapper {
    /**
     * 파일 정보 저장
     *
     * @param files - 파일 정보 리스트
     * @return
     */
    boolean saveAll(List<FileRequest> files);

    List<FileRequest> findOne(int petId);

}
