package com.petbayo.petbayo.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
    private int qsId;
    private int userId;
    private String content;
    private String qsTitle;
    private Date createdDate;
    private Process process;
    private Disclosure disclosure;

    public enum Process {
        PROCESSING, // 'R' - 처리중 (Processing)
        PENDING, // 'P' - 대기중 (Pending)
        COMPLETED  // 'C' - 처리완료 (Completed)
    }

    public enum Disclosure {
        OPEN, // 'O' - 공개 (Open)
        CLOSED  // 'X' - 비공개 (Closed)
    }

    public void update(Board req) {
        this.userId = req.getUserId();
        this.qsTitle = req.getQsTitle();
        this.process = req.getProcess();
    }
}
