package com.petbayo.petbayo.Model;

import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Process process;

    @Enumerated(EnumType.STRING)
    private Disclosure disclosure;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;;

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