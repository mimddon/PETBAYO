package com.petbayo.petbayo.Model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ChatMessage {

    String id;

    String senderId;

    String recipientId;

    String message;

    LocalDateTime createdAt;

    String senderNickname;

    String recipientNickname;
}
