package com.petbayo.petbayo.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
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

    private int viewCnt;

    public void update(Board req) {
        this.userId = req.getUserId();
        this.qsTitle = req.getQsTitle();
    }

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = new Date();
    }
     public void setUser(User user){
        this.userId = user.getUserId();
     }
     public User getUser(){
        User user = new User();
        user.setUserId(this.userId);
        return user;
     }
}
