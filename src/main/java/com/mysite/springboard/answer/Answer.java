package com.mysite.springboard.answer;


import com.mysite.springboard.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.mysite.springboard.question.Question;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne //    N:1 관계를 나타낼 수 있다.
    private Question question;

    @ManyToOne
    private SiteUser author;
}
