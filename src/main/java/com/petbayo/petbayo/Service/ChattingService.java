package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.ChatMessage;
import com.petbayo.petbayo.Model.ChatRequest;
import com.petbayo.petbayo.Repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRepository chattingRepository;

    public List<ChatMessage> getMessageList(int userId) {
        return chattingRepository.getChatList(userId);
    }

    public List<ChatMessage> getChatLog(HashMap<String, String> params) {
        return chattingRepository.getChatLog(params);
    }

    public void submitMessage(ChatRequest chatRequest) {
        chattingRepository.submitMessage(chatRequest);
    }


}
