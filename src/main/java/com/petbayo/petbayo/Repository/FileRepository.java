package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.FileRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class FileRepository {

    private final SqlSession sqlSession;

    public int saveAll( List<FileRequest> files) {
        return sqlSession.insert("file.saveAll", files);
    }
    public List<HashMap<String,String>> findOne(int petId) {
        return sqlSession.selectList("file.findOne", petId);
    }

    public void delete(int petId) {
        sqlSession.update("file.delete", petId);
    }
}
