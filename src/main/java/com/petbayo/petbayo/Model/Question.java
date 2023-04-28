package com.petbayo.petbayo.Model;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int qsId;

    @Column(length = 256)
    private String qsTitle;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Process process;

    @Enumerated(EnumType.STRING)
    private Disclosure disclosure;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public int getQsId() {
        return qsId;
    }

    public void setQsId(int qsId) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}