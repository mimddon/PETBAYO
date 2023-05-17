package com.petbayo.petbayo.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qs_id")
    private Long qsId;

    @Column(length = 256)
    private String qsTitle;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Process process;

    @Enumerated(EnumType.STRING)
    private Disclosure disclosure;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;;

    public Long getQsId() {
        return qsId;
    }

    public void setQsId(Long qsId) {
        this.qsId = qsId;
    }

    public String getQsTitle() {
        return qsTitle;
    }

    public void setQsTitle(String qsTitle) {
        this.qsTitle = qsTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
    public Disclosure getDisclosure() {
        return disclosure;
    }

    public void setDisclosure(Disclosure disclosure) {
        this.disclosure = disclosure;
    }

    public enum Process {
        R, // 'R' - 처리중 (Processing)
        P, // 'P' - 대기중 (Pending)
        C  // 'C' - 처리완료 (Completed)
    }

    public enum Disclosure {
        O, // 'O' - 공개 (Open)
        X  // 'X' - 비공개 (Closed)
    }

    public void update(Question req) {
        this.userId = req.getUserId();
        this.qsTitle = req.getQsTitle();
        this.process = req.getProcess();
    }
}