package com.petbayo.petbayo.Model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qsId;

    private String qsTitle;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private String process;

    private String disclosure;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDisclosure() {
        return disclosure;
    }

    public void setDisclosure(String disclosure) {
        this.disclosure = disclosure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
