package com.petbayo.petbayo.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comId;
    private int qsId;

    @Column(name = "user_id")
    private int userId;

    @Column(length = 10000)
    private String contents;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 관계 설정
    @JoinColumn(name = "board_id") // 외래 키
    private Board board;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }
}
