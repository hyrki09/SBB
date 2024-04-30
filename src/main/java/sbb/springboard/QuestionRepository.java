package sbb.springboard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<Question, Integer>는 Question 엔티티로 리포지터리를 생성한다는 의미
// 기본키가 Integer임을 이와 같이 추가로 지정
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);

}
