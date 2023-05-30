package com.petbayo.petbayo.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qsId;

    private int userId;

    @Column(length = 1000)
    private String content;

    private String qsTitle;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    private Process process;

    @Enumerated(EnumType.STRING)
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
