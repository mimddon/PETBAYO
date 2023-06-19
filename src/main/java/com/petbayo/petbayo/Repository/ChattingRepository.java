package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.ChatMessage;
import com.petbayo.petbayo.Model.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChattingRepository {

    private final SqlSession sqlSession;

    public List<ChatMessage> getChatList(int userId) {
        return sqlSession.selectList("chatting.chattingList", userId);
    }

    public List<ChatMessage> getChatLog(HashMap<String, String> params) {
        return sqlSession.selectList("chatting.chattingLog", params);
    }
    public void submitMessage(ChatRequest chatRequest) {
        sqlSession.insert("chatting.insertMessage", chatRequest);
    }

}
