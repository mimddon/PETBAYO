package com.petbayo.petbayo.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class ChatRequest {

    private String message;

    private String senderId;

    private String recipientId;
}
