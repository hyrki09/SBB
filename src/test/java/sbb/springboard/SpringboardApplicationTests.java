package sbb.springboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SpringboardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

//	Save 테스트
	@Test
	void contextLoads() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);


		Question q2 = new Question();
		q2.setSubject("스프링 부트 모델 질문입니다.");
		q2.setContent("어떻게 하면 잘해질까요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

	}

}
