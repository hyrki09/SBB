package com.mysite.springboard.question;

import com.mysite.springboard.answer.Answer;
import com.mysite.springboard.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
//    엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블의 열로 인식한다.
//    테이블의 열로 인식하고 싶지 않다면 @Transient 애너테이션을 사용한다.
//    @Transient 애너테이션은 엔티티의 속성을 테이블의 열로 만들지 않고
//    클래스의 속성 기능으로만 사용하고자 할 때 쓴다.

    @ManyToOne
    private SiteUser author;

    // 질문이 사라지면 답변은 자동으로 사라진다. = CascadeType.REMOVE
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
